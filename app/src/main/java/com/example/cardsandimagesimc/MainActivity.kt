package com.example.cardsandimagesimc

import android.R.attr.y
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.cardsandimagesimc.ui.theme.CardsAndImagesIMCTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CardsAndImagesIMCTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    IMCScreen(
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }
}

@Composable
fun IMCScreen(modifier: Modifier = Modifier){
    var altura by remember {
        mutableStateOf("")
    }

    var peso by remember {
        mutableStateOf("")
    }

    var imc by remember {
        mutableStateOf(0.0)
    }

    var categoriaImc by remember {
        mutableStateOf("")
    }

    Column(
        modifier = modifier.fillMaxSize()
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ){
            Column(modifier = Modifier.fillMaxSize()) {

                // -- header --
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .height(160.dp)
                        .background(color = colorResource(id = R.color.cor_app)),
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Image(
                        painter = painterResource(R.drawable.bmi),
                        contentDescription = "bmi",
                        modifier = Modifier.size(80.dp)
                            .padding(16.dp)
                    )

                    Text(
                        text = "Calculadora IMC",
                        fontSize = 24.sp,
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }
                Column(
                    modifier = Modifier.fillMaxWidth()
                        .padding(horizontal = 32.dp)
                ) {
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(300.dp)
                            .offset(y = (-30).dp),
                        colors = CardDefaults.cardColors(
                            containerColor = Color(0xFFF9F6F6)
                        ),
                        elevation = CardDefaults.cardElevation(4.dp)
                    ) {
                        Column(
                            modifier = Modifier.fillMaxWidth()
                                .padding(horizontal = 30.dp),
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Text(
                                text = "Seus dados",
                                fontSize = 22.sp,
                                color = colorResource(R.color.cor_app),
                                fontWeight = FontWeight.Bold,
                                modifier = Modifier
                                    .padding(20.dp)
                            )

                            OutlinedTextField(
                                value = altura,
                                onValueChange = {altura= it},
                                label = {Text(text = "Altura")},
                                shape = RoundedCornerShape(15.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                        focusedBorderColor = colorResource(R.color.cor_app),
                                        unfocusedBorderColor =  colorResource(R.color.cor_app),
                                        focusedLabelColor = colorResource(R.color.cor_app)
                            )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedTextField(
                                value = peso,
                                onValueChange = {peso= it},
                                label = {Text(text = "Peso")},
                                shape = RoundedCornerShape(15.dp),
                                colors = OutlinedTextFieldDefaults.colors(
                                    focusedBorderColor = colorResource(R.color.cor_app),
                                    unfocusedBorderColor =  colorResource(R.color.cor_app),
                                    focusedLabelColor = colorResource(R.color.cor_app)
                                )
                            )

                            Spacer(modifier = Modifier.height(10.dp))

                            OutlinedButton(
                                onClick = {
                                    val alturaNum= altura.replace(",",".").toDoubleOrNull()
                                    val pesoNum = peso.replace(",",".").toDoubleOrNull()
                                    if (alturaNum != null && alturaNum > 0 && pesoNum !=null){
                                        imc = calcularIMC(altura = alturaNum, peso = pesoNum)
                                        categoriaImc = determinarCategoriaIMC(imc)
                                    }
                                },
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .height(45.dp),
                                colors = ButtonDefaults.outlinedButtonColors(
                                    containerColor = colorResource(R.color.cor_app),
                                    contentColor = Color.White
                                ),
                                border = null
                            ) {
                                Text(text = "CALCULAR")

                            }


                        }


                    }
                }
            }
            if (categoriaImc.isNotEmpty()) {
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 32.dp)
                        .padding(top = 16.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = Color(0xFF4CAF50)
                    )
                ) {
                    Row(
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(20.dp),
                        verticalAlignment = Alignment.CenterVertically
                    ) {
                        Text(
                            text = String.format("%.1f", imc),
                            fontSize = 28.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                        Spacer(modifier = Modifier.width(12.dp))
                        Text(
                            text = categoriaImc,
                            fontSize = 20.sp,
                            fontWeight = FontWeight.Bold,
                            color = Color.White
                        )
                    }
                }
            }


        }

    }
}

