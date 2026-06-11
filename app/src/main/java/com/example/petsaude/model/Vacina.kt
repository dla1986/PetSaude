package com.example.petsaude.model

import java.time.LocalDate

data class Vacina(

    val id: Int = 0,
    val petId: Int,
    val nomeVacina: String,
    val veterinarioResp: String,
    val dataAplicada: LocalDate,
    val proximoReforco: LocalDate
)
