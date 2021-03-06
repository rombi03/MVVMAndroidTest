package com.romanbialek.mvvmtest.presentation.characterlist

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.romanbialek.mvvmtest.domain.usecase.GetCharactersUseCase
import com.romanbialek.mvvmtest.domain.model.Character
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject


@HiltViewModel
class CharactersListViewModel @Inject constructor(private val getCharactersListUseCase: GetCharactersUseCase) : ViewModel() {

    private val TAG = CharactersListViewModel::class.java.simpleName
    val isLoadFinished = MutableLiveData<Boolean>()
    val charactersReceivedLiveData = MutableLiveData<List<Character>>()
    val errorLiveData = MutableLiveData<Throwable>()

    init {
        isLoadFinished.value = false
    }

    fun getCharacters(offset: Int) {
        getCharactersListUseCase.setOffset(offset)
        getCharactersListUseCase.execute(
            onSuccess = {
                isLoadFinished.value = true
                if(it.data.characters != null) {
                    charactersReceivedLiveData.value = it.data.characters!!
                }
            },
            onError = {
                it.printStackTrace()
                errorLiveData.value = it
            }
        )
    }

}