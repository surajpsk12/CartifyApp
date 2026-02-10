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
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.surajvanshsv.cartify_ecomemerceapp.model.Category
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.CategoryViewModel
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.ProductDetailsViewModel

@Composable
fun CategoryScreen(
    navController: NavController,
    onCartClick : ()-> Unit,
    onProfileClick : ()-> Unit,
    categoriesViewModel: CategoryViewModel = hiltViewModel()

){

    // data from view model

    val categoriesState = categoriesViewModel.categories.collectAsState()
    val categories = categoriesState.value

    // display the categories

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