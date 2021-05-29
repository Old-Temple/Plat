package com.example.plat

import android.content.Context
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast

var temp_view: View? = null

class WarehouseFurnitureItems2(val image:String, val text:String)
class WarehouseThemaItems2(val image:String, val text:String)
class WarehouseAvatarItems2(val image:String, val text:String)
class WarehouseMyListItems2(val image:String, val text:String)

class PutFurnitureWarehouseAdapter(val context: Context, val items: ArrayList<WarehouseFurnitureItems>):
    BaseAdapter(){


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_furniture, null)

        val image = view.findViewById<TextView>(R.id.imageOfWarehouseSearchFurniture)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchFurniture)
        val item = items[position]


        // todo : 가구 아이템창 터치되었는지 확인위한 flag
        view.findViewById<LinearLayout>(R.id.warehouseFurnitureItem).setOnClickListener{ view ->

            if(flag==1){
                temp_view?.setBackgroundColor(0)
                flag = 0
            }
            
            if(flag==0){
                temp_view = view
                view.setBackgroundColor(RED)
                flag = 1
            }


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




