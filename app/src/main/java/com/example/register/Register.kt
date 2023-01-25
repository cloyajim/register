package com.example.register

data class Register(
    val id: Long = 0,
    val name: String = "",
    val lastName: String = "",
    val phone: String = "",
    val address: String = "",
    val description: String = "") {
}