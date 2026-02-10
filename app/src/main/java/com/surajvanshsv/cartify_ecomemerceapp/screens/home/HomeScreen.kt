package com.surajvanshsv.cartify_ecomemerceapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.CategoryViewModel
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.ProductViewModel
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.SearchViewModel

@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(
    navController: NavController,
    onProfileClick: () -> Unit,
    onCartClick: () -> Unit,
    productViewModel: ProductViewModel = hiltViewModel(),
    categoryViewModel: CategoryViewModel = hiltViewModel(),
    searchViewModel: SearchViewModel = hiltViewModel()
) {
    Scaffold(
        topBar = { MyTopAppBar(onProfileClick, onCartClick) },
        bottomBar = { BottonNavigationBar(navController) },
        containerColor = Color(0xFFF8F9FA)
    ) { paddingValues ->

        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(Color(0xFFF8F9FA))
                .padding(paddingValues)
                .verticalScroll(rememberScrollState())
        ) {

            // Search section
            val searchQuery = remember { mutableStateOf("") }
            val focusManager = LocalFocusManager.current

            SearchBar(
                query = searchQuery.value,
                onQueryChange = { searchQuery.value = it },
                onSearch = {
                    searchViewModel.searchProducts(searchQuery.value)
                    focusManager.clearFocus()
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 12.dp)
            )

            // Search result section
            if (searchQuery.value.isNotBlank()) {
                SearchResultsSection(navController, searchViewModel)
            }

            Spacer(Modifier.height(8.dp))

            // Categories section
            SectionTitle(
                "Categories",
                "See all"
            ) { navController.navigate(Screens.CategoryList.route) }

            Spacer(Modifier.height(12.dp))

            // Mock the categories items
            val categoriesState = categoryViewModel.categories.collectAsState()
            val categories = categoriesState.value

            val selectedCategory = remember { mutableStateOf(0) }

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(10.dp)
            ) {
                items(categories.size) { index ->
                    CategoryChip(
                        icon = categories[index].iconUrl,
                        text = categories[index].name,
                        isSelected = selectedCategory.value == index,
                        onClick = {
                            selectedCategory.value = index
                            /* do the navigation logic here */
                            navController.navigate(
                                Screens.ProductList.createRoute(categoryId = categories[index].id.toString())
                            )
                        }
                    )
                }
            }

            Spacer(Modifier.height(24.dp))

            // Featured section
            SectionTitle(
                title = "Featured Products",
                actionText = "See all"
            ) {
                navController.navigate(Screens.CategoryList.route)
            }

            Spacer(Modifier.height(12.dp))

            // Fetch products when the screen is first displayed
            productViewModel.getAllProductsInFirestore()

            // Getting all products
            val allProductsState = productViewModel.allProducts.collectAsState()
            val allProductsFound = allProductsState.value

            LazyRow(
                contentPadding = PaddingValues(horizontal = 16.dp),
                horizontalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(allProductsFound) { product ->
                    FeaturedProductCard(product) {
                        /*handle the click event  */
                        navController.navigate(
                            Screens.ProductDetails.createRoute(productId = product.id)
                        )
                    }
                }
            }

            Spacer(Modifier.height(24.dp))
        }
    }
}