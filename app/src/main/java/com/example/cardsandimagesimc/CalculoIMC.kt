package com.example.cardsandimagesimc

import kotlin.math.pow

fun calcularIMC(altura: Double, peso: Double): Double {
    return peso / (altura * altura)
}

fun determinarCategoriaIMC(imc: Double): String {
    return when {
        imc < 18.5 -> "Abaixo do peso"
        imc < 25.0 -> "Peso normal"
        imc < 30.0 -> "Sobrepeso"
        imc < 35.0 -> "Obesidade grau 1"
        imc < 40.0 -> "Obesidade grau 2"
        else -> "Obesidade grau 3"
    }
}