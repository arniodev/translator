package com.arniodev.translator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arniodev.translator.R
import com.arniodev.translator.data.HomepageItem

class HomepageAdapter(private val homepageItemList: List<HomepageItem>): RecyclerView.Adapter<HomepageAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconView: ImageView = view.findViewById(R.id.item_icon)
        val firstLineView: TextView = view.findViewById(R.id.item_name)
        val secondLineView: TextView = view.findViewById(R.id.item_descr)
        val sView = view
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.homepage_item, parent, false
        )
    )

    override fun getItemCount() = homepageItemList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = homepageItemList[position]
        holder.iconView.setImageResource(item.icon)
        holder.firstLineView.text = item.firstLine
        holder.secondLineView.text = item.secondLine
        holder.sView.setOnClickListener {
            item.clickOnListener()
        }
    }
}