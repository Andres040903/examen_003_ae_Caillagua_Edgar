package com.examen.inventoryconfig.dto

import jakarta.validation.constraints.NotBlank

data class InventoryRuleRequest(
    @field:NotBlank(message = "name is required")
    val name: String,
    val description: String? = null,
    val isActive: Boolean = true
)
