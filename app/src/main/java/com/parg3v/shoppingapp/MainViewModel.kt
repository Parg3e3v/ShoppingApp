package com.parg3v.shoppingapp

import androidx.lifecycle.ViewModel
import com.parg3v.domain.repository.MyRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MyRepository
): ViewModel() {
}