package io.github.agpsl.credit_application_system.service

import io.github.agpsl.credit_application_system.entity.Customer

interface ICustomerService {
    fun save(customer: Customer): Customer

    fun findById(id: Long): Customer

    fun delete(id: Long)
}