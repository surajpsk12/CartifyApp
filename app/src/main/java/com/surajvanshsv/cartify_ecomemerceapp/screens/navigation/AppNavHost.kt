package com.surajvanshsv.cartify_ecomemerceapp.screens.navigation

import androidx.compose.runtime.Composable
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
import com.surajvanshsv.cartify_ecomemerceapp.screens.products.ProductDetailsScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.products.ProductScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.LoginScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.ProfileScreen
import com.surajvanshsv.cartify_ecomemerceapp.screens.profile.SignUpScreen
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.AuthViewModel

@Composable
fun AppNavHost(){
    // navigation system
    val navController = rememberNavController()

    // auth view model
    val authViewModel : AuthViewModel = hiltViewModel()

    // properly observe the state
    val isLoggedIn by remember {
        derivedStateOf {
            authViewModel.isLoggedIn
        }
    }



    // Nav Host : manages navigation btw screens and their transitions
    NavHost(
        navController = navController,
        startDestination = "home",
        route = "root_graph"
    ){
        // defines routes using composable
        // for each screen you want to supports


        composable("home"){
            HomeScreen(
                navController = navController,
                onProfileClick = {
                    navController.navigate(Screens.Profile.route)
                },
                onCartClick = {
                    navController.navigate(Screens.Cart.route)
                }
            )
        }

        composable(Screens.Cart.route) {
            CartScreen(navController = navController)
        }

        composable(Screens.Profile.route) {
            ProfileScreen(
                navController = navController,
                onSignOut = {
                    authViewModel.signOut()
                    navController.navigate(Screens.Login.route)

                }
            )
        }

        composable(Screens.Categories.route) {
            CategoryScreen(navController = navController,
                onCartClick = {
                    navController.navigate(Screens.Cart.route)
                },
                onProfileClick = {
                    if(isLoggedIn)navController.navigate(Screens.Profile.route)
                    else navController.navigate(Screens.Login.route)
                })
        }


        composable(Screens.ProductDetails.route) {
            val productId = it.arguments?.getString("productId")
            if(productId != null){
                // show the product details screen
                ProductDetailsScreen(productId, navController)
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

