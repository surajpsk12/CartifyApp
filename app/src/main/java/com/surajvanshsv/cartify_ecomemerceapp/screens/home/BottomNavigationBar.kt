package com.surajvanshsv.cartify_ecomemerceapp.screens.home

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Badge
import androidx.compose.material3.BadgedBox
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens

@Composable
fun BottonNavigationBar(
    navController: NavController
) {

    val currentRoute = ""


    val items = listOf( // list of bottom navigation items
        BottomNavigationItem(
            title = "Home",
            icon = Icons.Default.Home,
            route = Screens.Home.route

        ),
        BottomNavigationItem(
            title = "Categories",
            icon = Icons.Default.Search,
            route = Screens.Categories.route


        ),

        BottomNavigationItem(
            title = "Wishlist",
            icon = Icons.Default.Favorite,
            route = Screens.Cart.route,
            badgeCount = 5

        ),
        BottomNavigationItem(
            title = "Cart",
            icon = Icons.Default.ShoppingCart,
            route = Screens.Cart.route,
            badgeCount = 3

        ),
        BottomNavigationItem(
            title = "Profile",
            icon = Icons.Default.Person,
            route = Screens.Profile.route,


        )

    ) // end of list

    NavigationBar(
        modifier = Modifier.height(82.dp),
        containerColor = Color.White,
        tonalElevation = 8.dp
    ) {

        // converting the current nav back stack entry into a state object
        val navBackStackEntry by navController.currentBackStackEntryAsState()
        // getting the route of the current destination
        val currentRoute = navBackStackEntry?.destination?.route


        items.forEach { item ->
            NavigationBarItem(
                icon = {
                    // Check if there is a badge count
                    if (item.badgeCount > 0) {
                        BadgedBox( // This is the badge UI , badge is placed on the icon of bottom navigation item showing number or alert type
                            badge = {
                                // This is the small badge UI
                                Badge {
                                    Text(text = item.badgeCount.toString())
                                }
                            }
                        ) {
                            // This is the main Icon that the badge sits on top of
                            Icon(
                                imageVector = item.icon,
                                contentDescription = item.title,
                                modifier = Modifier.size(24.dp)
                            )
                        }
                    } else {
                        // Icon WITHOUT a badge
                        Icon(
                            imageVector = item.icon,
                            contentDescription = item.title,
                            modifier = Modifier.size(24.dp)
                        )
                    }
                },
                label = { Text(text = item.title) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route){
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                alwaysShowLabel = true
            )

        }
    }

}
data class BottomNavigationItem(
    val title: String,
    val icon: ImageVector,
    val route: String,
    val badgeCount : Int = 0
)