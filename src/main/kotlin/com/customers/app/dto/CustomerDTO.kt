package com.customers.app.dto

import jakarta.validation.constraints.Email
import jakarta.validation.constraints.NotBlank

data class CustomerDTO(
    @field:NotBlank
    val name: String,

    @field:NotBlank
    @field:Email
    val email: String
)