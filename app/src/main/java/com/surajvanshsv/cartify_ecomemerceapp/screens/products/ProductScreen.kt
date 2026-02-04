package com.surajvanshsv.cartify_ecomemerceapp.screens.products

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.surajvanshsv.cartify_ecomemerceapp.model.Product
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.ProductViewModel

@Composable
fun ProductScreen(
    categoryId : String,
    navController: NavController,
    productViewModel: ProductViewModel = hiltViewModel()
){

    // fetch products from view model
    LaunchedEffect(categoryId) {
        productViewModel.fetchProducts(categoryId)
    }

    // collect the products from view model
    val  productState = productViewModel.products.collectAsState()
    val products = productState.value



    // display the products
    Column(
        modifier = Modifier.fillMaxSize()
    ) {
        Text(
            text = "Products of Category ID : $categoryId",
            style = MaterialTheme.typography.titleLarge,
            modifier = Modifier.padding(16.dp)
        )

        if(products.isEmpty()){
            Text(
                text = "No Products Found!",
                modifier = Modifier.padding(16.dp)
            )
        }else{
            LazyColumn(
                modifier = Modifier.fillMaxSize(),
                contentPadding = PaddingValues(8.dp)
            ) {

                items(products){ product ->
                    ProductItem(
                        product = product,
                        onClick = {
                            // navigate to product details screen with product id
                            navController.navigate(Screens.ProductDetails.createRoute(product.id))
                        },
                        onAddToCart = {
                           // add to cart logic
                            // room database insertion function
                        }
                        )

                }


            }
        }


    }
}