package com.examen.inventoryconfig.service

import com.examen.inventoryconfig.dto.InventoryRuleRequest
import com.examen.inventoryconfig.dto.InventoryRuleResponse
import com.examen.inventoryconfig.exception.NotFoundException
import com.examen.inventoryconfig.mapper.InventoryRuleMapper
import com.examen.inventoryconfig.repository.InventoryRuleRepository
import com.examen.inventoryconfig.util.AuthUtils
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.time.OffsetDateTime

@Service
class InventoryRuleService(
    private val repository: InventoryRuleRepository
) {

    fun findAll(): List<InventoryRuleResponse> =
        repository.findAll().map { InventoryRuleMapper.toResponse(it) }

    fun findById(id: Long): InventoryRuleResponse {
        val rule = repository.findById(id)
            .orElseThrow { NotFoundException("Rule $id not found") }
        return InventoryRuleMapper.toResponse(rule)
    }

    @Transactional
    fun create(request: InventoryRuleRequest): InventoryRuleResponse {
        val userId = AuthUtils.getUserId("sub")
        val entity = InventoryRuleMapper.toEntity(request, userId)
        return InventoryRuleMapper.toResponse(repository.save(entity))
    }

    @Transactional
    fun update(id: Long, request: InventoryRuleRequest): InventoryRuleResponse {
        val userId = AuthUtils.getUserId("sub")

        val rule = repository.findById(id)
            .orElseThrow { NotFoundException("Rule $id not found") }

        rule.name = request.name
        rule.description = request.description
        rule.isActive = request.isActive
        rule.updatedBy = userId
        rule.updatedAt = OffsetDateTime.now()

        return InventoryRuleMapper.toResponse(repository.save(rule))
    }

    @Transactional
    fun toggle(id: Long): InventoryRuleResponse {
        val userId = AuthUtils.getUserId("sub")

        val rule = repository.findById(id)
            .orElseThrow { NotFoundException("Rule $id not found") }

        rule.isActive = !rule.isActive
        rule.updatedBy = userId
        rule.updatedAt = OffsetDateTime.now()

        return InventoryRuleMapper.toResponse(repository.save(rule))
    }
}
