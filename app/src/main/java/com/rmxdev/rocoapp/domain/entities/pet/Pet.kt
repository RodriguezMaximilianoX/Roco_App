package com.rmxdev.rocoapp.domain.entities.pet

import com.rmxdev.rocoapp.domain.entities.user.User

data class Pet(
    val owner: User,
    val name: String,
    val age: Int,
    val gender: String,
)
