package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class DialogSearchPlat(val item:SearchedItem) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view:View = inflater.inflate(R.layout.fragment_dialog_search_plat, container, false)

        view.findViewById<TextView>(R.id.searchedItemText).text =
            item.text
        view.findViewById<TextView>(R.id.searchedItemImage).text =
            item.image

        view.findViewById<Button>(R.id.btnSearchedRequest).setOnClickListener { view ->
            //todo : 참가 요청 버튼
        }
        view.findViewById<Button>(R.id.btnSearchedCancle).setOnClickListener { view ->
            dismiss()
        }

        return view
    }
}