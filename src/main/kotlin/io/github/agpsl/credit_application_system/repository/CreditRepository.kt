package io.github.agpsl.credit_application_system.repository

import io.github.agpsl.credit_application_system.entity.Credit
import org.springframework.data.jpa.repository.JpaRepository

interface CreditRepository : JpaRepository<Credit, Long>