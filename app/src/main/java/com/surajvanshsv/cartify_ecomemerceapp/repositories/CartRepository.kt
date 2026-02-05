package com.surajvanshsv.cartify_ecomemerceapp.repositories

import android.util.Log
import com.surajvanshsv.cartify_ecomemerceapp.model.Product
import com.surajvanshsv.cartify_ecomemerceapp.room.CartDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CartRepository @Inject constructor(
    private val cartDao: CartDao
) {

    val allCartItems : Flow<List<Product>> = cartDao.getAllCartItems()

    suspend fun addtoCart(product: Product){
        val existingItem = cartDao.getCartItemById(product.id)

        if(existingItem != null){
            cartDao.updateCartItem(product)
            Log.v("tagy","Product Updated")
        }else{
            cartDao.insertCartItem(product)
            Log.v("tagy","Product Added")
        }
    }
    suspend fun removeCartItem(product: Product){
        cartDao.deleteCartItem(product)
        Log.v("tagy","Product Removed")

    }

    suspend fun clearCart(){
        cartDao.clearCart()
        Log.v("tagy","Cart Cleared")

    }

    

}





