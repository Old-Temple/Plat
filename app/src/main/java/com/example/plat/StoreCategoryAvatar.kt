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
import kotlinx.android.synthetic.main.fragment_store_category_avatar.*

/**
 * A simple [Fragment] subclass.
 * Use the [StoreCategoryAvatar.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoreCategoryAvatar : Fragment() {

    fun newInstance(): Fragment {
        return StoreCategoryAvatar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    private val bodyList = arrayListOf<StoreAvatarItems>(
        StoreAvatarItems("BImage1","Text1"),
        StoreAvatarItems("BImage2","Text2"),
        StoreAvatarItems("BImage3","Text3"),
        StoreAvatarItems("BImage4","Text4"),
        StoreAvatarItems("BImage5","Text5"),
        StoreAvatarItems("BImage6","Text6"),
        StoreAvatarItems("BImage7","Text7")
    )
    private val headList = arrayListOf<StoreAvatarItems>(
        StoreAvatarItems("HImage8","Text8"),
        StoreAvatarItems("HImage9","Text9"),
        StoreAvatarItems("HImage0","Text0"),
        StoreAvatarItems("HImage9","Text9"),
        StoreAvatarItems("HImage8","Text8"),
        StoreAvatarItems("HImage7","Text7"),
        StoreAvatarItems("HImage6","Text6"),
        StoreAvatarItems("HImage5","Text5"),
        StoreAvatarItems("hImage4","Text4"),
        StoreAvatarItems("hImage3","Text3"),
        StoreAvatarItems("hImage2","Text2")
    )
    private val shoesList = arrayListOf<StoreAvatarItems>(
        StoreAvatarItems("Image1","Text1"),
        StoreAvatarItems("Image2","Text2"),
        StoreAvatarItems("Image3","Text3"),
        StoreAvatarItems("Image4","Text4"),
        StoreAvatarItems("Image5","Text5")
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store_category_avatar, container, false)
        val gridView = view.findViewById<GridView>(R.id.storeAvatarGridView)

        val btnBody: Button = view.findViewById(R.id.btnBody)
        val btnHead: Button = view.findViewById(R.id.btnHead)
        val btnShoes: Button = view.findViewById(R.id.btnShoes)

        val adapter = StoreAvatarAdapter(activity!!, headList)
        gridView.adapter = adapter

        btnBody.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(activity!!, bodyList)
            gridView.adapter = adapter
        }
        btnHead.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(activity!!, headList)
            gridView.adapter = adapter
        }
        btnShoes.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(activity!!, shoesList)
            gridView.adapter = adapter
        }

        return view
    }
}


class StoreAvatarItems(val image:String, val text:String)

class StoreAvatarAdapter(val context: Context, val items: ArrayList<StoreAvatarItems>):
    BaseAdapter(){
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        val view: View = LayoutInflater.from(context).inflate(R.layout.list_item_store_avatar, null)

        val image = view.findViewById<TextView>(R.id.imageOfStoreSearchAvatar)
        val text = view.findViewById<TextView>(R.id.textOfStoreSearchAvatar)
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