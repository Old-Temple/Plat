package com.example.plat

import android.content.Context
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.fragment.app.Fragment
import com.bumptech.glide.Glide

var temp_view: View? = null
var itemData : SeeItemQuery.SeeItem? = null
//var groupId:


class PutFunitureWarehouseFurnitureItems(val context: Context, val items: MutableList<SeeItemQuery.SeeItem>?):
    BaseAdapter(){

    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_warehouse_furniture, null)

        val image = view.findViewById<ImageView>(R.id.imageOfWarehouseSearchFurniture)
        val text = view.findViewById<TextView>(R.id.textOfWarehouseSearchFurniture)
        val aaa = view.findViewById<LinearLayout>(R.id.warehouseFurnitureItem)
        val item = items?.get(position)


        // todo : 가구 아이템창 터치되었는지 확인위한 flag
        image.setOnClickListener{ view ->

            furniture.url = item?.itemInfo?.file.toString()

            Toast.makeText(context, "토스트 메세지 띄우기 입니다.", Toast.LENGTH_SHORT).show()

            Log.d("AAA", "AAA")


            if(funi_flag==1){
                temp_view?.setBackgroundColor(0)
                funi_flag = 0

            }

            if(funi_flag==0){
                temp_view = view
                view.setBackgroundColor(RED)
                funi_flag = 1
                itemData  = item


            }


        }

        Glide.with(view).load(item?.itemInfo?.file).into(image)
        text.text = item?.itemInfo?.itemName

        return view
    }

    override fun getCount(): Int {
        if (items != null) {
            return items.size
        }
        else{
            return 0
        }
    }

    override fun getItem(position: Int): SeeItemQuery.SeeItem? {
        return items?.get(position)
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}





