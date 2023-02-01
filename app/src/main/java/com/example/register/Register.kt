package com.example.register

data class Register(
    var id: Long = 0,
    var name: String = "",
    var lastName: String = "",
    var phone: String = "",
    var address: String = "",
    var description: String = "") {
}