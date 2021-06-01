package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class WarehouseCategoryFurnitureChange(val furnitures : MutableList<SeeItemQuery.SeeItem>?) : Fragment() {


    fun newInstance(): Fragment {
        return WarehouseCategoryFurnitureChange(furnitures!!)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //todo : 뷰 할 때마다 리스트 불러와야함
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_furniture, null)
        val gridView = view.findViewById<GridView>(R.id.warehouseFurnitureGridView)
        val adapter = PutFunitureWarehouseFurnitureItems(activity!!, furnitures)

        gridView.adapter = adapter

        return view
    }
}

//class putFurniturePlat (val mainActivity: MainActivity) : Fragment() {
//    override fun onCreateView(
//        inflater: LayoutInflater,
//        container: ViewGroup?,
//        savedInstanceState: Bundle?
//    ): View? {
//        val view: View = inflater.inflate(R.layout.fragment_funiture_put_child_plat, null)
//
//    }
//}
