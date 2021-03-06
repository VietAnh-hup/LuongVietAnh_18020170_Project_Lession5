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

import android.os.Bundle
import android.text.format.DateUtils
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.lifecycle.ViewModelProviders.of
import androidx.lifecycle.ViewModelStores.of
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.android.guesstheword.R
import com.example.android.guesstheword.databinding.GameFragmentBinding

/**
 * Fragment where the game is played
 */
class GameFragment : Fragment() {

    private  lateinit var viewModel: GameViewModel
    // The current word


    // The list of words - the front of the list is the next word to guess


    private lateinit var binding: GameFragmentBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
                inflater,
                R.layout.game_fragment,
                container,
                false
        )

        Log.i("GameFragment" , "ViewModelProviders.of  called")
        viewModel = ViewModelProviders.of(this).get(GameViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.setLifecycleOwner(this)
        // TODO (04) Create and initialize a GameViewModel, using ViewModelProvider; Add a log
        // statement



//        binding.correctButton.setOnClickListener {
//            viewModel.onCorrect() }
//        binding.skipButton.setOnClickListener { viewModel.onSkip() }

//        viewModel.score.observe(this, Observer { newScore ->
//            binding.scoreText.text = newScore.toString()
//        })
//
//        viewModel.word.observe(this, Observer { newWord ->
//            binding.wordText.text = newWord
//        })

        viewModel.evenGameFinish.observe(this, Observer { hasFinished ->
            if (hasFinished){
                val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController(this).navigate(action)
                viewModel.onGameFinishComplete()
                viewModel.onGameFinishComplete()
            }
        })
//        viewModel.currentTimer.observe(this, Observer { newTime ->
//            binding.timerText.text = DateUtils.formatElapsedTime(newTime)
//        })




        updateScoreText()
        updateWordText()



        return binding.root

    }

    /**
     * Resets the list of words and randomizes the order
     */


    /** Methods for buttons presses **/


    /** Methods for updating the UI **/

    private fun updateWordText() {
        binding.wordText.text = viewModel.word.value
    }

    private fun updateScoreText() {
        binding.scoreText.text = viewModel.score.value.toString()
    }

//    private fun gameFinished() {
//
//        val action = GameFragmentDirections.actionGameToScore(viewModel.score.value ?: 0)
//        NavHostFragment.findNavController(this).navigate(action)
//    }
}
