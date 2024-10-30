package com.cornel.a10_31_latihan4_recyclerview.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cornel.a10_31_latihan4_recyclerview.DetailActivity
import com.cornel.a10_31_latihan4_recyclerview.MyData
import com.cornel.a10_31_latihan4_recyclerview.databinding.ItemListBinding

class ListMyDataAdapter(private val listMyData: ArrayList<MyData>) :
    RecyclerView.Adapter<ListMyDataAdapter.ListViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder {
        val binding = ItemListBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListViewHolder(binding)
    }
    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind(listMyData[position])
    }
    override fun getItemCount(): Int = listMyData.size
    inner class ListViewHolder(private val binding: ItemListBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myData: MyData) {
            with(binding) {
                tvItemName.text = myData.name
                tvItemDescription.text = myData.description
                Glide.with(itemView.context)
                    .load(myData.photo)
                    .apply(RequestOptions().override(55, 55))
                    .into(imgItemPhoto)
            }
            itemView.setOnClickListener {
                val intent = Intent(itemView.context, DetailActivity::class.java)
                intent.putExtra(DetailActivity.EXTRA_NAME, myData.name)
                intent.putExtra(DetailActivity.EXTRA_DESCRIPTION, myData.description)
                intent.putExtra(DetailActivity.EXTRA_PHOTO, myData.photo)
                itemView.context.startActivity(intent)
            }
        }
    }
}
