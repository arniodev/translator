package com.arniodev.translator.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources
import androidx.recyclerview.widget.RecyclerView
import com.arniodev.translator.R
import com.arniodev.translator.data.EngineItem

class EngineAdapter(private val activity: AppCompatActivity, private val engineList: List<EngineItem>) : RecyclerView.Adapter<EngineAdapter.ViewHolder>() {

    private var selectedEngine = -1
    private val nextStepView = activity.findViewById<View>(R.id.engine_next_step) as RelativeLayout
    private val arrowView = activity.findViewById<View>(R.id.engine_next_step_arrow) as ImageView
    private val nextStepTextView =
        activity.findViewById<View>(R.id.engine_next_step_text) as TextView

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val iconView: ImageView = view.findViewById(R.id.engine_icon)
        val engineNameView: TextView = view.findViewById(R.id.engine_name)
        val line: ImageView = view.findViewById(R.id.chosen_line)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = ViewHolder(
        LayoutInflater.from(parent.context).inflate(
            R.layout.engine_item, parent, false
        )
    )

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val engine = engineList[position]
        holder.iconView.setImageResource(engine.icon)
        holder.engineNameView.text = engine.name

        if (selectedEngine == position) {
            holder.line.visibility = View.VISIBLE
        } else {
            holder.line.visibility = View.GONE
        }

        holder.itemView.setOnClickListener {
            val oldSelectedEngine = selectedEngine
            selectedEngine = holder.adapterPosition
            nextStepView.background = AppCompatResources.getDrawable(
                activity,
                R.drawable.next_step_btn
            )
            arrowView.alpha = 1F
            nextStepTextView.alpha = 1F
            notifyItemChanged(oldSelectedEngine)
            notifyItemChanged(selectedEngine)
        }
    }

    fun getEngine() = engineList[selectedEngine].engine

    override fun getItemCount() = engineList.size

}