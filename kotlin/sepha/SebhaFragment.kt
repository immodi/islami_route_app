package com.client.routeapplication.sepha

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import com.client.routeapplication.R

class SebhaFragment : Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_sebha, container, false)
        val button = view.findViewById<Button>(R.id.sabeh_button)
        val count = view.findViewById<TextView>(R.id.tesbihat_count)
        val sebhaBody = view.findViewById<ImageView>(R.id.sebha_body)

        button.setOnClickListener {
            val newText = (count.text.toString().toInt() + 1).toString()
            count.text = newText
            sebhaBody.rotation += 12
        }
        return view
    }
}