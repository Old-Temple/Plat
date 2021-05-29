package com.example.plat

import android.content.Context
import android.graphics.Color.RED
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.LinearLayout
import android.widget.TextView

var head_temp_view: View? = null

class ClothWarehouseAvatarItems(val image:String, val text:String)




class ClothWarehouseAvatarAdapter(val context: Context, val items: ArrayList<ClothWarehouseAvatarItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_avatar, null)

        val image = view.findViewById<TextView>(R.id.imageOfWarehouseSearchAvatar)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchAvatar)
        val item = items[position]



        //todo : 아이템 터치된후에 아바타 파츠 터치되었는지 확인 위함
        view.findViewById<LinearLayout>(R.id.warehouseAvatarItem).setOnClickListener { view ->

            if(headflag==1){
                head_temp_view?.setBackgroundColor(0)
                headflag = 0
                    }

            if(headflag==0){
                head_temp_view = view
                view.setBackgroundColor(RED)
                headflag = 1
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