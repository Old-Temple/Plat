package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class WarehouseCategoryFurnitureChange : Fragment() {
    // todo : 가구 바꿀때  내부 카테고리
    fun newInstance(): Fragment {
        return WarehouseCategoryFurnitureChange()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }
    //todo : 이건 어떻게 쓰는거죠
    private val myList = arrayListOf<PutFunitureWarehouseFurnitureItems>(
        PutFunitureWarehouseFurnitureItems("Image1","Text1"),
        PutFunitureWarehouseFurnitureItems("Image2","Text2"),
        PutFunitureWarehouseFurnitureItems("Image3","Text3"),
        PutFunitureWarehouseFurnitureItems("Image4","Text4"),
        PutFunitureWarehouseFurnitureItems("Image5","Text5"),
        PutFunitureWarehouseFurnitureItems("Image6","Text6"),
        PutFunitureWarehouseFurnitureItems("Image7","Text7"),
        PutFunitureWarehouseFurnitureItems("Image8","Text8"),
        PutFunitureWarehouseFurnitureItems("Image9","Text9"),
        PutFunitureWarehouseFurnitureItems("Image0","Text0"),
        PutFunitureWarehouseFurnitureItems("Image9","Text9"),
        PutFunitureWarehouseFurnitureItems("Image8","Text8"),
        PutFunitureWarehouseFurnitureItems("Image7","Text7"),
        PutFunitureWarehouseFurnitureItems("Image6","Text6"),
        PutFunitureWarehouseFurnitureItems("Image5","Text5"),
        PutFunitureWarehouseFurnitureItems("Image4","Text4"),
        PutFunitureWarehouseFurnitureItems("Image3","Text3"),
        PutFunitureWarehouseFurnitureItems("Image2","Text2"),
        PutFunitureWarehouseFurnitureItems("Image1","Text1"),
        PutFunitureWarehouseFurnitureItems("Image2","Text2"),
        PutFunitureWarehouseFurnitureItems("Image3","Text3"),
        PutFunitureWarehouseFurnitureItems("Image4","Text4"),
        PutFunitureWarehouseFurnitureItems("Image5","Text5")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_furniture, null)
        val gridView = view.findViewById<GridView>(R.id.warehouseFurnitureGridView)
        val adapter = PutFurnitureWarehouseAdapter(activity!!, myList)

        gridView.adapter = adapter

        return view
    }
}
