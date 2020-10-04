package com.example.madlevel4task2

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_results.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class ResultsFragment : Fragment() {
    private val results = arrayListOf<Result>()
    private val resultListAdapter = ResultAdapter(results)
    private lateinit var resultRepository: ResultRepository
    private val mainScope = CoroutineScope(Dispatchers.Main)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        setHasOptionsMenu(true)
        return inflater.inflate(R.layout.fragment_results, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val decorationItem = DividerItemDecoration(context, DividerItemDecoration.VERTICAL)
        rvResults.layoutManager = LinearLayoutManager(
            context, RecyclerView.VERTICAL, false
        )

        rvResults.addItemDecoration(decorationItem)
        rvResults.adapter = resultListAdapter

        resultRepository = ResultRepository(requireContext())
        getResultsFromDatabase()
    }

    private fun getResultsFromDatabase() {
        mainScope.launch {
            val resultList = withContext(Dispatchers.IO) {
                resultRepository.getAllResults()
            }
            this@ResultsFragment.results.clear()
            this@ResultsFragment.results.addAll(resultList)
            this@ResultsFragment.resultListAdapter.notifyDataSetChanged()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.clear_fights -> {
                deleteAllFights()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun deleteAllFights() {
        mainScope.launch {
            withContext(Dispatchers.IO) {
                resultRepository.deleteAllResults()
            }
            getResultsFromDatabase()
        }
    }


}