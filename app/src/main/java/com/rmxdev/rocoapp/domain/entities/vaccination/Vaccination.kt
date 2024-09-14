package com.rmxdev.rocoapp.domain.entities.vaccination

import com.rmxdev.rocoapp.domain.entities.pet.Pet

data class Vaccination(
    val pet: Pet,
    val dateApplication: String,
    val name: String,
    val dateRenewal: String
)
