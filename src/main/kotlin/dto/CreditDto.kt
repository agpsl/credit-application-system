package dto

import io.github.agpsl.credit_application_system.entity.Credit
import io.github.agpsl.credit_application_system.entity.Customer
import java.math.BigDecimal
import java.time.LocalDate

data class CreditDto(
    val customerId: Long,
    val creditValue: BigDecimal,
    val dayFirstInstallment: LocalDate,
    val numberOfInstallments: Int
) {
    fun toEntity(): Credit = Credit(
        creditValue = this.creditValue,
        dayFirstInstallment = this.dayFirstInstallment,
        numberOfInstallments = this.numberOfInstallments,
        customer = Customer(id = this.customerId)
    )
}
