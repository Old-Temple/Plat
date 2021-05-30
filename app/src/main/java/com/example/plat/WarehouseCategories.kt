package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class WarehouseCategoryFurniture(val furnitures : MutableList<SeeItemQuery.SeeItem>) : Fragment() {
    //todo : 창고 가구 목록
    fun newInstance(): Fragment {
        return WarehouseCategoryFurniture(furnitures)
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
        val adapter = WarehouseFurnitureAdapter(activity!!, furnitures)

        gridView.adapter = adapter

        return view
    }
}

class WarehouseCategoryThema(val themas : MutableList<SeeItemQuery.SeeItem>) : Fragment() {
    fun newInstance() : Fragment {
        return WarehouseCategoryThema(themas)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_thema, null)
        val gridView = view.findViewById<GridView>(R.id.warehouseThemaGridView)
        val adapter = WarehouseThemaAdapter(activity!!, themas)

        gridView.adapter = adapter

        return view
    }
}

class WarehouseCategoryAvatar(
    val heads : MutableList<SeeItemQuery.SeeItem>,
    val bodys : MutableList<SeeItemQuery.SeeItem>,
    val legs : MutableList<SeeItemQuery.SeeItem>
) : Fragment() {
    fun newInstance(): Fragment{
        return WarehouseCategoryAvatar(heads, bodys, legs)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_avatar, null)
        val gridView = view.findViewById<GridView>(R.id.warehouseAvatarGridView)
        val headAdapter = WarehouseAvatarAdapter(activity!!, heads)
        val bodyAdapter = WarehouseAvatarAdapter(activity!!, bodys)
        val shoesAdapter = WarehouseAvatarAdapter(activity!!, legs)

        val btnBody = view.findViewById<Button>(R.id.btnWarehouseAvatarBody)
        val btnHead = view.findViewById<Button>(R.id.btnWarehouseAvatarHead)
        val btnShoes = view.findViewById<Button>(R.id.btnWarehouseAvatarShoes)

        gridView.adapter = headAdapter

        btnBody.setOnClickListener { view ->
            gridView.adapter = bodyAdapter
        }
        btnHead.setOnClickListener { view ->
            gridView.adapter = headAdapter
        }
        btnShoes.setOnClickListener { view ->
            gridView.adapter = shoesAdapter
        }




        return view
    }

}

