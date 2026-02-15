package com.surajvanshsv.cartify_ecomemerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.surajvanshsv.cartify_ecomemerceapp.screens.cart.CartScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.categories.CategoryScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.home.HomeScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.AppNavHost
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens
import com.surajvanshsv.cartify_ecomemerceapp.screens.products.ProductDetailsScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.products.ProductScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.LoginScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.ProfileScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.SignUpScreen
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.AuthViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            AppNavHost()

        }
    }
}
