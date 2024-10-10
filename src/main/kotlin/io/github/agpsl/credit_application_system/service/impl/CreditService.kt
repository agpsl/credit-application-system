package io.github.agpsl.credit_application_system.service.impl

import io.github.agpsl.credit_application_system.entity.Credit
import io.github.agpsl.credit_application_system.repository.CreditRepository
import io.github.agpsl.credit_application_system.service.ICreditService
import org.springframework.stereotype.Service
import java.lang.RuntimeException
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : ICreditService {

    override fun save(credit: Credit): Credit {
        credit.apply { customer = customerService.findById(credit.customer?.id!!) }
        return creditRepository.save(credit)
    }

    override fun findAllByCustomer(customerId: Long): List<Credit> {
        TODO("Not yet implemented")
    }

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit = creditRepository.findByCreditCode(creditCode) ?: throw RuntimeException("Credit Code not found")
        return if (credit.customer?.id == customerId) credit else throw RuntimeException("Contact admin")
    }
}