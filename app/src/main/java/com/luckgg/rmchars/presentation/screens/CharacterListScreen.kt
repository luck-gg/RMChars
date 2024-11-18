import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import com.luckgg.rmchars.domain.model.CharacterRM
import com.luckgg.rmchars.presentation.components.LoadingIndicator
import com.luckgg.rmchars.presentation.screens.CharacterViewModel
import com.luckgg.rmchars.presentation.screens.Screen

@OptIn(ExperimentalMaterial3Api::class)
@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterScreen(
    navController: NavController,
    viewModel: CharacterViewModel = hiltViewModel(),
) {
    val query by viewModel.query.collectAsState()
    val charactersLazyItems: LazyPagingItems<CharacterRM> =
        viewModel.characterUiState.collectAsLazyPagingItems()

    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.background(Color.Cyan),
                title = { Text("Rick and Morty Characters") },
            )
        },
    ) { innerPadding ->
        Column(
            modifier =
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding),
        ) {
            SearchBar(
                query = query,
                onQueryChange = { viewModel.onQueryChange(it) },
                onSearchConfirm = { viewModel.fetchCharacters() },
            )

            Box(
                modifier = Modifier.fillMaxSize(),
                contentAlignment = Alignment.Center,
            ) {
                LoadingIndicator(charactersLazyItems.itemCount == 0)

                LazyColumn(
                    state = rememberLazyListState(),
                    modifier = Modifier.fillMaxSize(1f),
                ) {
                    items(
                        count = charactersLazyItems.itemCount,
                        key = charactersLazyItems.itemKey(),
                        contentType = charactersLazyItems.itemContentType(),
                    ) { index ->
                        charactersLazyItems[index]?.let { character ->
                            CharacterRow(character) {
                                navController.currentBackStackEntry?.savedStateHandle?.set(
                                    "character",
                                    character,
                                )
                                navController.navigate(
                                    Screen.CharacterDetailScreen.route,
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
    onSearchConfirm: (String) -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        TextField(
            value = query,
            onValueChange = onQueryChange,
            modifier = Modifier.weight(1f),
            label = { Text("Search characters") },
            singleLine = true,
            keyboardActions = KeyboardActions(onSearch = { onSearchConfirm(query) }),
            trailingIcon = {
                if (query.isNotEmpty()) {
                    IconButton(onClick = {
                        onQueryChange("")
                        onSearchConfirm(query)
                    }) {
                        Icon(Icons.Filled.Clear, contentDescription = "Clear")
                    }
                }
            },
        )
        Spacer(modifier = Modifier.width(8.dp))
        Button(onClick = { onSearchConfirm(query) }) {
            Text("Search")
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterRow(
    character: CharacterRM,
    onCharacterClick: () -> Unit,
) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .clickable { onCharacterClick() }
                .padding(16.dp)
                .animateContentSize(),
        verticalAlignment = Alignment.CenterVertically,
        horizontalArrangement = Arrangement.SpaceBetween,
    ) {
        Text(text = character.name, style = MaterialTheme.typography.headlineSmall)
        Text(text = "ID: ${character.id}", style = MaterialTheme.typography.bodyLarge)
    }
}

// @Suppress("ktlint:standard:function-naming")
// @Preview(showBackground = true)
// @Composable
// fun CharacterRowPreview() {
//    CharacterRow(
//        CharacterRM(
//            id = 1,
//            name = "Rick Sanchez",
//            status = "Alive",
//            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
//            gender = "Male",
//            species = "Human",
//            locationOrigin = "Earth (C-137)",
//            locationCurrent = "Citadel of Ricks",
//            created = "",
//            url = "",
//        ),
//    )
// }
