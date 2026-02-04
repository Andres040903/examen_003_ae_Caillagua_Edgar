package com.examen.inventoryconfig.mapper

import com.examen.inventoryconfig.dto.InventoryRuleRequest
import com.examen.inventoryconfig.dto.InventoryRuleResponse
import com.examen.inventoryconfig.entity.InventoryRule
import java.time.OffsetDateTime

object InventoryRuleMapper {

    fun toEntity(request: InventoryRuleRequest, userId: String): InventoryRule {
        val now = OffsetDateTime.now()
        return InventoryRule(
            name = request.name,
            description = request.description,
            isActive = request.isActive,
            updatedBy = userId,
            createdAt = now,
            updatedAt = now
        )
    }

    fun toResponse(entity: InventoryRule): InventoryRuleResponse {
        return InventoryRuleResponse(
            id = entity.id ?: 0L,
            name = entity.name,
            description = entity.description,
            isActive = entity.isActive,
            updatedBy = entity.updatedBy,
            createdAt = entity.createdAt,
            updatedAt = entity.updatedAt
        )
    }
}
