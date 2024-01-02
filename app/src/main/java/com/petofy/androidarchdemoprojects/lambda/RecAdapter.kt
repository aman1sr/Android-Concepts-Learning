package com.petofy.androidarchdemoprojects.lambda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petofy.androidarchdemoprojects.databinding.RecLambdaItemLayoutBinding


class RecAdapter(
    private val list : ArrayList<String>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<RecAdapter.DataViewHolder>() {

    var onItemClick : ((position: Int) -> Unit)? = null     // lambda property

   inner class DataViewHolder(private val binding: RecLambdaItemLayoutBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(source: String, position: Int) {
            binding.btnInterface.setOnClickListener {
                listener.onItemClicked(position)
            }
            binding.btnLambda.setOnClickListener {
                onItemClick?.invoke(position)
            }
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

    override fun onBindViewHolder(holder: DataViewHolder, position: Int) = holder.bind(list[position],position)



}