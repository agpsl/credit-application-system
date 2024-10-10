package io.github.agpsl.credit_application_system.controller

import io.github.agpsl.credit_application_system.dto.CustomerDto
import io.github.agpsl.credit_application_system.dto.CustomerUpdateDto
import io.github.agpsl.credit_application_system.dto.CustomerView
import io.github.agpsl.credit_application_system.service.impl.CustomerService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customer")
class CustomerResource(
    private val customerService: CustomerService
){
    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): ResponseEntity<String> {
        val savedCustomer = customerService.save(customerDto.toEntity())
        return ResponseEntity
            .status(HttpStatus.OK)
            .body("Customer ${savedCustomer.email} saved!")
    }

    @GetMapping("/{id}")
    fun findById (@PathVariable id: Long): ResponseEntity<CustomerView> {
        val customer = customerService.findById(id)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CustomerView(customer))
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = customerService.delete(id)

    @PatchMapping("/{id}")
    fun updateCustomer(@PathVariable id: Long,
                       @RequestBody customerUpdateDto: CustomerUpdateDto
    ): ResponseEntity<CustomerView> {
        val customer = this.customerService.findById(id)
        val customerToUpdate = customerUpdateDto.toEntity(customer)
        val customerUpdated = this.customerService.save(customerToUpdate)
        return ResponseEntity
            .status(HttpStatus.OK)
            .body(CustomerView(customerUpdated))
    }
}