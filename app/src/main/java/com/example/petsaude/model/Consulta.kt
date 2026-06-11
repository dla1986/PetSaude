package com.example.petsaude.model

import java.time.LocalDate
import java.time.LocalTime

data class Consulta(

    val id: Int = 0,
    val petId: Int,
    val nomeVeteriario: String,
    val motivo: String,
    val data: LocalDate,
    val horario: LocalTime

)
