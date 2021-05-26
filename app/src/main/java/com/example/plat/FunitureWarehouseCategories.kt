package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class WarehouseCategoryFurnitureChange : Fragment() {
    // TODO: Rename and change types of parameters
    fun newInstance(): Fragment {
        return WarehouseCategoryFurnitureChange()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val myList = arrayListOf<WarehouseFurnitureItems>(
        WarehouseFurnitureItems("Image1","Text1"),
        WarehouseFurnitureItems("Image2","Text2"),
        WarehouseFurnitureItems("Image3","Text3"),
        WarehouseFurnitureItems("Image4","Text4"),
        WarehouseFurnitureItems("Image5","Text5"),
        WarehouseFurnitureItems("Image6","Text6"),
        WarehouseFurnitureItems("Image7","Text7"),
        WarehouseFurnitureItems("Image8","Text8"),
        WarehouseFurnitureItems("Image9","Text9"),
        WarehouseFurnitureItems("Image0","Text0"),
        WarehouseFurnitureItems("Image9","Text9"),
        WarehouseFurnitureItems("Image8","Text8"),
        WarehouseFurnitureItems("Image7","Text7"),
        WarehouseFurnitureItems("Image6","Text6"),
        WarehouseFurnitureItems("Image5","Text5"),
        WarehouseFurnitureItems("Image4","Text4"),
        WarehouseFurnitureItems("Image3","Text3"),
        WarehouseFurnitureItems("Image2","Text2"),
        WarehouseFurnitureItems("Image1","Text1"),
        WarehouseFurnitureItems("Image2","Text2"),
        WarehouseFurnitureItems("Image3","Text3"),
        WarehouseFurnitureItems("Image4","Text4"),
        WarehouseFurnitureItems("Image5","Text5")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_furniture, null)
        val gridView = view.findViewById<GridView>(R.id.warehouseFurnitureGridView)
        val adapter = WarehouseFurnitureAdapter2(activity!!, myList)

        gridView.adapter = adapter

        return view
    }
}
