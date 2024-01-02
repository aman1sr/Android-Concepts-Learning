package com.petofy.androidarchdemoprojects.lambda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petofy.androidarchdemoprojects.databinding.RecLambdaItemLayoutBinding


class RecAdapter(
    private val list : ArrayList<String>
) : RecyclerView.Adapter<RecAdapter.DataViewHolder>() {
    class DataViewHolder(private val binding: RecLambdaItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(source: String) {

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int)= DataViewHolder(
        RecLambdaItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun getItemCount() = list.size

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(list[position])



}