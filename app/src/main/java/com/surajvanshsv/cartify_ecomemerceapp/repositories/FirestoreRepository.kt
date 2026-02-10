package com.surajvanshsv.cartify_ecomemerceapp.repositories

import android.util.Log
import com.google.firebase.firestore.FirebaseFirestore
import com.surajvanshsv.cartify_ecomemerceapp.model.Category
import com.surajvanshsv.cartify_ecomemerceapp.model.Product
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import javax.inject.Inject

class FirestoreRepository @Inject constructor(
    private val firestore: FirebaseFirestore
) {

    fun getCategoriesFlow() : Flow<List<Category>> =
        callbackFlow{

            val listenerRegistration = firestore.collection("categories")
                .addSnapshotListener { snapshot, error ->
                    if(error != null){
                        println("Error fetching categories: ${error.message}")
                        return@addSnapshotListener
                    }

                    if(snapshot != null){
                        val categories = snapshot.toObjects(Category::class.java)
                        trySend(categories)

                    }
                }

            // close the flow when listener is no longer needed
            awaitClose {
                listenerRegistration.remove()
            }



    }


    suspend fun getProductByCategory(categoryId : String) : List<Product>{
        return try {
            val result = firestore.collection("products")
                .whereEqualTo("categoryId",categoryId)
                .get()
                .await()

            result.toObjects(Product::class.java)

        } catch (e:Exception){
            emptyList()
        }
    }


    suspend fun getPrdouctsById(productId : String) : Product? {
            return try {
                val result = firestore.collection("products")
                    .document(productId)
                    .get()
                    .await()

                result.toObject(Product::class.java)

            } catch (e:Exception){
                null
            }
    }



    suspend fun getAllProductsInFirestore() : List<Product>{
        return try {
            val allProducts = firestore.collection("products")
                .get()
                .await()
                .documents
                .mapNotNull {
                    it.toObject(Product::class.java)
                }
            allProducts

        } catch (e:Exception){
            emptyList()
        }
    }

    suspend fun searchProducts(query: String): List<Product> {
        return try {
            // convert query to lowercase for case-insensitive search
            val lowercaseQuery = query.lowercase()

            // get all products and filter locally
            val allProducts = firestore.collection("products")
                .get()
                .await()
                .documents
                .mapNotNull {
                    it.toObject(Product::class.java)
                }
            allProducts.filter {product ->
                product.name.lowercase().contains(lowercaseQuery)

            }
        } catch (e: Exception) {
            Log.e("FirestoreRepository", "Error searching products: ${e.message}")
            emptyList()
        }


    }








}

