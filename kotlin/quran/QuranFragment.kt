package com.client.routeapplication.quran

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.client.routeapplication.R
import com.client.routeapplication.helpers.getLocalFileContent
import com.client.routeapplication.models.QuranModel
import com.client.routeapplication.models.allSuras

class QuranFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Initialize the RecyclerView
        val view = inflater.inflate(R.layout.fragment_quran, container, false)
        val recyclerView: RecyclerView = view!!.findViewById(R.id.recycler_view)

        // Initialize the data
        val itemList = mutableListOf<QuranModel>()

        for (suraName in allSuras) {
            itemList.add(QuranModel(suraName, 286))
        }

        val adapter = QuranAdapter(itemList, ::launchSoraFragment)
        recyclerView.adapter = adapter
        return view
    }

    private fun launchSoraFragment(soraName: String, soraIndex: Int) {
        val soraContent = getLocalFileContent(requireView().context, "soar/$soraIndex.txt")
        val transaction = parentFragmentManager
            .beginTransaction()
            .replace(R.id.frame_layout, SoraReadFragment(arrayListOf(soraName, soraContent)))

        transaction.addToBackStack(null)
        transaction.commit()
    }
}