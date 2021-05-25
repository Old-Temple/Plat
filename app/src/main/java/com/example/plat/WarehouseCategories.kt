package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

class WarehouseCategoryFurniture : Fragment() {
    // TODO: Rename and change types of parameters
    fun newInstance(): Fragment {
        return WarehouseCategoryFurniture()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_furniture, null)

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_thema, null)

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

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view:View = inflater.inflate(R.layout.fragment_warehouse_category_avatar, null)

        return view
    }
}