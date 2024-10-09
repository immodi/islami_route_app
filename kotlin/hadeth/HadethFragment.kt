package com.client.routeapplication.hadeth

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.client.routeapplication.R
import com.client.routeapplication.helpers.getLocalFileContent
import com.client.routeapplication.helpers.readTextFileLinesFromAssets
import com.client.routeapplication.models.HadethModel
import com.client.routeapplication.models.QuranModel
import com.client.routeapplication.quran.QuranAdapter
import com.client.routeapplication.quran.SoraReadFragment

class HadethFragment : Fragment() {
    private var ahadethContent = arrayListOf<String>()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize the RecyclerView
        val view = inflater.inflate(R.layout.fragment_hadeth, container, false)
        val recyclerView: RecyclerView = view!!.findViewById(R.id.recycler_view_hadeth)

        // Initialize the data
        val contentArray = readTextFileLinesFromAssets(container!!.context, "ahadeth.txt")

        val ahadethNames = mutableListOf<HadethModel>()

        for (hadeth in contentArray) {
            ahadethContent.add(hadeth[1])
            ahadethNames.add(HadethModel(hadeth[0]))
        }

        val adapter = HadethAdapter(ahadethNames, ::launchHadethFragment)
        recyclerView.adapter = adapter
        return view
    }

    private fun launchHadethFragment(hadethName: String, hadethIndex: Int) {
        val transaction = parentFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, HadethReadFragment(
                hadethName,
                ahadethContent[hadethIndex]
            ))

        transaction.addToBackStack(null)
        transaction.commit()
    }
}