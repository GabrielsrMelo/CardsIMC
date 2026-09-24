package com.example.cardsandimagesimc

import kotlin.math.pow

fun  calcularIMC(altura: Double, peso: Double): Double{
    return peso / (altura / 100).pow(2.0)
}

fun determinarCategoriaIMC(imc: Double): String {
    return if (imc < 18.5) {
        "Abaixo do peso"
    } else if (imc >= 18.5 && imc < 25.0) {
        "Peso ideal"
    } else if (imc >= 25.0 && imc < 30.0) {
        "Levemente acima do peso"
    } else if (imc >= 30.0 && imc < 35.0) {
        "Obesidade grau I"
    } else if (imc >= 35.0 && imc < 40.0) {
        "Obesidade grau II"
    } else {
        "Obesidade grau III"
    }
}