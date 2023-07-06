package com.arniodev.translator.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.arniodev.translator.data.LanguageItem
import com.arniodev.translator.R

class LanguageAdapter(private val context: Context,private val LanguageList: List<LanguageItem>,val callback: (langId: String) -> Unit): RecyclerView.Adapter<LanguageAdapter.ViewHolder>() {

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val langNameView: TextView = view.findViewById(R.id.lang_name)
        val sView = view
    }

    override fun getItemCount() = LanguageList.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(R.layout.lang_item,parent,false)
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = LanguageList[position]
        holder.langNameView.text =context.getString(item.langName)
        holder.sView.setOnClickListener {
            callback(item.langId)
        }
    }

}