package com.example.hackormythapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WelcomeScreen {
                // Navigate to Quiz Page
                startActivity(Intent(this, QuizActivity::class.java))
            }
        }
    }
}

@Composable
fun WelcomeScreen(onStartClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightOrange) // from Theme.kt
            .padding(24.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        // Top design bar (simple pattern)
        Box(
            modifier = Modifier
                .width(200.dp)
                .height(10.dp)
                .background(darkOrange)
        )

        Spacer(modifier = Modifier.height(30.dp))

        // Title
        Text(
            text = "Hack or Myth",
            fontSize = 36.sp,
            fontWeight = FontWeight.Bold,
            color = darkOrange
        )

        Spacer(modifier = Modifier.height(10.dp))

        // Subtitle
        Text(
            text = "Test real-life hacks vs internet myths",
            fontSize = 18.sp,
            color = darkOrange
        )

        Spacer(modifier = Modifier.height(40.dp))

        // Start Button
        Button(
            onClick = onStartClick,
            colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
            modifier = Modifier
                .width(200.dp)
                .height(55.dp)
        ) {
            Text(
                text = "Play",
                fontSize = 18.sp,
                color = white
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        // Bottom design bar
        Box(
            modifier = Modifier
                .width(150.dp)
                .height(10.dp)
                .background(darkOrange)
        )
    }
}