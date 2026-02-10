package com.surajvanshsv.cartify_ecomemerceapp.screens.home

import android.annotation.SuppressLint
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusRequester
import androidx.compose.ui.focus.focusRequester
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavController
import com.surajvanshsv.cartify_ecomemerceapp.screens.navigation.Screens
import com.surajvanshsv.cartify_ecomemerceapp.screens.products.ProductItem
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.CartViewModel
import com.surajvanshsv.cartify_ecomemerceapp.viewmodels.SearchViewModel

@Composable
fun SearchBar( // search bar UI , this params is for text field logic and control of search button
    query: String,
    onQueryChange: (String) -> Unit,
    onSearch: () -> Unit,
    modifier: Modifier = Modifier
) {
    // focus requester so clicking anywhere on the bar focuses the text field
    val focusRequester = remember { FocusRequester() }

    Box( // full container UI of search bar
        modifier = modifier.height(50.dp)
            .clip(RoundedCornerShape(25.dp))
            .background(Color.LightGray.copy(alpha = 0.3f))
            // request focus when clicked anywhere in the box
            .clickable { focusRequester.requestFocus() },
        contentAlignment = Alignment.CenterStart
    ){
        Row( // contains icons and text field
            modifier=Modifier.fillMaxSize().padding(horizontal = 14.dp),
            verticalAlignment = Alignment.CenterVertically
        ){
            Icon(Icons.Default.Search,"search") // icon
            Spacer(Modifier.width(8.dp))
            TextField( // text field
                value = query,
                onValueChange = onQueryChange,
                singleLine = true,
                modifier=Modifier.fillMaxWidth().focusRequester(focusRequester),
                placeholder = { Text("Search...", color = Color.Gray, fontSize = 16.sp) },
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search
                ),
                keyboardActions = KeyboardActions(
                    onSearch = { onSearch() }
                ),
                colors = TextFieldDefaults.colors(
                        focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Transparent,
                    unfocusedIndicatorColor = Color.Transparent,
                    disabledIndicatorColor = Color.Transparent
                )
            )
                }

        }
    }


@SuppressLint("StateFlowValueCalledInComposition")
@Composable
fun SearchResultsSection(
    navController: NavController,
    searchViewModel: SearchViewModel = hiltViewModel()
){
    // remember the navController.currentBackStackEntry as key, then obtain the shared cart viewmodel scoped to the nav host
    val cartBackStackEntry = remember(navController.currentBackStackEntry) { navController.getBackStackEntry("root_graph") }
    val cartViewModel: CartViewModel = hiltViewModel(cartBackStackEntry)

    val searchResults = searchViewModel.searchResults.value
    val isSearching = searchViewModel.isSearching.value


    Column(modifier = Modifier.fillMaxWidth()) {
        Text(
            text = "Search Results",
            style = MaterialTheme.typography.titleMedium.copy(
                fontWeight = FontWeight.Bold
            ),
            modifier = Modifier.padding(horizontal = 16.dp, vertical = 16.dp)
        )

        if(isSearching){
            CircularProgressIndicator(
                modifier = Modifier.align(Alignment.CenterHorizontally)

            )
        } else {
            LazyColumn(
                modifier = Modifier.fillMaxWidth(),
                contentPadding = PaddingValues(16.dp)
            ) {
                items(searchResults.size){
                    index -> val product = searchResults[index]
                    ProductItem(
                        product = product,
                        onClick = {navController.navigate(Screens.ProductDetails.createRoute(product.id))},
                        onAddToCart = {cartViewModel.addToCart(product)}
                    )

                }
            }
        }


    }



}
