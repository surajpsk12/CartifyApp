package com.surajvanshsv.cartify_ecomemerceapp.screens.home

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopAppBar(
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit
) {
    TopAppBar( // top app bar
        title = { Text( // title of  top app bar
            text = "Cartify",
            style = MaterialTheme.typography.titleLarge.copy(
                color = MaterialTheme.colorScheme.onPrimary,
                fontWeight = FontWeight.Bold
            )
        ) },
        colors = TopAppBarDefaults.topAppBarColors( // color of top app bar
            containerColor = Color(0xff1565c0),
            titleContentColor = Color.White
        ),
        actions = { // buttons of top app bar
            IconButton(onClick = {onCartClick()}) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Cart",
                    tint = Color.White
                )

            }
            IconButton(onClick = {onProfileClick()}){
                Icon(
                    imageVector = Icons.Default.AccountCircle,
                    contentDescription = "Profile",
                    tint = Color.White
                )
            }
        }




    )
}