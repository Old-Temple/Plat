package com.example.plat

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

class WarehouseFurnitureAdapter(val context: Context, val items: MutableList<SeeItemQuery.SeeItem>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_furniture, null)

        val image = view.findViewById<TextView>(R.id.imageOfWarehouseSearchFurniture)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchFurniture)
        val item = items[position]

        view.findViewById<LinearLayout>(R.id.warehouseFurnitureItem).setOnClickListener{ view ->
            //todo : 여기 가구 작업
        }

        image.text = item.itemInfo?.file
        text.text = item.itemInfo?.itemName

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

class WarehouseThemaAdapter(val context: Context, val items: MutableList<SeeItemQuery.SeeItem>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_thema, null)

        val image = view.findViewById<TextView>(R.id.imageOfWarehouseSearchThema)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchThema)
        val item = items[position]

        view.findViewById<LinearLayout>(R.id.warehouseThemaItem).setOnClickListener { view ->
            //todo : 테마 아이템 클릭 리스너
        }

        image.text = item.itemInfo?.file
        text.text = item.itemInfo?.itemName

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

class WarehouseAvatarAdapter(val context: Context, val items: MutableList<SeeItemQuery.SeeItem>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_avatar, null)

        val image = view.findViewById<TextView>(R.id.imageOfWarehouseSearchAvatar)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchAvatar)
        val item = items[position]

        view.findViewById<LinearLayout>(R.id.warehouseAvatarItem).setOnClickListener { view ->
            //todo : 아바타 클릭

        }

        image.text = item.itemInfo?.file
        text.text = item.itemInfo?.itemName

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