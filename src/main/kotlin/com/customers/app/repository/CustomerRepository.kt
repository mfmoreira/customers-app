package com.customers.app.repository

import com.customers.app.domain.Customer
import org.springframework.data.jpa.repository.JpaRepository
import java.util.*

interface CustomerRepository : JpaRepository<Customer, Long> {
    fun findByEmail(email: String): Optional<Customer>
}