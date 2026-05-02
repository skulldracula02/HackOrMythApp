package com.example.hackormythapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class QuizActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizScreen()
        }
    }
}

/* 🟠 THEME COLORS (CHANGE HERE IF YOU WANT NEW DESIGN) */
val darkOrange = Color(0xFFFF6F00)
val lightOrange = Color(0xFFFFE0B2)
val cardWhite = Color.White
val textBlack = Color.Black

@Composable
fun QuizScreen() {

    /* 🟠 QUESTIONS */
    val questions = listOf(
        "Drinking water improves focus" to true,
        "Putting phone in freezer charges it faster" to false,
        "Carrots give instant night vision" to false,
        "Sleeping 2 hours is healthy" to false,
        "Stretching improves flexibility" to true
    )

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var feedback by remember { mutableStateOf("") }
    var isFinished by remember { mutableStateOf(false) }

    val currentQuestion = questions[currentIndex]
    val totalQuestions = questions.size

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightOrange)
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /* TITLE */
        Text(
            text = "Hack or Myth Quiz",
            fontSize = 28.sp,
            fontWeight = FontWeight.Bold,
            color = darkOrange
        )

        Spacer(modifier = Modifier.height(20.dp))

        /* QUESTION CARD */
        Card(
            colors = CardDefaults.cardColors(containerColor = cardWhite),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = currentQuestion.first,
                fontSize = 22.sp,
                modifier = Modifier.padding(20.dp),
                color = textBlack
            )
        }

        Spacer(modifier = Modifier.height(25.dp))

        /* HACK + MYTH BUTTONS */
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceEvenly
        ) {

            Button(
                onClick = {
                    if (!isFinished) {
                        if (currentQuestion.second) {
                            score++
                            feedback = "Correct! It's a Hack."
                        } else {
                            feedback = "Wrong! It's a Myth."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
                modifier = Modifier.weight(1f).padding(end = 8.dp)
            ) {
                Text("Hack", fontSize = 18.sp)
            }

            Button(
                onClick = {
                    if (!isFinished) {
                        if (!currentQuestion.second) {
                            score++
                            feedback = "Correct! It's a Myth."
                        } else {
                            feedback = "Wrong! It's a Hack."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
                modifier = Modifier.weight(1f).padding(start = 8.dp)
            ) {
                Text("Myth", fontSize = 18.sp)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        /* FEEDBACK */
        Text(
            text = feedback,
            fontSize = 18.sp,
            color = textBlack
        )

        Spacer(modifier = Modifier.height(20.dp))

        /* NEXT BUTTON */
        Button(
            onClick = {
                if (currentIndex < totalQuestions - 1) {
                    currentIndex++
                    feedback = ""
                } else {
                    isFinished = true
                    feedback = "Quiz Finished!"
                }
            },
            colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Next Question", fontSize = 18.sp)
        }

        Spacer(modifier = Modifier.height(25.dp))

        /* SCORE DISPLAY */
        Text(
            text = "Score: $score / $totalQuestions",
            fontSize = 22.sp,
            fontWeight = FontWeight.Bold,
            color = darkOrange
        )

        /* RESTART BUTTON (ONLY SHOWS AT END) */
        if (isFinished) {

            Spacer(modifier = Modifier.height(20.dp))

            Button(
                onClick = {
                    currentIndex = 0
                    score = 0
                    feedback = ""
                    isFinished = false
                },
                colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Restart Quiz", fontSize = 18.sp)
            }
        }
    }
}