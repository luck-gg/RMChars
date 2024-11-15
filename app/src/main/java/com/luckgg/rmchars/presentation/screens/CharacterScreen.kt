import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.material.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.luckgg.rmchars.domain.model.CharacterRM
import com.luckgg.rmchars.presentation.screens.CharacterViewModel

@Suppress("ktlint:standard:function-naming")
@Composable
fun CharacterScreen(viewModel: CharacterViewModel = hiltViewModel()) {
    val characters by viewModel.characters.collectAsState()
    val query by viewModel.query.collectAsState()

    Column(modifier = Modifier.fillMaxSize()) {
        SearchBar(query = query, onQueryChange = { viewModel.onQueryChange(it) })

        LazyColumn(modifier = Modifier.weight(1f)) {
            items(characters) { character ->
                CharacterRow(characterRM = character)
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
fun CharacterRow(characterRM: CharacterRM) {
    Row(
        modifier =
            Modifier
                .fillMaxWidth()
                .padding(8.dp),
    ) {
        Image(
            painter = rememberAsyncImagePainter(model = characterRM.image),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier =
                Modifier
                    .size(64.dp)
                    .padding(end = 8.dp),
        )
        Column {
            Text(
                text = characterRM.name,
                fontWeight = FontWeight.Bold,
            )
            Text(
                text = "ID: ${characterRM.id}",
                color = Color.Gray,
            )
            Text(
                text = "Status: ${characterRM.status}",
                color = Color.Red,
                fontWeight = FontWeight.Thin,
            )
        }
    }
}

@Suppress("ktlint:standard:function-naming")
@Preview(showBackground = true)
@Composable
fun CharacterRowPreview() {
    CharacterRow(
        characterRM =
            CharacterRM(
                id = 1,
                name = "Rick Sanchez",
                status = "Alive",
                species = "???",
                image = "https://rickandmortyapi.com/api/character/avatar/1.jpeg",
            ),
    )
}
