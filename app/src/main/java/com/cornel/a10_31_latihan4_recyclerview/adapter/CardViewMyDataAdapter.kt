package com.cornel.a10_31_latihan4_recyclerview.adapter

import android.content.Intent
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cornel.a10_31_latihan4_recyclerview.DetailActivity
import com.cornel.a10_31_latihan4_recyclerview.MyData
import com.cornel.a10_31_latihan4_recyclerview.databinding.ItemCardviewBinding

class CardViewMyDataAdapter(private val listMyData: ArrayList<MyData>) :
    RecyclerView.Adapter<CardViewMyDataAdapter.CardViewViewHolder>() {
    inner class CardViewViewHolder(private val binding: ItemCardviewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(myData: MyData) {
            with(binding) {
                Glide.with(itemView.context)
                    .load(myData.photo)
                    .apply(RequestOptions().override(350, 550))
                    .into(imgItemPhoto)
                tvItemName.text = myData.name
                tvItemDetail.text = myData.description
                btnSetFavorite.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Favorite ${listMyData[adapterPosition].name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                btnSetShare.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Share ${listMyData[adapterPosition].name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
                itemView.setOnClickListener {
                    Toast.makeText(
                        itemView.context,
                        "Kamu memilih ${listMyData[adapterPosition].name}",
                        Toast.LENGTH_SHORT
                    ).show()
                }
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
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewViewHolder {
        val binding =
            ItemCardviewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CardViewViewHolder(binding)
    }
    override fun onBindViewHolder(holder: CardViewViewHolder, position: Int) {
        holder.bind(listMyData[position])
    }
    override fun getItemCount(): Int = listMyData.size
}