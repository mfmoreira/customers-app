package com.customers.app.controller

import com.customers.app.domain.Customer
import com.customers.app.dto.CustomerDTO
import com.customers.app.service.CustomerService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/customers")
class CustomerController(private val service: CustomerService) {

    @GetMapping
    fun all(): List<Customer> = service.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): ResponseEntity<Customer> =
        ResponseEntity.ok(service.findById(id))

    @PostMapping
    fun create(@Valid @RequestBody dto: CustomerDTO): ResponseEntity<Customer> =
        ResponseEntity.status(HttpStatus.CREATED).body(service.create(dto))

    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody dto: CustomerDTO): ResponseEntity<Customer> =
        ResponseEntity.ok(service.update(id, dto))

    @DeleteMapping("/{id}")
    fun delete(@PathVariable id: Long): ResponseEntity<Void> {
        service.delete(id)
        return ResponseEntity.noContent().build()
    }
}