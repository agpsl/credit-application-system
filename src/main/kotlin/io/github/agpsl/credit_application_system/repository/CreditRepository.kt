package io.github.agpsl.credit_application_system.repository

import io.github.agpsl.credit_application_system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CreditRepository : JpaRepository<Credit, Long> {
    fun findByCreditCode(creditCode: UUID): Credit?
}