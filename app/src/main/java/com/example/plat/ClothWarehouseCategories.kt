package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*



class ClothWarehouseCategoryAvatar : Fragment() {
    fun newInstance(): Fragment{
        return ClothWarehouseCategoryAvatar()
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
        val headAdapter = ClothWarehouseAvatarAdapter(activity!!, headList)
        val bodyAdapter = ClothWarehouseAvatarAdapter(activity!!, bodyList)
        val shoesAdapter = ClothWarehouseAvatarAdapter(activity!!, shoesList)

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

