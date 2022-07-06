package com.pujiarto.ppid

import android.content.res.Configuration.UI_MODE_NIGHT_YES
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ExpandLess
import androidx.compose.material.icons.filled.ExpandMore
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.pujiarto.ppid.ui.theme.PpidTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PpidTheme {
                MyApp()
            }
        }
    }
}

@Composable
fun MyApp() {
    var shouldShowSearchScreen by rememberSaveable { mutableStateOf(true) }

    if (shouldShowSearchScreen) {
        SearchScreen(onContinueClicked = { shouldShowSearchScreen = false })
    } else {
        DocumentLists()
    }
}

@Composable
fun SearchScreen(onContinueClicked: () -> Unit){
    var searchDocument by remember { mutableStateOf("doc")}

    Surface{
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            OutlinedTextField(
                value = searchDocument,
                onValueChange = {searchDocument = it },
                label = { Text("Search")}
            )
            Button(
                modifier = Modifier.padding(vertical = 24.dp),
                onClick = onContinueClicked
            ) {
               Text(text = "Search")
            }
        }
    }
}

@Composable
private fun DocumentLists(documentNames: List<String> = List(1000){"$it"}){
    LazyColumn(
        modifier = Modifier.padding(vertical = 4.dp)
    ){
        items(items = documentNames) { documentName ->
            DocumentList(documentName = documentName)
        }
    }
}

@Composable
private fun DocumentList(documentName: String) {
    Card(
        backgroundColor = MaterialTheme.colors.primary,
        modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp)
    ) {
        CardContent(documentName)
    }
}

@Composable
private fun CardContent(documentName: String) {
    var expanded by remember { mutableStateOf( false ) }

    Row(
        modifier = Modifier
            .padding(12.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )
            )
    ) {
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(12.dp)
        ) {
            Text(text = "hello")
            Text(
                text = documentName,
                style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.ExtraBold
                )
            )
            if (expanded) {
                Text(
                    text = "Composem ipsum color sit lazy " +
                            "padding theme elit, sed do bouncy. ".repeat(4)
                )
            }
        }
        IconButton(onClick = { expanded = !expanded }){
            Icon(
                imageVector = if (expanded) Icons.Filled.ExpandLess else Icons.Filled.ExpandMore,
                contentDescription = if (expanded) {
                    stringResource(R.string.show_less)
                } else {
                    stringResource(R.string.show_more)
                }
            )
        }
    }
}
@Preview(
    showBackground = true,
    widthDp = 320,
    uiMode = UI_MODE_NIGHT_YES,
    name = "DefaultPreviewDark"
)
@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun DefaultPreview() {
    PpidTheme {
        DocumentLists()
    }
}

@Preview(showBackground = true, widthDp = 320, heightDp = 320)
@Composable
fun OnboardingPreview() {
    PpidTheme {
        SearchScreen(onContinueClicked = {})
    }
}

