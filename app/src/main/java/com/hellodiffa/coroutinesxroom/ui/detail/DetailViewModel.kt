package com.hellodiffa.coroutinesxroom.ui.detail

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hellodiffa.coroutinesxroom.common.Result
import com.hellodiffa.coroutinesxroom.data.PlayerRepository
import com.hellodiffa.coroutinesxroom.data.model.Player
import kotlinx.coroutines.*
import javax.inject.Inject
import javax.inject.Named

/*
* created by Diffa
*/

class DetailViewModel @Inject constructor(
    private val repository: PlayerRepository,
    @Named("IO") private val io: CoroutineDispatcher,
    @Named("MAIN") private val main: CoroutineDispatcher
) : ViewModel() {

    private val _player = MutableLiveData<Result<Player>>()
    val player: LiveData<Result<Player>> get() = _player

    private fun setResultPlayer(result: Result<Player>) {
        _player.postValue(result)
    }

    private val _isFavorite = MutableLiveData<Result<Boolean>>()
    val favorite: LiveData<Result<Boolean>> get() = _isFavorite

    private fun setResultFavorite(result: Result<Boolean>) {
        _isFavorite.postValue(result)
    }

    internal fun observePlayerByUUID(id: String) {
        viewModelScope.launch(main) {
            try {
                setResultPlayer(Result.loading<Player>())
                delay(1_500)
                val result = async(context = io) {
                    repository.observePlayerByUUID(id)
                }
                setResultPlayer(Result.success(result.await()))
            } catch (e: Throwable) {
                setResultPlayer(Result.error(e.message ?: "Unknown error"))
            }
        }
    }

    internal fun updateFavorite(player: Player) {
        viewModelScope.launch(main) {
            try {
                setResultFavorite(Result.loading())
                delay(1_500)
                val result = async(context = io) {
                    repository.updateFavoritePlayer(player)
                }
                result.await()
                setResultFavorite(Result.success(player.isFavorite))

            } catch (e: Throwable) {
                setResultFavorite(Result.error(e.message ?: "Unknown error"))
            }
        }
    }

    override fun onCleared() {
        super.onCleared()
        viewModelScope.cancel()
    }

}