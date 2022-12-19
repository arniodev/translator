package com.arniodev.translator

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView

class PrivacyAdapter(private val privacyList: List<PrivacyItem>) : RecyclerView.Adapter<PrivacyAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val privacyIcon : ImageView = view.findViewById(R.id.privacy_icon)
        val privacyName : TextView = view.findViewById(R.id.privacy_name)
        val privacyDescr: TextView = view.findViewById(R.id.privacy_descr)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.privacy_list_item, parent, false))

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = privacyList[position]
        holder.privacyIcon.setImageResource(item.image)
        holder.privacyName.text = item.name
        holder.privacyDescr.text = item.descr
    }

    override fun getItemCount() = privacyList.size
}