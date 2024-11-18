package com.luckgg.rmchars.presentation.screens

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberAsyncImagePainter
import com.luckgg.rmchars.domain.model.CharacterRM

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterDetailsScreen(navController: NavController) {
    val character =
        navController.previousBackStackEntry?.savedStateHandle?.get<CharacterRM>("character")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Character Details") },
                navigationIcon = {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Back")
                    }
                },
            )
        },
    ) { innerPadding ->
        character?.let {
            Column(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
                horizontalAlignment = Alignment.CenterHorizontally,
            ) {
                Image(
                    painter = rememberAsyncImagePainter(model = it.image),
                    contentDescription = it.name,
                    modifier =
                        Modifier
                            .size(200.dp)
                            .padding(16.dp),
                )

                Text(
                    text = "${it.name} (ID: ${it.id})",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier = Modifier.padding(bottom = 8.dp),
                )

                // Rest of the details as secondary text
                CharacterDetailItem(label = "Status:", value = it.status)
                CharacterDetailItem(label = "Species:", value = it.species)
                CharacterDetailItem(label = "Gender:", value = it.gender)
                CharacterDetailItem(label = "Original From:", value = it.locationOrigin)
                CharacterDetailItem(label = "Current Location:", value = it.locationCurrent)
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterDetailItem(
    label: String,
    value: String,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 4.dp),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceEvenly,
    ) {
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            modifier =
                Modifier
                    .weight(0.4f)
                    .animateContentSize(),
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyLarge,
            modifier =
                Modifier
                    .weight(0.6f)
                    .animateContentSize(),
        )
    }
}
