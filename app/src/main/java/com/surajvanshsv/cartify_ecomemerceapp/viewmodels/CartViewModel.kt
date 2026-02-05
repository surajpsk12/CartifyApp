package com.surajvanshsv.cartify_ecomemerceapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajvanshsv.cartify_ecomemerceapp.model.Product
import com.surajvanshsv.cartify_ecomemerceapp.repositories.CartRepository
import kotlinx.coroutines.launch
import javax.inject.Inject

class CartViewModel @Inject constructor(
    private val repository: CartRepository
) : ViewModel() {
    val cartItems = repository.allCartItems

    fun addToCart(product: Product){
        viewModelScope.launch {
            repository.addtoCart(product)
        }
    }

    fun removeFromCart(cartItem: Product){
        viewModelScope.launch {
            repository.removeCartItem(cartItem)
        }
    }

    fun clearCart(){
        viewModelScope.launch {
            repository.clearCart()
        }
    }

    fun calculateTotal(items: List<Product>) : Double {
        return items.sumOf { it.price }
    }


}

