package controller

import dto.CustomerDto
import dto.CustomerUpdateDto
import dto.CustomerView
import io.github.agpsl.credit_application_system.service.impl.CustomerService
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customer")
class CustomerResource(
    private val customerService: CustomerService
){
    @PostMapping
    fun saveCustomer(@RequestBody customerDto: CustomerDto): String {
        val savedCustomer = customerService.save(customerDto.toEntity())
        return "Customer ${savedCustomer.email} saved!"
    }

    @GetMapping("/{id}")
    fun findById (@PathVariable id: Long): CustomerView {
        val customer = customerService.findById(id)
        return CustomerView(customer)
    }

    @DeleteMapping("/{id}")
    fun deleteCustomer(@PathVariable id: Long) = customerService.delete(id)

    @PatchMapping("/{id}")
    fun updateCustomer(@RequestParam(value = "customerId") id: Long,
                       @RequestBody customerUpdateDto: CustomerUpdateDto): CustomerView {
        val customer = this.customerService.findById(id)
        val customerToUpdate = customerUpdateDto.toEntity(customer)
        val customerUpdated = this.customerService.save(customerToUpdate)
        return CustomerView(customerUpdated)
    }
}