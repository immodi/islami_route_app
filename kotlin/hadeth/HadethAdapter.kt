package com.client.routeapplication.hadeth

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.client.routeapplication.R
import com.client.routeapplication.models.HadethModel

class HadethAdapter(
    private val mList: List<HadethModel>,
    private val callback: (String, Int) -> Unit
) : RecyclerView.Adapter<HadethAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        // inflates the card_view_design view
        // that is used to hold list item
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.hadeth_item, parent, false)

        return ViewHolder(view)
    }

    // binds the list items to a view
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val model = mList[position]

        holder.hadethNameTextView.text = model.name
        holder.itemView.setOnClickListener {
            callback.invoke(
                holder.hadethNameTextView.text.toString(),
                position
            )
        }
    }

    // return the number of the items in the list
    override fun getItemCount(): Int {
        return mList.size
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val hadethNameTextView: TextView = this.itemView.findViewById(R.id.hadeth_name)
    }
}