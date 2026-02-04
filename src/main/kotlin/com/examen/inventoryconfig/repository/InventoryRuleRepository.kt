package com.examen.inventoryconfig.repository

import com.examen.inventoryconfig.entity.InventoryRule
import org.springframework.data.jpa.repository.JpaRepository

interface InventoryRuleRepository : JpaRepository<InventoryRule, Long> {
    fun findByIsActiveTrue(): List<InventoryRule>
    fun findByNameContainingIgnoreCase(name: String): List<InventoryRule>
}
