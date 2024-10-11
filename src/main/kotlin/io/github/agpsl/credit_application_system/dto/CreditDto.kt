package io.github.agpsl.credit_application_system.dto

import io.github.agpsl.credit_application_system.entity.Credit
import io.github.agpsl.credit_application_system.entity.Customer
import jakarta.validation.constraints.Future
import jakarta.validation.constraints.NotNull
import jakarta.validation.constraints.Positive
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    @field:NotNull(message = "Invalid customer ID") val customerId: Long,
    @field:NotNull(message = "Invalid credit value") val creditValue: BigDecimal,
    @field:Future(message = "Invalid date for first installment") val dayFirstInstallment: LocalDate,
    @field:Positive(message = "Invalid number of installments")val numberOfInstallments: Int
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
