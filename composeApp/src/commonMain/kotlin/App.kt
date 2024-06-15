import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import data_layer.network.Constants.BASE_URL
import data_layer.network.Network.httpClient
import io.ktor.client.request.get
import io.ktor.client.statement.bodyAsText
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.jetbrains.compose.ui.tooling.preview.Preview
import presentation_layer.navigation.bottombar.BottomBarScreen
import presentation_layer.settings.ProfileScreen

@Composable
@Preview
fun App() {
    MaterialTheme {
        Navigator(screen = MainScreen()) { navigator ->
            // Transitions
            FadeTransition(navigator = navigator)
//            ScaleTransition(navigator = navigator)
//            SlideTransition(navigator = navigator)
        }
    }
}

class MainScreen : Screen {

    @Composable
    override fun Content() {

        // Access to Navigator
        val navigator = LocalNavigator.current
        var apiResponse by remember { mutableStateOf("") }

        Column(Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally) {
            Button(
                onClick = {
                    navigator?.push(item = HomeScreen())
                }
            ) {
                Text("Click me!")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navigator?.push(item = BottomBarScreen())
                }
            ) {
                Text("BottomBar")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    navigator?.push(item = ProfileScreen())
                }
            ) {
                Text("Persistence navigation")
            }

            Spacer(modifier = Modifier.height(16.dp))

            Button(
                onClick = {
                    CoroutineScope(Dispatchers.IO).launch {
                        try {
                            val test = httpClient.get(BASE_URL)
                            val responseText = test.bodyAsText()

                            // Update the state on the main thread
                            withContext(Dispatchers.Main) {
                                apiResponse = responseText
                            }
                        } catch (e: Exception) {
                            withContext(Dispatchers.Main) {
                                apiResponse = "Error: ${e.message}"
                            }
                        }
                    }
                }
            ) {
                Text(
                    text = "API"
                )
            }
            Text(
                text = apiResponse
            )
        }
    }
}

class HomeScreen : Screen {

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color.Black),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(
                text = "Home Screen",
                fontSize = 22.sp,
                color = Color.White
            )
            Spacer(modifier = Modifier.height(16.dp))
            Button(
                onClick = {
                    navigator?.pop()
                }
            ) {
                Text(
                    text = "Volver"
                )
            }
        }
    }
}