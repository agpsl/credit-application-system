package io.github.agpsl.credit_application_system.dto

import io.github.agpsl.credit_application_system.entity.Customer
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import java.math.BigDecimal

data class CustomerUpdateDto(
    @field:NotEmpty(message = "Invalid name") val firstName: String,
    @field:NotEmpty(message = "Invalid surname") val lastName: String,
    @field:NotNull(message = "Invalid income") val income: BigDecimal,
    @field:NotEmpty(message = "Invalid ZIP code") val zipCode: String,
    @field:NotEmpty(message = "Invalid street") val street: String
){
    fun toEntity(customer: Customer): Customer {
        customer.firstName = this.firstName
        customer.lastName = this.lastName
        customer.income = this.income
        customer.address.zipCode = this.zipCode
        customer.address.street = this.street
        return customer
    }
}
