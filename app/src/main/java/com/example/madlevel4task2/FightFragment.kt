package com.example.madlevel4task2

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import kotlinx.android.synthetic.main.fragment_fight.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.util.*
import kotlin.random.Random

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FightFragment : Fragment() {
    private val results = arrayListOf<Result>()
    private val shoppingListAdapter = ResultAdapter(results)
    private lateinit var resultRepository: ResultRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_fight, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        resultRepository = ResultRepository(requireContext())
//        initRv()
        ivPaper.setOnClickListener {
            makeFight(1)
        }
        ivRock.setOnClickListener {
            makeFight(2)
        }
        ivScissors.setOnClickListener {
            makeFight(3)
        }

    }

    private fun makeFight(id: Int) {
        val randomChoiceComputer = Random.nextInt(1, 4);
        makeChoice(id, ivYou)
        makeChoice(randomChoiceComputer, ivComputer)
        val result: String

        // decide win (1 > 2 , 2 > 3, 3 > 1)
        if (id == 1 && randomChoiceComputer == 2 || id == 2 && randomChoiceComputer == 3 ||
            id == 3 && randomChoiceComputer == 1
        ) {
            tvResult.text = getString(R.string.win)
            result = "You win"
        } else if (id == randomChoiceComputer) {
            tvResult.text = getString(R.string.draw)
            result = "Draw"
        } else {
            tvResult.text = getString(R.string.lose)
            result = "Computer wins"
        }
        addResult(result, id, randomChoiceComputer)
    }

    private fun makeChoice(id: Int, iv: ImageView) {
        // 1 = paper, 2 = rock, 3= scissors.
        when (id) {
            1 -> iv.setImageResource(R.drawable.paper)
            2 -> iv.setImageResource(R.drawable.rock)
            3 -> iv.setImageResource(R.drawable.scissors)
        }

    }

    private fun addResult(result: String, choiceYou: Int, choicePc: Int) {
        mainScope.launch {
            val resultObj = Result(
                date = Calendar.getInstance().time,
                choiceYou = choiceYou,
                choicePc = choicePc,
                result = result
            )

            withContext(Dispatchers.IO) {
                resultRepository.insertResult(resultObj)
            }
        }
    }

}