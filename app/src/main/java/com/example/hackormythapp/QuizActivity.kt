package com.example.hackormythapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.platform.LocalContext

class QuizActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            QuizScreen {
                // restart goes back to MainActivity
                startActivity(Intent(this, MainActivity::class.java))
                finish()
            }
        }
    }
}

/* ---------------- DATA (QUESTIONS) ---------------- */

data class Question(
    val text: String,
    val answer: Boolean,
    val explanation: String
)

/* ---------------- QUIZ SCREEN ---------------- */

@Composable
fun QuizScreen(onRestart: () -> Unit) {

    // Questions list (simple beginner level)
    val questions = listOf(
        Question("Drinking water improves concentration", true, "Correct! Staying hydrated helps brain function."),
        Question("Putting ice on burns is safe", false, "Wrong! Ice can damage skin tissue."),
        Question("Reading improves memory", true, "Correct! Reading strengthens brain activity."),
        Question("You should wash hands after eating only", false, "Wrong! You must wash before eating too.")
    )

    var currentIndex by remember { mutableStateOf(0) }
    var score by remember { mutableStateOf(0) }
    var feedback by remember { mutableStateOf("") }
    var answered by remember { mutableStateOf(false) }
    var quizFinished by remember { mutableStateOf(false) }

    val currentQuestion = questions[currentIndex]

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightOrange)
            .padding(20.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {

        // TITLE
        Text(
            text = "Hack or Myth?",
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold,
            color = darkOrange
        )

        Spacer(modifier = Modifier.height(20.dp))

        // QUESTION BOX
        Text(
            text = currentQuestion.text,
            fontSize = 20.sp,
            fontWeight = FontWeight.Medium,
            color = darkOrange
        )

        Spacer(modifier = Modifier.height(30.dp))

        // HACK / MYTH BUTTONS SIDE BY SIDE
        Row {

            Button(
                onClick = {
                    if (!answered) {
                        answered = true
                        if (currentQuestion.answer) {
                            score++
                            feedback = "Correct! That's a real hack."
                        } else {
                            feedback = "Wrong! That's a myth."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Hack", color = white)
            }

            Button(
                onClick = {
                    if (!answered) {
                        answered = true
                        if (!currentQuestion.answer) {
                            score++
                            feedback = "Correct! That's a myth."
                        } else {
                            feedback = "Wrong! That's a real hack."
                        }
                    }
                },
                colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
                modifier = Modifier.padding(8.dp)
            ) {
                Text("Myth", color = white)
            }
        }

        Spacer(modifier = Modifier.height(20.dp))

        // FEEDBACK
        Text(
            text = feedback,
            fontSize = 16.sp,
            color = darkOrange
        )

        Spacer(modifier = Modifier.height(20.dp))

        // NEXT BUTTON
        Button(
            onClick = {

                if (answered) {

                    if (currentIndex < questions.lastIndex) {
                        currentIndex++
                        answered = false
                        feedback = ""
                    } else {
                        quizFinished = true
                    }
                }

            },
            colors = ButtonDefaults.buttonColors(containerColor = darkOrange)
        ) {
            Text("Next", color = white)
        }

        Spacer(modifier = Modifier.height(20.dp))

        // SCORE DISPLAY (7/10 FORMAT STYLE)
        Text(
            text = "Score: $score / ${questions.size}",
            fontSize = 18.sp,
            fontWeight = FontWeight.Bold,
            color = darkOrange
        )

        // FINAL SCREEN
        if (quizFinished) {

            Spacer(modifier = Modifier.height(30.dp))

            val resultText = if (score >= 3) {
                "Master Hacker!"
            } else {
                "Keep Practicing!"
            }

            Text(
                text = resultText,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = darkOrange
            )

            Spacer(modifier = Modifier.height(20.dp))

            // RESTART BUTTON
            Button(
                onClick = onRestart,
                colors = ButtonDefaults.buttonColors(containerColor = darkOrange)
            ) {
                Text("Restart", color = white)
            }

            // REVIEW BUTTON
            val context = LocalContext.current

            Button(
                onClick = {
                    val intent = Intent(context, ReviewActivity::class.java)
                    context.startActivity(intent)
                },
                colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
                modifier = Modifier.padding(top = 10.dp)
            ) {
                Text("Review Answers", color = white)
            }
        }
    }
}    