package com.surajvanshsv.cartify_ecomemerceapp.viewmodels

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.google.firebase.firestore.FirebaseFirestore
import com.surajvanshsv.cartify_ecomemerceapp.model.Product
import com.surajvanshsv.cartify_ecomemerceapp.repositories.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class SearchViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel() {

    private val _searchResults = MutableStateFlow<List<Product>>(emptyList())
    val searchResults: MutableStateFlow<List<Product>> get() = _searchResults

    private val _isSearching = MutableStateFlow<Boolean>(false)
    val isSearching: MutableStateFlow<Boolean> get()  = _isSearching

    fun searchProducts(query: String) {

        if(query.isBlank()){
            _searchResults.value = emptyList()
            _isSearching.value = false
            return
        }
        _isSearching.value = true
        viewModelScope.launch {
            _searchResults.value = repository.searchProducts(query)
            _isSearching.value = false
        }



    }






}