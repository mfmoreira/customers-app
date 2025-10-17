package com.customers.app.domain


import jakarta.persistence.*
import java.time.Instant

@Entity
@Table(name = "customers", uniqueConstraints = [UniqueConstraint(columnNames = ["email"])])
class Customer(
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var createdAt: Instant = Instant.now()
)