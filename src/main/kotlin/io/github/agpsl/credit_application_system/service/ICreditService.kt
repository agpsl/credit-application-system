package io.github.agpsl.credit_application_system.service

import io.github.agpsl.credit_application_system.entity.Credit
import java.util.*

interface ICreditService {
    fun save(credit: Credit): Credit

    fun findAllByCustomer(customerId: Long): List<Credit>

    fun findByCreditCode(creditCode: UUID): Credit
}