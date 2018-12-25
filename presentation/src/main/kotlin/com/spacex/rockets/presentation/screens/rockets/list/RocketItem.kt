package com.spacex.rockets.presentation.screens.rockets.list

import java.io.Serializable

data class RocketItem(
        val id: String,
        val name: String,
        val country: String,
        val enginesCount: Int,
        val active: Boolean,
        val description: String) : Serializable