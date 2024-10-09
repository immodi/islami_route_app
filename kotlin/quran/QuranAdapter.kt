package com.client.routeapplication.quran

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.client.routeapplication.R
import com.client.routeapplication.models.QuranModel

class QuranAdapter(private val mList: List<QuranModel>, private val callback: (String, Int) -> Unit) :
    RecyclerView.Adapter<QuranAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.quran_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mList[position]

        holder.ayatNumberTextView.text = model.count.toString()
        holder.soraNameTextView.text = model.name

        holder.itemView.setOnClickListener {
            callback.invoke(
                holder.soraNameTextView.text.toString(),
                position + 1
            )
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    inner class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val ayatNumberTextView: TextView = this.itemView.findViewById(R.id.ayat_number)
        val soraNameTextView: TextView = this.itemView.findViewById(R.id.sora_name)
    }
}