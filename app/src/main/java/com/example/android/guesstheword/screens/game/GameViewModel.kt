/*
 * Copyright 2018, The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.example.android.guesstheword.screens.game

import android.os.CountDownTimer
import android.text.format.DateUtils
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.Transformations
import androidx.lifecycle.ViewModel
import androidx.navigation.fragment.NavHostFragment

class GameViewModel : ViewModel(){

    companion object {
        // These represent different important times
        // This is when the game is over
        const val DONE = 0L
        // This is the number of milliseconds in a second
        const val ONE_SECOND = 1000L
        // This is the total time of the game
        const val COUNTDOWN_TIME = 10000L
    }

    private  val timer: CountDownTimer


    private val _word = MutableLiveData<String>()
    val word : LiveData<String>
        get() = _word

    // The current score
    private  val _score = MutableLiveData<Int>()
    val score : LiveData<Int>
        get() = _score

    private  val _evenGameFinish = MutableLiveData<Boolean>()
    val evenGameFinish : LiveData<Boolean>
        get() = _evenGameFinish
    private lateinit var wordList: MutableList<String>

    private val _currentTime = MutableLiveData<Long>()
    val currentTimer :  LiveData<Long>
        get() = _currentTime
    val currentTimeString = Transformations.map(currentTimer) { time ->
        DateUtils.formatElapsedTime(time)
    }


    init {
        resetList()
        nextWord()
        Log.i("GameViewModel", "GameViewModel Created")
        _score.value = 0
        _evenGameFinish.value = false
        _currentTime.value = 10

        timer = object : CountDownTimer(COUNTDOWN_TIME , ONE_SECOND){
            override fun onTick(millisUntilFinished: Long) {
                _currentTime.value = (millisUntilFinished/ ONE_SECOND)
                // TODO implement what should happen each tick of the timer
            }

            override fun onFinish() {
                Log.i("ER" ,"EROR")
                _evenGameFinish.value = true
                _currentTime.value = DONE
                // TODO implement what should happen when the timer finishes
            }

        }
        timer.start()

    }

    override fun onCleared() {
        super.onCleared()
        Log.i("GameViewModel" , "GameViewModel Cleared")
        timer.cancel()
    }

    private fun resetList() {
        wordList = mutableListOf(
                "queen",
                "hospital",
                "basketball",
                "cat",
                "change",
                "snail",
                "soup",
                "calendar",
                "sad",
                "desk",
                "guitar",
                "home",
                "railway",
                "zebra",
                "jelly",
                "car",
                "crow",
                "trade",
                "bag",
                "roll",
                "bubble"
        )
        wordList.shuffle()
    }

    /**
     * Called when the game is finished
     */


    /**
     * Moves to the next word in the list
     */
    private fun nextWord() {
        //Select and remove a word from the list
        if (wordList.isEmpty()) {
        //    gameFinished()
           // _evenGameFinish.value = true
            resetList()
        }
        _word.value = wordList.removeAt(0)
//        updateWordText()
//        updateScoreText()
    }

    fun onSkip() {
        _score.value = score.value?.minus(1)
        nextWord()
    }

    fun onCorrect() {
        _score.value = score.value?.plus(1)
        nextWord()
    }

    fun onGameFinishComplete(){
        _evenGameFinish.value = false
    }



}

// TODO (02) Create the GameViewModel class, extending ViewModel
// TODO (03) Add init and override onCleared; Add log statements to both