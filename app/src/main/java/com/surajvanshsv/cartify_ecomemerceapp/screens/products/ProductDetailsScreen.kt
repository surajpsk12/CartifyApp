package com.surajvanshsv.cartify_ecomemerceapp.screens.products

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil3.compose.rememberAsyncImagePainter
import com.surajvanshsv.cartify_ecomemerceapp.model.Product

@Composable
fun ProductDetailsScreen(
    productId : String
){
// fetch products details when screen is first displayed
    // collect the products details from the viewmodel

    val myDummyProduct =Product("1","Smartphone",99.99,"https://www.livemint.com/lm-img/img/2023/10/13/1600x900/smartphones_1697191534268_1697191552490.jpg")

    if(myDummyProduct == null){
        Text(
            text = "Product not found"
        )
    } else {
        Column(
            modifier = Modifier.fillMaxSize().padding(16.dp)
        ) {
            Image(
                painter = rememberAsyncImagePainter(myDummyProduct.imageUrl),
                contentDescription = myDummyProduct.name,
                contentScale = ContentScale.Crop,
                modifier = Modifier.fillMaxWidth()
                    .height(300.dp)
                    .clip(RoundedCornerShape(12.dp))
            )

            Spacer(modifier = Modifier.height(16.dp))

            // product name and price
            Text(
                text = myDummyProduct.name,
                style = MaterialTheme.typography.titleLarge,
                fontWeight = FontWeight.Bold
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "$${myDummyProduct.price}",
                style = MaterialTheme.typography.titleMedium,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary
            )

            Spacer(modifier = Modifier.height(16.dp))

            // product description
            Text(
                text = myDummyProduct.categoryId?: "No Description Available",
                style = MaterialTheme.typography.bodyMedium,
                fontWeight = FontWeight.Bold
            )

            IconButton(
                onClick = { /* add to cart logic */ },
                modifier = Modifier.padding(16.dp)
                    .background(color = MaterialTheme.colorScheme.primary,
                shape = CircleShape)
            ) {
                Icon(
                    imageVector = Icons.Default.ShoppingCart,
                    contentDescription = "Add to Cart",
                    tint = Color.White
                )

            }

        }
    }


}