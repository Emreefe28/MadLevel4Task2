package com.example.madlevel4task2

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.result.view.*

class ResultAdapter(private val results: List<Result>) :
    RecyclerView.Adapter<ResultAdapter.ViewHolder>() {

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun databind(result: Result) {
            itemView.tvDate.text = result.date.toString()
            itemView.tvResult.text = result.result
            setImage(result.choicePc, itemView.ivComputer)
            setImage(result.choiceYou, itemView.ivYou)
        }
    }


    private fun setImage(id: Int, iv: ImageView) {
        // 1 = paper, 2 = rock, 3= scissors.
        when (id) {
            1 -> iv.setImageResource(R.drawable.paper)
            2 -> iv.setImageResource(R.drawable.rock)
            3 -> iv.setImageResource(R.drawable.scissors)
        }

    }

    /**
     * Creates and returns a ViewHolder object, inflating a standard layout called simple_list_item_1.
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.result, parent, false)
        )
    }

    /**
     * Returns the size of the list
     */
    override fun getItemCount(): Int {
        return results.size
    }

    /**
     * Called by RecyclerView to display the data at the specified position.
     */
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.databind(results[position])
    }


}
