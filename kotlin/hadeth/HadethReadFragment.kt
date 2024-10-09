package com.client.routeapplication.hadeth

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.client.routeapplication.R
import com.client.routeapplication.models.HadethModel

class HadethReadFragment( private val hadethName: String, private val hadethContent: String) : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize the RecyclerView
        val view = inflater.inflate(R.layout.fragment_read, container, false)

        view.findViewById<TextView>(R.id.sora_name).text = hadethName
        view.findViewById<TextView>(R.id.sora_content).text = hadethContent

        return view
    }
}