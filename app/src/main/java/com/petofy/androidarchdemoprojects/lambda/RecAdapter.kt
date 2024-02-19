package com.petofy.androidarchdemoprojects.lambda

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.petofy.androidarchdemoprojects.databinding.RecLambdaItemLayoutBinding


class RecAdapter(
    private val list : ArrayList<String>,
    private val listener: ItemClickListener
) : RecyclerView.Adapter<RecAdapter.DataViewHolder>() {

    /*
    * working of lambda in 3 steps (https://g.co/gemini/share/572ae87ae6e5)
    *       Step 1: Declaration of Lambda Property (the Contract)
    *       Step 2: Lambda Invocation (Sending Info if Needed)
    *       Step 3: Lambda Implementation (Receiving Info & Resuming Code Flow)
    *
    * flow of lambda prop here: (https://g.co/gemini/share/eb1e039ab0fa)
    * */
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