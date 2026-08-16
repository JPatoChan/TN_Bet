package com.example.tn_bet.ui

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import coil.compose.AsyncImage
import com.example.tn_bet.data.Team

@Composable
fun TitansScreen(viewModel: TitansViewModel = viewModel()) {
    val uiState by viewModel.uiState.collectAsState()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        when (val state = uiState) {
            is TitansUiState.Loading -> CircularProgressIndicator(color = MaterialTheme.colorScheme.primary)
            is TitansUiState.Success -> TeamInfoDisplay(state.team)
            is TitansUiState.Error -> ErrorDisplay(state.message) { viewModel.fetchTitansData() }
        }
    }
}

@Composable
fun TeamInfoDisplay(team: Team) {
    val logoUrl = team.logos.firstOrNull()?.href

    AsyncImage(
        model = logoUrl,
        contentDescription = "${team.displayName} Logo",
        modifier = Modifier
            .size(200.dp)
            .padding(16.dp),
        contentScale = ContentScale.Fit
    )

    Spacer(modifier = Modifier.height(16.dp))

    Text(
        text = team.displayName,
        fontSize = 28.sp,
        fontWeight = FontWeight.Bold,
        color = MaterialTheme.colorScheme.primary
    )

    Text(
        text = team.location,
        fontSize = 20.sp,
        color = MaterialTheme.colorScheme.secondary
    )

    Spacer(modifier = Modifier.height(8.dp))

    team.record?.items?.firstOrNull()?.summary?.let { record ->
        Text(
            text = "Record: $record",
            fontSize = 18.sp,
            fontWeight = FontWeight.Medium
        )
    }
}

@Composable
fun ErrorDisplay(message: String, onRetry: () -> Unit) {
    Text(text = "Error: $message", color = Color.Red)
    Button(onClick = onRetry) {
        Text("Retry")
    }
}
