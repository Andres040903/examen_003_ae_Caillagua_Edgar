package com.examen.inventoryconfig.dto

import java.time.OffsetDateTime

data class InventoryRuleResponse(
    val id: Long,
    val name: String,
    val description: String?,
    val isActive: Boolean,
    val updatedBy: String,
    val createdAt: OffsetDateTime,
    val updatedAt: OffsetDateTime
)
