package io.github.agpsl.credit_application_system.dto

import io.github.agpsl.credit_application_system.entity.Address
import io.github.agpsl.credit_application_system.entity.Customer
import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotEmpty
import jakarta.validation.constraints.NotNull
import org.hibernate.validator.constraints.br.CPF
import java.math.BigDecimal

data class CustomerDto (
    @field:NotEmpty(message = "Invalid name") val firstName: String,
    @field:NotEmpty(message = "Invalid surname") val lastName: String,
    @field:CPF(message = "Invalid CPF") val cpf: String,
    @field:NotNull(message = "Invalid income") val income: BigDecimal,
    @field:Email(message = "Invalid e-mail")val email: String,
    @field:NotEmpty(message = "Invalid password")val password: String,
    @field:NotEmpty(message = "Invalid ZIP code")val zipCode: String,
    @field:NotEmpty(message = "Invalid street")val street: String
) {
    fun toEntity(): Customer = Customer(
        firstName = this.firstName,
        lastName = this.lastName,
        cpf = this.cpf,
        income = this.income,
        email = this.email,
        password = this.password,
        address = Address(
            zipCode = this.zipCode,
            street = this.street
        )
    )
}

