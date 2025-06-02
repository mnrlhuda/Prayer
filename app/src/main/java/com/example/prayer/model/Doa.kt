package com.example.prayer.model

import java.io.Serializable

data class Doa(
    val id: String,
    val doa: String,
    val ayat: String,
    val latin: String,
    val artinya: String
) : Serializable
