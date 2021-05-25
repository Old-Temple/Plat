package com.example.plat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView

class WarehouseFurnitureItems(val image:String, val text:String)
class WarehouseThemaItems(val image:String, val text:String)
class WarehouseAvatarItems(val image:String, val text:String)
class WarehouseMyListItems(val image:String, val text:String)

class WarehouseFurnitureAdapter(val context: Context, val items: ArrayList<WarehouseFurnitureItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_furniture, null)

        val image = view.findViewById<TextView>(R.id.imageOfWarehouseSearchFurniture)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchFurniture)
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

class WarehouseThemaAdapter(val context: Context, val items: ArrayList<WarehouseThemaItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_thema, null)

        val image = view.findViewById<TextView>(R.id.imageOfWarehouseSearchThema)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchThema)
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

class WarehouseAvatarAdapter(val context: Context, val items: ArrayList<WarehouseAvatarItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_avatar, null)

        val image = view.findViewById<TextView>(R.id.imageOfWarehouseSearchAvatar)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchAvatar)
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