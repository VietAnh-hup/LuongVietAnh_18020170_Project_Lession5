package com.example.android.guesstheword.screens.score

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ScoreViewModel(finalScore : Int) : ViewModel() {
    private val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private  val _evenPlayAgain = MutableLiveData<Boolean>()
    val evenPlayAgain : LiveData<Boolean>
        get() = _evenPlayAgain

    init {
        Log.i("ScoreViewModel" , "Final score is $finalScore")
        _score.value = finalScore
        _evenPlayAgain.value = false
    }
    fun playAgain(){
        _evenPlayAgain.value = true
    }
    fun playAgainComeleted(){
        _evenPlayAgain.value = false
    }
}