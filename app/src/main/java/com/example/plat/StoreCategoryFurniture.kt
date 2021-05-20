package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

/**
 * A simple [Fragment] subclass.
 * Use the [StoreCategoryFurniture.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoreCategoryFurniture : Fragment() {
    //todo
    fun newInstance(): Fragment {
        return StoreCategoryFurniture()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store_category_furniture, container, false)

        return view
    }
}