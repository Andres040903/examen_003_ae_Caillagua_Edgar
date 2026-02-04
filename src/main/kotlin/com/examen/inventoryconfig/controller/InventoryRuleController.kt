package com.examen.inventoryconfig.controller

import com.examen.inventoryconfig.dto.InventoryRuleRequest
import com.examen.inventoryconfig.dto.InventoryRuleResponse
import com.examen.inventoryconfig.service.InventoryRuleService
import jakarta.validation.Valid
import org.springframework.http.HttpStatus
import org.springframework.security.access.prepost.PreAuthorize
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/rules")
class InventoryRuleController(
    private val service: InventoryRuleService
) {
    // 2) Autenticado: cualquier token válido
    @GetMapping
    fun list(): List<InventoryRuleResponse> = service.findAll()

    @GetMapping("/{id}")
    fun get(@PathVariable id: Long): InventoryRuleResponse = service.findById(id)

    // 3) Admin
    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun create(@Valid @RequestBody req: InventoryRuleRequest): InventoryRuleResponse =
        service.create(req)

    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/{id}")
    fun update(@PathVariable id: Long, @Valid @RequestBody req: InventoryRuleRequest): InventoryRuleResponse =
        service.update(id, req)

    @PreAuthorize("hasRole('ADMIN')")
    @PatchMapping("/{id}/toggle")
    fun toggle(@PathVariable id: Long): InventoryRuleResponse =
        service.toggle(id)
}
