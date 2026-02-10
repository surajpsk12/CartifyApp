package com.surajvanshsv.cartify_ecomemerceapp.screens.navigation

sealed class Screens(val route : String ){
    // defines screen routes for navigation in compose
    // each objects represents a specific screen in nav graph

    object Home : Screens("home")
    object Cart : Screens("Cart")

    object Categories : Screens("Categories")

    object ProductDetails : Screens("product_details/{productId}"){
        fun createRoute(productId : String) = "product_details/$productId"
    }


    object ProductList : Screens("product_list/{categoryId}"){
        fun createRoute(categoryId : String) = "product_list/$categoryId"
    }

    object CategoryList : Screens("category_list")

    // these three are for login , signup and profile screens
    object Login : Screens("Login")
    object SignUp : Screens("SignUp")
    object Profile : Screens("Profile")

}