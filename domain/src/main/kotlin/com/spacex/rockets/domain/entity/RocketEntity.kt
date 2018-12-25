package com.spacex.rockets.domain.entity

data class RocketEntity(
        val id: String,
        val name: String,
        val country: String,
        val enginesCount: Int,
        val active: Boolean,
        val description: String

) : DomainEntity