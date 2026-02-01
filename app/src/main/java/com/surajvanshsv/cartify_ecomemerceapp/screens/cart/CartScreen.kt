package com.surajvanshsv.cartify_ecomemerceapp.screens.cart

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.surajvanshsv.cartify_ecomemerceapp.model.Product

@Composable
fun CartScreen(
    navController: NavController
){

    val cartItems = listOf(
        Product("1","Smartphone",99.99,"https://www.livemint.com/lm-img/img/2023/10/13/1600x900/smartphones_1697191534268_1697191552490.jpg"),
        )


    Column(
        modifier = Modifier.fillMaxSize().padding( 16.dp)
    ) {
        Text(
            text = "Your Cart",
            style = MaterialTheme.typography.headlineMedium,
            modifier = Modifier.padding(bottom = 16.dp)
        )

        if(cartItems.isEmpty()){
            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(
                    text = "Your Cart is Empty",
                    style = MaterialTheme.typography.bodyLarge
                )

                Spacer(modifier = Modifier.height(16.dp))

                Button(
                    onClick = { /* Handle button click */ }
                ) {
                    Text(
                        text = "continue shopping"
                    )
                }

            }
        } else {
            LazyColumn(
                modifier = Modifier.weight(1f)

            ) {
                items(cartItems){ item ->
                    CartItemCard(
                        item,
                        onRemoveItems = { /* Handle remove item */ }
                    )
                }
            }
        }



        // total anc checkout section
        Column(
            modifier = Modifier.fillMaxWidth()
                .padding(top = 16.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceEvenly
            ) {
                Text(
                    text = "Total : ",
                    style = MaterialTheme.typography.titleMedium

                )
                // call the viewmodel to get the total price .....
                Text(
                    text = "$...",
                    style = MaterialTheme.typography.titleMedium
                    , fontWeight = FontWeight.Bold
                )


                Spacer(Modifier.height(16.dp))
                Button(
                    onClick = { /* Handle checkout */ },
                    modifier = Modifier.fillMaxWidth()
                        .height(50.dp)
                ) {

                    Text(
                        "Proceed to checkout"
                    )

                }




            }

        }

    }
}