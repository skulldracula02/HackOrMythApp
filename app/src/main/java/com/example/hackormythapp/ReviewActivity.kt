package com.example.hackormythapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

class ReviewActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContent {
            ReviewScreen()
        }
    }
}

/* ---------------- SIMPLE DATA MODEL ---------------- */

data class ReviewItem(
    val question: String,
    val answer: String
)

/* ---------------- SAMPLE REVIEW DATA ---------------- */
/*
IMPORTANT:
These MUST match your QuizActivity questions manually.
*/

val reviewList = listOf(
    ReviewItem(
        "Drinking water improves concentration",
        "Correct Answer: TRUE (Hack)"
    ),
    ReviewItem(
        "Putting ice on burns is safe",
        "Correct Answer: FALSE (Myth)"
    ),
    ReviewItem(
        "Reading improves memory",
        "Correct Answer: TRUE (Hack)"
    ),
    ReviewItem(
        "You should wash hands after eating only",
        "Correct Answer: FALSE (Myth)"
    )
)

/* ---------------- UI ---------------- */

@Composable
fun ReviewScreen() {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(lightOrange) // theme background
            .padding(16.dp)
    ) {

        Text(
            text = "Review Answers",
            style = MaterialTheme.typography.headlineMedium,
            color = darkOrange // theme text color
        )

        Spacer(modifier = Modifier.height(10.dp))

        LazyColumn {
            items(reviewList) { item ->

                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    colors = CardDefaults.cardColors(
                        containerColor = white // card background
                    )
                ) {

                    Column(modifier = Modifier.padding(12.dp)) {

                        Text(
                            text = item.question,
                            color = darkOrange
                        )

                        Spacer(modifier = Modifier.height(5.dp))

                        Text(
                            text = item.answer,
                            color = darkOrange
                        )
                    }
                }
            }
        }
    }
}