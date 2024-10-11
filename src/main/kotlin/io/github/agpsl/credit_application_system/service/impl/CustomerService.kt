package io.github.agpsl.credit_application_system.service.impl

import io.github.agpsl.credit_application_system.entity.Customer
import io.github.agpsl.credit_application_system.exception.BusinessException
import io.github.agpsl.credit_application_system.repository.CustomerRepository
import io.github.agpsl.credit_application_system.service.ICustomerService
import org.springframework.stereotype.Service

@Service
class CustomerService (
    private val customerRepository: CustomerRepository
) : ICustomerService {

    override fun save(customer: Customer): Customer = customerRepository.save(customer)

    override fun findById(id: Long): Customer = customerRepository.findById(id).orElseThrow {
        throw BusinessException("Customer with ID $id not found")
    }

    override fun delete(id: Long) {
        val customer = customerRepository.findById(id).orElseThrow{throw BusinessException("Customer with ID $id not found")}
        customerRepository.delete(customer)
    }

}