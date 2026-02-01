package com.surajvanshsv.cartify_ecomemerceapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.surajvanshsv.cartify_ecomemerceapp.model.Category
import com.surajvanshsv.cartify_ecomemerceapp.model.Product

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    onProfileClick : ()-> Unit,
    onCartClick : ()-> Unit
){
    Scaffold(
        topBar = {MyTopAppBar(onProfileClick,onCartClick)},
        bottomBar = {BottonNavigationBar()}
    ) {paddingValues ->

        Column(
            modifier = Modifier.fillMaxSize().padding(paddingValues)
        ) {

            // search section
            val searchQuery = remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current

            SearchBar(
                query = searchQuery.value,
                onQueryChange = {searchQuery.value == it},
                onSearch = {
//                    do the search logic
                },
                modifier = Modifier.fillMaxWidth().padding(16.dp
                )

            )

            // search result section

            // categories section
            SectionTitle(
                "Categories",
                "See all"
            ) { navController.navigate("Categories")}

            Spacer(Modifier.height(16.dp))



            // mock the categories items

            val categories : List<Category> = listOf(
                Category(1,"Electronic","https://thumbs.dreamstime.com/b/electronic-icon-circuit-177078270.jpg"),
                Category(2,"Clothing","https://static.vecteezy.com/system/resources/previews/063/023/433/non_2x/change-clothes-icon-symbol-isolated-white-background-illustration-color-editable-vector.jpg"),
                Category(3,"Mobile","https://cdn.vectorstock.com/i/1000v/03/94/smart-phone-mobile-icon-outline-in-black-vector-31300394.jpg"))

            val selectedCategory = remember { mutableStateOf(0) }

            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(8.dp)
            ){
                items(categories.size){
                    CategoryChip(
                        icon = categories[it].iconUrl,
                        text = categories[it].name,
                        isSelected = selectedCategory.value == it,
                        onClick = {
                            selectedCategory.value == it
                            /* do the navigation logic here */
                        }
                    )
                }

            }

            Spacer(Modifier.height(16.dp))

            SectionTitle(
                title = "Featured",
                actionText = "See all",
            ) { /* add navigation */ }


            val productList : List<Product> = listOf(
                Product("1","Smartphone",99.99,"https://www.livemint.com/lm-img/img/2023/10/13/1600x900/smartphones_1697191534268_1697191552490.jpg"),
                Product("2","Laptop",1299.99,"https://images.indianexpress.com/2020/10/Asus-ROG.jpg?w=1200")
            )

            LazyRow(
                contentPadding = PaddingValues(16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)

            ) {
                items(productList){product ->
                    FeaturedProductCard(product) {
                        /*handle the click event  */
                    }

                }

            }



        }
    }
}