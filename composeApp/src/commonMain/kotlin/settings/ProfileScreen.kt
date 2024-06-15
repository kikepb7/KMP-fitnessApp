package settings

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Checkbox
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.LocalNavigator
import com.russhwolf.settings.Settings
import com.russhwolf.settings.set

class ProfileScreen : Screen {

    private val settings: Settings = Settings()

    companion object {
        const val KEY_NAME = "NAME"
        const val KEY_VIP = "VIP"
    }

    @Composable
    override fun Content() {
        val navigator = LocalNavigator.current
        var name by remember { mutableStateOf("") }
        var isVip by remember { mutableStateOf(false) }

        Column(
            modifier = Modifier
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.weight(1f))

            OutlinedTextField(
                value = name,
                onValueChange = {
                    name = it
                }
            )

            Row(
                modifier = Modifier
                    .clickable { isVip = !isVip },
                verticalAlignment = Alignment.CenterVertically
            ) {
                Checkbox(
                    checked = isVip,
                    onCheckedChange = {
                        isVip = it
                    }
                )

                Text(
                    text = "Vip?"
                )
            }

            Spacer(modifier = Modifier.weight(1f))

            Button(
                onClick = {
//                    settings.putString(key = KEY_NAME, value = name)
//                    settings.putBoolean(key = KEY_VIP, value = isVip)
                    settings[KEY_NAME] = name
                    settings[KEY_VIP] = isVip
                    navigator?.push(ProfileResultScreen())
                },
                enabled = name.isNotEmpty() // If is not empty is enabled and if is empty is disabled
            ) {
                Text(
                    text = "Save profile",
                    fontSize = 22.sp,
                    color = Color.White
                )
            }


            Spacer(modifier = Modifier.weight(0.3f))
        }
    }
}