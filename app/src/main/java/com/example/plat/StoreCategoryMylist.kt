package com.example.plat

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Button
import android.widget.GridView
import android.widget.TextView

/**
 * A simple [Fragment] subclass.
 * Use the [StoreCategoryMylist.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoreCategoryMylist : Fragment() {
    // TODO
    fun newInstance(): Fragment {
        return StoreCategoryMylist()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val furnitureList = arrayListOf<StoreMyListItems>(
        StoreMyListItems("FurnitureImage1","Text1"),
        StoreMyListItems("FurnitureImage2","Text2"),
        StoreMyListItems("FurnitureImage3","Text3"),
        StoreMyListItems("FurnitureImage4","Text4"),
        StoreMyListItems("FurnitureImage5","Text5"),
        StoreMyListItems("FurnitureImage6","Text6"),
        StoreMyListItems("FurnitureImage7","Text7")
    )
    private val themaList = arrayListOf<StoreMyListItems>(
        StoreMyListItems("ThemaImage8","Text8"),
        StoreMyListItems("ThemaImage9","Text9"),
        StoreMyListItems("ThemaImage0","Text0"),
        StoreMyListItems("ThemaImage9","Text9"),
        StoreMyListItems("ThemaImage8","Text8"),
        StoreMyListItems("ThemaImage7","Text7"),
        StoreMyListItems("ThemaImage6","Text6"),
        StoreMyListItems("ThemaImage5","Text5"),
        StoreMyListItems("ThemaImage4","Text4"),
        StoreMyListItems("ThemaImage3","Text3"),
        StoreMyListItems("ThemaImage2","Text2")
    )
    private val avatarList = arrayListOf<StoreMyListItems>(
        StoreMyListItems("AvatarImage1","Text1"),
        StoreMyListItems("AvatarImage2","Text2"),
        StoreMyListItems("AvatarImage3","Text3"),
        StoreMyListItems("AvatarImage4","Text4"),
        StoreMyListItems("AvatarImage5","Text5")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store_category_mylist, container, false)
        val gridView = view.findViewById<GridView>(R.id.storeMyListGridView)

        val btnFurniture: Button = view.findViewById(R.id.btnFurniture)
        val btnThema: Button = view.findViewById(R.id.btnThema)
        val btnAvatar: Button = view.findViewById(R.id.btnAvatar)

        val adapter = StoreMyListAdapter(activity!!, furnitureList)
        gridView.adapter = adapter

        btnFurniture.setOnClickListener { view ->
            val adapter = StoreMyListAdapter(activity!!, furnitureList)
            gridView.adapter = adapter
        }
        btnThema.setOnClickListener { view ->
            val adapter = StoreMyListAdapter(activity!!, themaList)
            gridView.adapter = adapter
        }
        btnAvatar.setOnClickListener { view ->
            val adapter = StoreMyListAdapter(activity!!, avatarList)
            gridView.adapter = adapter
        }

        return view
    }
}


class StoreMyListItems(val image:String, val text:String)

class StoreMyListAdapter(val context: Context, val items: ArrayList<StoreMyListItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_mylist, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchMyList)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchMyList)
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