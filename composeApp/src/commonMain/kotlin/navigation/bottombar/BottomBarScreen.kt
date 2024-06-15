package navigation.bottombar

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import cafe.adriel.voyager.core.screen.Screen
import cafe.adriel.voyager.navigator.tab.CurrentTab
import cafe.adriel.voyager.navigator.tab.LocalTabNavigator
import cafe.adriel.voyager.navigator.tab.TabDisposable
import cafe.adriel.voyager.navigator.tab.TabNavigator

class BottomBarScreen : Screen {

    @Composable
    override fun Content() {
        TabNavigator(
            tab = HomeTab,
            tabDisposable = {
                TabDisposable(
                    it,
                    listOf(HomeTab, FavTab, ProfileTab)
                )
            }
        ) {
            Scaffold(
                topBar = {
                    TopAppBar(
                        title = {
                            Text(
                                text = it.current.options.title
                            )
                        }
                    )
                },
                bottomBar = {
                    BottomNavigation {
                        val tabNavigator = LocalTabNavigator.current

                        BottomNavigationItem(
                            selected = tabNavigator.current.key == HomeTab.key,
                            label = {
                                Text(
                                    text = HomeTab.options.title
                                )
                            },
                            icon = {
                                HomeTab.options.icon?.let { painter ->
                                    Icon(
                                        painter = painter,
                                        contentDescription = null
                                    )
                                }
                            },
                            onClick = { tabNavigator.current = HomeTab}
                        )

                        BottomNavigationItem(
                            selected = tabNavigator.current.key == FavTab.key,
                            label = {
                                Text(
                                    text = FavTab.options.title
                                )
                            },
                            icon = {
                                FavTab.options.icon?.let { painter ->
                                    Icon(
                                        painter = painter,
                                        contentDescription = null
                                    )
                                }
                            },
                            onClick = { tabNavigator.current = FavTab}
                        )

                        BottomNavigationItem(
                            selected = tabNavigator.current.key == ProfileTab.key,
                            label = {
                                Text(
                                    text = ProfileTab.options.title
                                )
                            },
                            icon = {
                                ProfileTab.options.icon?.let { painter ->
                                    Icon(
                                        painter = painter,
                                        contentDescription = null
                                    )
                                }
                            },
                            onClick = { tabNavigator.current = ProfileTab}
                        )
                    }
                },
                content = {
                    CurrentTab()
                }
            )
        }
    }
}