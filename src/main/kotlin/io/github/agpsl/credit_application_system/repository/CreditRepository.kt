package io.github.agpsl.credit_application_system.repository

import io.github.agpsl.credit_application_system.entity.Customer
import org.springframework.data.jpa.repository.JpaRepository

interface CreditRepository : JpaRepository<Customer, Long>