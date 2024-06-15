package presentation_layer.settings

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.russhwolf.settings.Settings
import presentation_layer.settings.ProfileScreen.Companion.KEY_NAME
import presentation_layer.settings.ProfileScreen.Companion.KEY_VIP

class ProfileResultScreen : Screen {

    private val settings : Settings = Settings()

    @Composable
    override fun Content() {

        val navigator = LocalNavigator.current
        // Recover the values
//        settings.getBooleanOrNull(KEY_VIP)
        val isVip = settings.getBoolean(key = KEY_VIP, defaultValue = false)
        val backgroundColor = if (isVip) Color.Yellow else Color.White


        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(color =  backgroundColor),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            val name = settings.getString(KEY_NAME, "")
            Text(
                text = "Welcome, $name!",
                fontSize = 26.sp,
                fontWeight = FontWeight.Bold
            )

            Button(
                onClick = {
//                    settings.remove(key = KEY_NAME)
//                    settings.remove(key = KEY_VIP)
                    settings.clear()    // Remove all key from db
                    navigator?.pop()
                }
            ) {
                Text(
                    text = "Go back and remove data"
                )
            }
        }
    }
}