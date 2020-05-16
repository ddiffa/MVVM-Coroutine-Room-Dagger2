package com.hellodiffa.coroutinesxroom.ui.main

import androidx.lifecycle.ViewModel
import com.hellodiffa.coroutinesxroom.data.PlayerRepository
import javax.inject.Inject

class MainViewModel @Inject constructor(
    private val repository: PlayerRepository
) : ViewModel() {


    val player by lazy {
        repository.observePlayer()
    }

}