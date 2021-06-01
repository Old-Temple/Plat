package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.ImageView
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogBuyItems(val mainActivity: MainActivity, val item:SeeTypeQuery.ItemInfo?) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_buy_items, container, false)
        val name = view.findViewById<TextView>(R.id.buyItemName)
        val image = view.findViewById<ImageView>(R.id.buyItemImage)

        name.text = item?.itemName
        Glide.with(view).load(item?.file).into(image)

        view.findViewById<Button>(R.id.btnbuyItembuy).setOnClickListener { view ->
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val response : Response<BuyItemMutation.Data> =
                    mainActivity.apolloClient?.mutate(BuyItemMutation(item?.id!!))!!.await()
                if (response.data?.buyItem?.error != null){
                    name.text = response.data?.buyItem?.error
                }
                else{
                    dismiss()
                }
            }
        }

        view.findViewById<Button>(R.id.btnbuyItemCancle).setOnClickListener{ view ->
            dismiss()
        }
        return view.rootView
    }

}