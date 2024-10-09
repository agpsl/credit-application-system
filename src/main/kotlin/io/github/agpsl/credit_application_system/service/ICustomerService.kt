package io.github.agpsl.credit_application_system.service

import io.github.agpsl.credit_application_system.entity.Credit
import io.github.agpsl.credit_application_system.entity.Customer
import java.util.UUID

interface ICustomerService {
    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long): Customer
}