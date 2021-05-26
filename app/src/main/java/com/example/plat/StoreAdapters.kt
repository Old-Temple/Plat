package com.example.plat

import android.content.Context
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.LinearLayout
import android.widget.TextView

class StoreFurnitureItems(val image:String, val text:String)
class StoreThemaItems(val image:String, val text:String)
class StoreAvatarItems(val image:String, val text:String)
class StoreMyListItems(val image:String, val text:String)

class StoreFurnitureAdapter(val context: Context, val items: ArrayList<StoreFurnitureItems>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_furniture, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchFurniture)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchFurniture)
        val item = items[position]

        view.findViewById<LinearLayout>(R.id.warehouseAvatarItem).setOnClickListener { view ->

            val makedialogbuyitems = DialogBuyItems()
            makedialogbuyitems.show(childFragmentManager.beginTransaction(), makeChangeFragment.tag)
        }
        image.text = item.image
        text.text = item.text

        return view
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}

class StoreThemaAdapter(val context: Context, val items: ArrayList<StoreThemaItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_thema, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchThema)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchThema)
        val item = items[position]

        image.text = item.image
        text.text = item.text

        return view
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}

class StoreAvatarAdapter(val context: Context, val items: ArrayList<StoreAvatarItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_avatar, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchAvatar)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchAvatar)
        val item = items[position]

        image.text = item.image
        text.text = item.text

        return view
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}

class StoreMyListAdapter(val context: Context, val items: ArrayList<StoreMyListItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_mylist, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchMyList)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchMyList)
        val item = items[position]

        image.text = item.image
        text.text = item.text

        return view
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}