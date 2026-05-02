package com.example.hackormythapp

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            WelcomeScreen {
                startActivity(Intent(this, QuizActivity::class.java))
            }
        }
    }
}

/* 🟠 SAME ORANGE THEME (CONSISTENCY FOR MARKS) */
val darkOrange = Color(0xFFFF6F00)
val lightOrange = Color(0xFFFFE0B2)
val white = Color.White

@Composable
fun WelcomeScreen(onStartClick: () -> Unit) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightOrange)
            .padding(20.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        /* 🟠 SIMPLE DESIGN PATTERN (TOP DECOR BAR) */
        Box(
            modifier = Modifier
                .size(width = 200.dp, height = 10.dp)
                .background(darkOrange)
        )

        Spacer(modifier = Modifier.height(30.dp))

        /* TITLE */
        Text(
            text = "Hack or Myth",
            fontSize = 34.sp,
            fontWeight = FontWeight.Bold,
            color = darkOrange
        )

        Spacer(modifier = Modifier.height(10.dp))

        /* SUBTITLE */
        Text(
            text = "Test real life hacks vs internet myths",
            fontSize = 18.sp,
            color = darkOrange
        )

        Spacer(modifier = Modifier.height(40.dp))

        /* START BUTTON */
        Button(
            onClick = onStartClick,
            colors = ButtonDefaults.buttonColors(containerColor = darkOrange),
            modifier = Modifier
                .width(200.dp)
                .height(50.dp)
        ) {
            Text(
                text = "Start",
                fontSize = 18.sp,
                color = white
            )
        }

        Spacer(modifier = Modifier.height(30.dp))

        /* 🟠 SIMPLE DESIGN PATTERN (BOTTOM BAR) */
        Box(
            modifier = Modifier
                .size(width = 150.dp, height = 10.dp)
                .background(darkOrange)
        )
    }
}