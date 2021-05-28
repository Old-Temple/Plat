package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.TextView
import androidx.fragment.app.DialogFragment

class DialogBuyItems(val item:StoreItems) : DialogFragment() {
//    fun onCreate() {
//        super.onCreate(savedInstanceState)
//    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_buy_items, container, false)
        //todo : 아이템 구매창
        val name = view.findViewById<TextView>(R.id.buyItemName)
        val image = view.findViewById<TextView>(R.id.buyItemImage)

        name.text = item.text
        image.text = item.image

        view.findViewById<Button>(R.id.btnbuyItembuy).setOnClickListener { view ->
            //todo : 구매버튼
        }

        view.findViewById<Button>(R.id.btnbuyItemCancle).setOnClickListener{ view ->
            dismiss()
        }
        return view.rootView
    }

}