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
    //todo : PutFunitureWarehouseCategories와 같이 리스트 어떻게 사용되는지 물어봐야하는부분
    private val bodyList = arrayListOf<ClothWarehouseAvatarItems>(
        ClothWarehouseAvatarItems("BImage1","Text1"),
        ClothWarehouseAvatarItems("BImage2","Text2"),
        ClothWarehouseAvatarItems("BImage3","Text3"),
        ClothWarehouseAvatarItems("BImage4","Text4"),
        ClothWarehouseAvatarItems("BImage5","Text5"),
        ClothWarehouseAvatarItems("BImage6","Text6"),
        ClothWarehouseAvatarItems("BImage7","Text7")
    )
    private val headList = arrayListOf<ClothWarehouseAvatarItems>(
        ClothWarehouseAvatarItems("HImage8","Text8"),
        ClothWarehouseAvatarItems("HImage9","Text9"),
        ClothWarehouseAvatarItems("HImage0","Text0"),
        ClothWarehouseAvatarItems("HImage9","Text9"),
        ClothWarehouseAvatarItems("HImage8","Text8"),
        ClothWarehouseAvatarItems("HImage7","Text7"),
        ClothWarehouseAvatarItems("HImage6","Text6"),
        ClothWarehouseAvatarItems("HImage5","Text5"),
        ClothWarehouseAvatarItems("hImage4","Text4"),
        ClothWarehouseAvatarItems("hImage3","Text3"),
        ClothWarehouseAvatarItems("hImage2","Text2")
    )
    private val shoesList = arrayListOf<ClothWarehouseAvatarItems>(
        ClothWarehouseAvatarItems("Image1","Text1"),
        ClothWarehouseAvatarItems("Image2","Text2"),
        ClothWarehouseAvatarItems("Image3","Text3"),
        ClothWarehouseAvatarItems("Image4","Text4"),
        ClothWarehouseAvatarItems("Image5","Text5")
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

