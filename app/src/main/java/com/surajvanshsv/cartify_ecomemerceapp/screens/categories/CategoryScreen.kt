package com.surajvanshsv.cartify_ecomemerceapp.screens.categories

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.surajvanshsv.cartify_ecomemerceapp.model.Category
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens

@Composable
fun CategoryScreen(
    navController: NavController,

){
    // dummy data
    val categories : List<Category> = listOf(
        Category(
            1,
            "Electronic",
            "https://thumbs.dreamstime.com/b/electronic-icon-circuit-177078270.jpg"
        ),
        Category(2,"Clothing","https://static.vecteezy.com/system/resources/previews/063/023/433/non_2x/change-clothes-icon-symbol-isolated-white-background-illustration-color-editable-vector.jpg"),
        Category(3,"Mobile","https://cdn.vectorstock.com/i/1000v/03/94/smart-phone-mobile-icon-outline-in-black-vector-31300394.jpg"))

    Column(

    ) {
        if(categories.isEmpty()){
            Box(modifier = Modifier.fillMaxSize().padding(16.dp),
                contentAlignment = Alignment.Center){
                Text(
                    text = "No Categories Found",
                    style = MaterialTheme.typography.headlineMedium
                )
            }

        } else {
            // categories title
            Text(
                text = "Categories",
                style = MaterialTheme.typography.titleLarge,
                modifier = Modifier.padding(vertical = 8.dp, horizontal = 16.dp)
            )

            // categories grid
            LazyVerticalGrid(
                    columns = GridCells.Fixed(3),
                contentPadding = PaddingValues(8.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp),
                horizontalArrangement = Arrangement.spacedBy(12.dp),
                modifier = Modifier.fillMaxWidth().padding(horizontal = 8.dp)
            ){

                items(categories){ category->
                    CategoryItem(
                        category = category,
                        onClick = {
                            navController.navigate(
                                Screens.ProductList.createRoute(category.id.toString())
                            )
                        }
                    )

                }
            }
        }
    }


}