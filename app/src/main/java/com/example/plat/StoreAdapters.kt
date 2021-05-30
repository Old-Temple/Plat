package com.example.plat

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView

open class StoreItems {
    open val image = ""
    open val text = ""
}
class StoreFurnitureAdapter(val fragment: Fragment, val context: Context, val items: List<SeeTypeQuery.ItemInfo>?):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_furniture, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchFurniture)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchFurniture)
        val item = items?.get(position)

        view.findViewById<LinearLayout>(R.id.storeFurnitureItem).setOnClickListener { view ->

            val makedialogbuyitems = DialogBuyItems(item)
            makedialogbuyitems.show(fragment.childFragmentManager.beginTransaction(), makedialogbuyitems.tag)
        }

        image.text = item?.file
        text.text = item?.itemName

        return view
    }

    override fun getCount(): Int {
        if (items != null) {
            return items.size
        }
        else {
            return 0
        }
    }

    override fun getItem(position: Int): SeeTypeQuery.ItemInfo? {
        return items?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}

class StoreThemaAdapter(val fragment: Fragment, val context: Context, val items: List<SeeTypeQuery.ItemInfo>?):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_thema, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchThema)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchThema)
        val item = items?.get(position)

        view.findViewById<LinearLayout>(R.id.storeThemaItem).setOnClickListener { view ->

            val makedialogbuyitems = DialogBuyItems(item)
            makedialogbuyitems.show(fragment.childFragmentManager.beginTransaction(), makedialogbuyitems.tag)
        }

        image.text = item?.file
        text.text = item?.itemName

        return view
    }

    override fun getCount(): Int {
        if (items != null) {
            return items.size
        }
        else {
            return 0
        }
    }

    override fun getItem(position: Int): SeeTypeQuery.ItemInfo? {
        return items?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}

class StoreAvatarAdapter(val fragment: Fragment, val context: Context, val items: List<SeeTypeQuery.ItemInfo>?):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_avatar, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchAvatar)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchAvatar)
        val item = items?.get(position)

        view.findViewById<LinearLayout>(R.id.storeAvatarItem).setOnClickListener { view ->
            getItem(position)
            val makedialogbuyitems = DialogBuyItems(item)
            makedialogbuyitems.show(fragment.childFragmentManager.beginTransaction(), makedialogbuyitems.tag)
        }

        image.text = item?.file
        text.text = item?.itemName

        return view
    }

    override fun getCount(): Int {
        if (items != null) {
            return items.size
        }
        else {
            return 0
        }
    }

    override fun getItem(position: Int): SeeTypeQuery.ItemInfo? {
        return items?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}