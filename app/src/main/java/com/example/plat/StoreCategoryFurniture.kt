package com.example.plat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.GridView
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * Use the [StoreCategoryFurniture.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoreCategoryFurniture : Fragment() {
    fun newInstance(): Fragment {
        return StoreCategoryFurniture()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val myList = arrayListOf<StoreFurnitureItems>(
        StoreFurnitureItems("Image1","Text1"),
        StoreFurnitureItems("Image2","Text2"),
        StoreFurnitureItems("Image3","Text3"),
        StoreFurnitureItems("Image4","Text4"),
        StoreFurnitureItems("Image5","Text5"),
        StoreFurnitureItems("Image6","Text6"),
        StoreFurnitureItems("Image7","Text7"),
        StoreFurnitureItems("Image8","Text8"),
        StoreFurnitureItems("Image9","Text9"),
        StoreFurnitureItems("Image0","Text0"),
        StoreFurnitureItems("Image9","Text9"),
        StoreFurnitureItems("Image8","Text8"),
        StoreFurnitureItems("Image7","Text7"),
        StoreFurnitureItems("Image6","Text6"),
        StoreFurnitureItems("Image5","Text5"),
        StoreFurnitureItems("Image4","Text4"),
        StoreFurnitureItems("Image3","Text3"),
        StoreFurnitureItems("Image2","Text2"),
        StoreFurnitureItems("Image1","Text1"),
        StoreFurnitureItems("Image2","Text2"),
        StoreFurnitureItems("Image3","Text3"),
        StoreFurnitureItems("Image4","Text4"),
        StoreFurnitureItems("Image5","Text5")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store_category_furniture, null)
        val storeFurnitureListView = view.findViewById<GridView>(R.id.storeFurnitureGridView)
        val storeFurnitureAdapter = StoreFurnitureAdapter(activity!!, myList)

        storeFurnitureListView.adapter = storeFurnitureAdapter

        return view
    }
}

class StoreFurnitureItems(val image:String, val text:String)

class StoreFurnitureAdapter(val context: Context, val items: ArrayList<StoreFurnitureItems>):BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_furniture, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchFurniture)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchFurniture)
        val item = items[position]

        image.text = item.image
        text.text = item.text

        return view
    }

    override fun getCount(): Int {
        return items.size
    }

    override fun getItem(position: Int): Any {
        return items[position]
    }

    override fun getItemId(position: Int): Long {
        return 0
    }
}