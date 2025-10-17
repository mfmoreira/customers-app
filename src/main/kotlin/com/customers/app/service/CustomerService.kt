package com.customers.app.service

import com.customers.app.domain.Customer
import com.customers.app.dto.CustomerDTO
import com.customers.app.repository.CustomerRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.*

@Service
@Transactional
class CustomerService(private val repo: CustomerRepository) {

    fun create(dto: CustomerDTO): Customer {
        if (repo.findByEmail(dto.email).isPresent) {
            throw IllegalArgumentException("Email já cadastrado")
        }
        val customer = Customer(name = dto.name, email = dto.email)
        return repo.save(customer)
    }

    @Transactional(readOnly = true)
    fun findAll(): List<Customer> = repo.findAll()

    @Transactional(readOnly = true)
    fun findById(id: Long): Customer =
        repo.findById(id).orElseThrow { NoSuchElementException("Cliente não encontrado") }

    fun update(id: Long, dto: CustomerDTO): Customer {
        val existing = findById(id)
        existing.name = dto.name
        existing.email = dto.email
        return repo.save(existing)
    }

    fun delete(id: Long) = repo.deleteById(id)
}