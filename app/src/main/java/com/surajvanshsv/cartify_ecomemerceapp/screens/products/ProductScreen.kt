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
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.surajvanshsv.cartify_ecomemerceapp.model.Product
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens

@Composable
fun ProductScreen(
    categoryId : String,
    navController: NavController
){

    // fetch products from view model
    // dummy products
    val products : List<Product> = listOf(
        Product("1","Smartphone",99.99,"https://www.livemint.com/lm-img/img/2023/10/13/1600x900/smartphones_1697191534268_1697191552490.jpg"),
        Product("2","Laptop",1299.99,"https://images.indianexpress.com/2020/10/Asus-ROG.jpg?w=1200")
    )

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