package com.corp.luqman.kotlintraining.lesson5.screens.game

import android.content.Context
import android.os.Build
import android.os.Bundle
import android.os.VibrationEffect
import android.os.Vibrator
import android.text.format.DateUtils
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.corp.luqman.kotlintraining.R
import com.corp.luqman.kotlintraining.databinding.FragmentGameWordBinding
import java.util.EnumSet.of


class GameWordFragment : Fragment() {
    // TODO: Rename and change types of parameters
    // The current word


    lateinit var viewModel: GameWordViewModel

    // The list of words - the front of the list is the next word to guess


    private lateinit var binding: FragmentGameWordBinding

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? {

        // Inflate view and obtain an instance of the binding class
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_game_word,
            container,
            false
        )

        viewModel = ViewModelProviders.of(this).get(GameWordViewModel::class.java)
        binding.gameViewModel = viewModel
        binding.setLifecycleOwner(this)
        Log.i("GameWordFragment", "Called ViewModelProviders.of!")


// Buzzes when triggered with different buzz events
        viewModel.eventBuzz.observe(this, Observer { buzzType ->
            if (buzzType != GameWordViewModel.BuzzType.NO_BUZZ) {
                buzz(buzzType.pattern)
                viewModel.onBuzzComplete()
            }
        })
        //comment because use data binding in xml
//        binding.correctButton.setOnClickListener {
//            viewModel.onCorrect()
////            updateWordText()
////            updateScoreText()
//        }
//        binding.skipButton.setOnClickListener {
//            viewModel.onSkip()
////            updateWordText()
////            updateScoreText()
//        }
        //comment because use data binding in xml
//        viewModel.score.observe(this, Observer {
//            updateScoreText(it)
//        })
//        viewModel.word.observe(this, Observer {
//            updateWordText(it)
//        })
//        viewModel.currentTime.observe(this, Observer {
//            binding.timerText.text = DateUtils.formatElapsedTime(it)
//        })
        viewModel.finishGame.observe(this, Observer {
            when(it){
                true ->{
                    gameFinished()
                    viewModel.gameCompletedFinish()
                }
            }
        })
//        updateScoreText()
//        updateWordText()
        return binding.root

    }

    private fun buzz(pattern: LongArray) {
        val buzzer = activity?.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator

        buzzer?.let {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                buzzer.vibrate(VibrationEffect.createWaveform(pattern, -1))
            } else {
                //deprecated in API 26
                buzzer.vibrate(pattern, -1)
            }
        }
    }

    /**
     * Resets the list of words and randomizes the order
     */


    /**
     * Called when the game is finished
     */
    private fun gameFinished() {
        val currentScore = viewModel.score.value?:0
        val action = GameWordFragmentDirections.actionGameToScore(currentScore)
        findNavController(this).navigate(action)
//        Toast.makeText(context, "Game was Finish!", Toast.LENGTH_SHORT).show()
    }

    /**
     * Moves to the next word in the list
     */


    /** Methods for buttons presses **/



    /** Methods for updating the UI **/

    private fun updateWordText(wordFix : String) {
        binding.wordText.text = wordFix

    }

    private fun updateScoreText(scoreFix : Int) {
        binding.scoreText.text = scoreFix.toString()
    }
}