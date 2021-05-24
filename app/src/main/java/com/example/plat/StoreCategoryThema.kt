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

class StoreCategoryThema : Fragment() {
    // TODO
    fun newInstance(): Fragment {
        return StoreCategoryThema()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val myList = arrayListOf<StoreThemaItems>(
        StoreThemaItems("ThemaImage1","Text1"),
        StoreThemaItems("ThemaImage2","Text2"),
        StoreThemaItems("ThemaImage3","Text3"),
        StoreThemaItems("ThemaImage4","Text4"),
        StoreThemaItems("ThemaImage5","Text5"),
        StoreThemaItems("ThemaImage6","Text6"),
        StoreThemaItems("ThemaImage7","Text7"),
        StoreThemaItems("ThemaImage8","Text8"),
        StoreThemaItems("ThemaImage9","Text9"),
        StoreThemaItems("ThemaImage0","Text0"),
        StoreThemaItems("ThemaImage9","Text9"),
        StoreThemaItems("ThemaImage8","Text8"),
        StoreThemaItems("ThemaImage7","Text7"),
        StoreThemaItems("ThemaImage6","Text6"),
        StoreThemaItems("ThemaImage5","Text5"),
        StoreThemaItems("ThemaImage4","Text4"),
        StoreThemaItems("ThemaImage3","Text3"),
        StoreThemaItems("ThemaImage2","Text2"),
        StoreThemaItems("ThemaImage1","Text1"),
        StoreThemaItems("ThemaImage2","Text2"),
        StoreThemaItems("ThemaImage3","Text3"),
        StoreThemaItems("ThemaImage4","Text4"),
        StoreThemaItems("ThemaImage5","Text5")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store_category_thema, container, false)
        val gridView = view.findViewById<GridView>(R.id.storeThemaGridView)
        val adapter = StoreThemaAdapter(activity!!, myList)

        gridView.adapter = adapter

        return view
    }
}

class StoreThemaItems(val image:String, val text:String)

class StoreThemaAdapter(val context: Context, val items: ArrayList<StoreThemaItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_thema, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchThema)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchThema)
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