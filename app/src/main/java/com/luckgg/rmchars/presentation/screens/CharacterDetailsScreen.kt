package com.luckgg.rmchars.presentation.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
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
            ConstraintLayout(
                modifier =
                    Modifier
                        .fillMaxSize()
                        .padding(innerPadding),
            ) {
                val (image, name, status, species, gender, origin, location) = createRefs()
                Image(
                    painter = rememberAsyncImagePainter(model = it.image),
                    contentDescription = it.name,
                    modifier =
                        Modifier
                            .size(200.dp)
                            .constrainAs(image) {
                                top.linkTo(parent.top, 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                )

                Text(
                    text = "${it.name} (ID: ${it.id})",
                    style = MaterialTheme.typography.headlineMedium,
                    modifier =
                        Modifier
                            .constrainAs(name) {
                                top.linkTo(image.bottom, 16.dp)
                                start.linkTo(parent.start)
                                end.linkTo(parent.end)
                            },
                )

                CharacterDetailItem(
                    label = "Status:",
                    value = it.status,
                    modifier =
                        Modifier.constrainAs(status) {
                            top.linkTo(name.bottom, 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
                CharacterDetailItem(
                    label = "Species:",
                    value = it.species,
                    modifier =
                        Modifier.constrainAs(species) {
                            top.linkTo(status.bottom, 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
                CharacterDetailItem(
                    label = "Gender:",
                    value = it.gender,
                    modifier =
                        Modifier.constrainAs(gender) {
                            top.linkTo(species.bottom, 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
                CharacterDetailItem(
                    label = "Original From:",
                    value = it.locationOrigin,
                    modifier =
                        Modifier.constrainAs(origin) {
                            top.linkTo(gender.bottom, 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
                CharacterDetailItem(
                    label = "Current Location:",
                    value = it.locationCurrent,
                    modifier =
                        Modifier.constrainAs(location) {
                            top.linkTo(origin.bottom, 8.dp)
                            start.linkTo(parent.start)
                            end.linkTo(parent.end)
                        },
                )
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterDetailItem(
    label: String,
    value: String,
    modifier: Modifier = Modifier,
) {
    ConstraintLayout(
        modifier = modifier,
    ) {
        val (title, description) = createRefs()
        Text(
            text = label,
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.primary,
            modifier =
                Modifier
                    .constrainAs(title) {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(description.start)
                    },
        )
        Text(
            text = value,
            style = MaterialTheme.typography.bodyMedium,
            color = MaterialTheme.colorScheme.onSurfaceVariant,
            modifier =
                Modifier
                    .constrainAs(description) {
                        top.linkTo(parent.top)
                        start.linkTo(title.end)
                        end.linkTo(parent.end)
                    },
        )
    }
}
