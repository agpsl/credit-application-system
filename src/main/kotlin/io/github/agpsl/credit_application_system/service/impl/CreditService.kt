package io.github.agpsl.credit_application_system.service.impl

import io.github.agpsl.credit_application_system.entity.Credit
import io.github.agpsl.credit_application_system.exception.BusinessException
import io.github.agpsl.credit_application_system.repository.CreditRepository
import io.github.agpsl.credit_application_system.service.ICreditService
import org.springframework.stereotype.Service
import java.time.LocalDate
import java.time.temporal.ChronoUnit
import java.util.*

@Service
class CreditService(
    private val creditRepository: CreditRepository,
    private val customerService: CustomerService
) : ICreditService {

    /**
    *  Business Rules
    * - date of first installment should not be less than 3 months from now
    * - number of installments should be less than or equal to 48
    */
    override fun save(credit: Credit): Credit {
        val firstInstallmentInDays = LocalDate.now().until(credit.dayFirstInstallment, ChronoUnit.DAYS)

        if (firstInstallmentInDays > 90)
            throw BusinessException("First installment should be no less than 90 days from now")
        else if (credit.numberOfInstallments > 48)
            throw BusinessException("Too many installments")

        credit.apply { customer = customerService.findById(credit.customer?.id!!) }
        return creditRepository.save(credit)
    }

    override fun findAllByCustomerId(customerId: Long): List<Credit> = creditRepository.findAllByCustomerId(customerId)

    override fun findByCreditCode(customerId: Long, creditCode: UUID): Credit {
        val credit: Credit =
            creditRepository.findByCreditCode(creditCode) ?: throw IllegalArgumentException("Credit Code not found")
        return if (credit.customer?.id == customerId) credit else throw IllegalArgumentException("Credit Code not found")
    }
}