import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.paging.compose.LazyPagingItems
import androidx.paging.compose.collectAsLazyPagingItems
import androidx.paging.compose.itemContentType
import androidx.paging.compose.itemKey
import coil.compose.rememberAsyncImagePainter
import com.luckgg.rmchars.domain.model.CharacterRM
import com.luckgg.rmchars.presentation.screens.CharacterViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterScreen(viewModel: CharacterViewModel = hiltViewModel()) {
    val query by viewModel.query.collectAsState()
    val charactersLazyItems: LazyPagingItems<CharacterRM> = viewModel.characterFlow.collectAsLazyPagingItems()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(query = query, onQueryChange = { viewModel.onQueryChange(it) })

        LazyColumn(
            state = rememberLazyListState(),
            modifier = Modifier.weight(1f),
        ) {
            items(
                count = charactersLazyItems.itemCount,
                key = charactersLazyItems.itemKey(),
                contentType = charactersLazyItems.itemContentType(),
            ) { index ->
                charactersLazyItems[index]?.let { character ->
                    CharacterRow(character)
                }
                HorizontalDivider()
            }
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun SearchBar(
    query: String,
    onQueryChange: (String) -> Unit,
) {
    TextField(
        value = query,
        onValueChange = onQueryChange,
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        label = { Text("Search characters") },
        singleLine = true,
    )
}

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterRow(character: CharacterRM) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = character.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .size(64.dp)
                    .padding(end = 8.dp),
        )
        Spacer(modifier = Modifier.width(16.dp))
        Column {
            Text(text = "ID: ${character.id}", style = MaterialTheme.typography.bodyLarge)
            Text(text = character.name, style = MaterialTheme.typography.headlineSmall)
            Text(
                text = "Status: ${character.status}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Gender: ${character.gender}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Species: ${character.species}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Origin: ${character.locationOrigin}",
                style = MaterialTheme.typography.bodyMedium,
            )
            Text(
                text = "Location: ${character.locationCurrent}",
                style = MaterialTheme.typography.bodyMedium,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun CharacterRowPreview() {
    CharacterRow(
        CharacterRM(
            id = 1,
            name = "Rick Sanchez",
            status = "Alive",
            image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            gender = "Male",
            species = "Human",
            locationOrigin = "Earth (C-137)",
            locationCurrent = "Citadel of Ricks",
            created = "",
            url = "",
        ),
    )
}
