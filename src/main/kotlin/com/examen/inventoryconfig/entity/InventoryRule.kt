package com.examen.inventoryconfig.entity

import jakarta.persistence.*
import java.time.OffsetDateTime

@Entity
@Table(name = "inventory_rules")
class InventoryRule(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @Column(nullable = false)
    var name: String,

    @Column(nullable = true)
    var description: String? = null,

    @Column(nullable = false)
    var isActive: Boolean = true,

    @Column(nullable = false)
    var updatedBy: String,

    @Column(nullable = false)
    var createdAt: OffsetDateTime = OffsetDateTime.now(),

    @Column(nullable = false)
    var updatedAt: OffsetDateTime = OffsetDateTime.now()
)
