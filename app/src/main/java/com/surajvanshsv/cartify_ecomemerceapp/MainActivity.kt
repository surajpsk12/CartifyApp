package com.surajvanshsv.cartify_ecomemerceapp

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.surajvanshsv.cartify_ecomemerceapp.screens.cart.CartScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.categories.CategoryScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.home.HomeScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens
import com.surajvanshsv.cartify_ecomemerceapp.screens.products.ProductDetailsScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.products.ProductScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.LoginScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.ProfileScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.SignUpScreen

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            // navigation system
            val navController = rememberNavController()

            // Nav Host : manages navigation btw screens and their transitions
            NavHost(
                navController = navController,
                startDestination = "home"
            ){
                // defines routes using composable
                // for each screen you want to supports

                composable("home"){
                    HomeScreen(
                        navController = navController,
                        onProfileClick = {
                            navController.navigate(Screens.Home)
                        },
                        onCartClick = {
                            navController.navigate(Screens.Cart)
                        }
                    )
                }

                composable(Screens.Cart.route) {
                    CartScreen(navController = navController)
                }

                composable(Screens.Profile.route) {
                    ProfileScreen(
                        navController = navController,
                        onSignOut = {}
                    )
                }

                composable(Screens.Categories.route) {
                    CategoryScreen(navController = navController)
                }


                composable(Screens.ProductDetails.route) {
                    val productId = it.arguments?.getString("productId")
                    if(productId != null){
                        // show the product details screen
                        ProductDetailsScreen(productId)
                    }
                }

                composable(Screens.ProductList.route) {
                    val categoryId = it.arguments?.getString("categoryId")
                    if(categoryId != null){
                        // show the product list screen
                        ProductScreen(
                            categoryId,
                            navController
                        )
                    }
                }

                composable(Screens.CategoryList.route) {
                    // show the category list screen
                    // add the logic here
                    CategoryScreen(navController)
                }

                composable(Screens.SignUp.route) {
                    // show the sign up screen
                    // add the logic here
                    SignUpScreen(
                        onNavigateToLogin = {
                            navController.navigate(Screens.Login.route)
                        },
                        onSignUpSuccess = {
                            navController.navigate(Screens.Home.route)
                        }
                    )

                }
                composable(Screens.Login.route) {
                    LoginScreen(
                            onLoginSuccess = {
                            navController.navigate(Screens.Home.route)
                        },
                        onNavigateToSignUp = {
                            navController.navigate(Screens.SignUp.route)
                        }
                    )
                }


            }

        }
    }
}
