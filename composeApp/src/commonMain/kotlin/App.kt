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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import cafe.adriel.voyager.navigator.Navigator
import cafe.adriel.voyager.transitions.FadeTransition
import navigation.bottombar.BottomBarScreen
import org.jetbrains.compose.ui.tooling.preview.Preview

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