package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*

class WarehouseCategoryFurniture : Fragment() {
    // TODO: Rename and change types of parameters
    fun newInstance(): Fragment {
        return WarehouseCategoryFurniture()
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
        val adapter = WarehouseFurnitureAdapter(activity!!, myList)

        gridView.adapter = adapter

        return view
    }
}

class WarehouseCategoryThema : Fragment() {
    fun newInstance() : Fragment {
        return WarehouseCategoryThema()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val myList = arrayListOf<WarehouseThemaItems>(
        WarehouseThemaItems("ThemaImage1","Text1"),
        WarehouseThemaItems("ThemaImage2","Text2"),
        WarehouseThemaItems("ThemaImage3","Text3"),
        WarehouseThemaItems("ThemaImage4","Text4"),
        WarehouseThemaItems("ThemaImage5","Text5"),
        WarehouseThemaItems("ThemaImage6","Text6"),
        WarehouseThemaItems("ThemaImage7","Text7"),
        WarehouseThemaItems("ThemaImage8","Text8"),
        WarehouseThemaItems("ThemaImage9","Text9"),
        WarehouseThemaItems("ThemaImage0","Text0"),
        WarehouseThemaItems("ThemaImage9","Text9"),
        WarehouseThemaItems("ThemaImage8","Text8"),
        WarehouseThemaItems("ThemaImage7","Text7"),
        WarehouseThemaItems("ThemaImage6","Text6"),
        WarehouseThemaItems("ThemaImage5","Text5"),
        WarehouseThemaItems("ThemaImage4","Text4"),
        WarehouseThemaItems("ThemaImage3","Text3"),
        WarehouseThemaItems("ThemaImage2","Text2"),
        WarehouseThemaItems("ThemaImage1","Text1"),
        WarehouseThemaItems("ThemaImage2","Text2"),
        WarehouseThemaItems("ThemaImage3","Text3"),
        WarehouseThemaItems("ThemaImage4","Text4"),
        WarehouseThemaItems("ThemaImage5","Text5")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_thema, null)
        val gridView = view.findViewById<GridView>(R.id.warehouseThemaGridView)
        val adapter = WarehouseThemaAdapter(activity!!, myList)

        gridView.adapter = adapter

        return view
    }
}

class WarehouseCategoryAvatar : Fragment() {
    fun newInstance(): Fragment{
        return WarehouseCategoryAvatar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val bodyList = arrayListOf<WarehouseAvatarItems>(
        WarehouseAvatarItems("BImage1","Text1"),
        WarehouseAvatarItems("BImage2","Text2"),
        WarehouseAvatarItems("BImage3","Text3"),
        WarehouseAvatarItems("BImage4","Text4"),
        WarehouseAvatarItems("BImage5","Text5"),
        WarehouseAvatarItems("BImage6","Text6"),
        WarehouseAvatarItems("BImage7","Text7")
    )
    private val headList = arrayListOf<WarehouseAvatarItems>(
        WarehouseAvatarItems("HImage8","Text8"),
        WarehouseAvatarItems("HImage9","Text9"),
        WarehouseAvatarItems("HImage0","Text0"),
        WarehouseAvatarItems("HImage9","Text9"),
        WarehouseAvatarItems("HImage8","Text8"),
        WarehouseAvatarItems("HImage7","Text7"),
        WarehouseAvatarItems("HImage6","Text6"),
        WarehouseAvatarItems("HImage5","Text5"),
        WarehouseAvatarItems("hImage4","Text4"),
        WarehouseAvatarItems("hImage3","Text3"),
        WarehouseAvatarItems("hImage2","Text2")
    )
    private val shoesList = arrayListOf<WarehouseAvatarItems>(
        WarehouseAvatarItems("Image1","Text1"),
        WarehouseAvatarItems("Image2","Text2"),
        WarehouseAvatarItems("Image3","Text3"),
        WarehouseAvatarItems("Image4","Text4"),
        WarehouseAvatarItems("Image5","Text5")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_avatar, null)
        val gridView = view.findViewById<GridView>(R.id.warehouseAvatarGridView)
        val headAdapter = WarehouseAvatarAdapter(activity!!, headList)
        val bodyAdapter = WarehouseAvatarAdapter(activity!!, bodyList)
        val shoesAdapter = WarehouseAvatarAdapter(activity!!, shoesList)

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

