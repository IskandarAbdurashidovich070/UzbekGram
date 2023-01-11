package com.iskandar.uzbekgram

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.iskandar.uzbekgram.databinding.ItemPagerBinding

class MyRvAdapter(var list: ArrayList<User>) : RecyclerView.Adapter<MyRvAdapter.Vh>() {

    inner class Vh(var rvItemBinding: ItemPagerBinding): RecyclerView.ViewHolder(rvItemBinding.root){
        fun onBind(user: User, position: Int ){
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Vh {
        return Vh(ItemPagerBinding.inflate(LayoutInflater.from(parent.context) , parent, false))
    }

    override fun onBindViewHolder(holder: Vh, position: Int) {
        holder.onBind(list[position], position)
    }

    override fun getItemCount(): Int = list.size

}