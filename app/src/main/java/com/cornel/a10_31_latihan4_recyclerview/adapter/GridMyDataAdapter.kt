package com.cornel.a10_31_latihan4_recyclerview.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cornel.a10_31_latihan4_recyclerview.DetailActivity
import com.cornel.a10_31_latihan4_recyclerview.MyData
import com.cornel.a10_31_latihan4_recyclerview.R
import com.cornel.a10_31_latihan4_recyclerview.databinding.ItemGridBinding

class GridMyDataAdapter(private val listMyData: ArrayList<MyData>) :
    RecyclerView.Adapter<GridMyDataAdapter.GridViewHolder>() {

    inner class GridViewHolder(private val binding: ItemGridBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myData: MyData) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(myData.photo)
                    .apply(RequestOptions().override(350, 550))
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
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): GridMyDataAdapter.GridViewHolder {
        val binding = ItemGridBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GridViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GridMyDataAdapter.GridViewHolder, position: Int) {
        holder.bind(listMyData[position])
    }

    override fun getItemCount(): Int = listMyData.size
}