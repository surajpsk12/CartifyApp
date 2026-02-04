package com.surajvanshsv.cartify_ecomemerceapp.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.surajvanshsv.cartify_ecomemerceapp.model.Category
import com.surajvanshsv.cartify_ecomemerceapp.repositories.FirestoreRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject



@HiltViewModel
class CategoryViewModel @Inject constructor(
    private val repository: FirestoreRepository
) : ViewModel() {


    // encapsulate the state of the categories , mutable internally and immutable externally
    private val _categories = MutableStateFlow<List<Category>>(emptyList())
    val categories : StateFlow<List<Category>>  get() = _categories

    init {
        fetchCategories()
    }

    private fun fetchCategories(){
        viewModelScope.launch {
            repository.getCategoriesFlow()
                .catch {
                    //handle any error
                    println("Error in flow: ${it.message}")
                }

                .collect{
                    // each time new data is emitted this block run
                    categories->
                    // update stateflow with new data
                    _categories.value = categories
                    println("Categories updated in viewmodel")
                }
        }
    }
}

