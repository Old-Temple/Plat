package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.GridView

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
        val storeFurnitureAdapter = StoreFurnitureAdapter(this, activity!!, myList)

        storeFurnitureListView.adapter = storeFurnitureAdapter

        return view
    }
}

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
        val adapter = StoreThemaAdapter(this, activity!!, myList)

        gridView.adapter = adapter

        return view
    }
}

class StoreCategoryAvatar : Fragment() {
    //todo
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

        val btnBody: Button = view.findViewById(R.id.btnStoreAvatarBody)
        val btnHead: Button = view.findViewById(R.id.btnStoreAvatarHead)
        val btnShoes: Button = view.findViewById(R.id.btnStoreAvatarShoes)

        val adapter = StoreAvatarAdapter(this, activity!!, headList)
        gridView.adapter = adapter

        btnBody.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(this, activity!!, bodyList)
            gridView.adapter = adapter
        }
        btnHead.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(this, activity!!, headList)
            gridView.adapter = adapter
        }
        btnShoes.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(this, activity!!, shoesList)
            gridView.adapter = adapter
        }

        return view
    }
}

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

        val btnFurniture: Button = view.findViewById(R.id.btnStoreMylistFurniture)
        val btnThema: Button = view.findViewById(R.id.btnStoreMylistThema)
        val btnAvatar: Button = view.findViewById(R.id.btnStoreMylistAvatar)

        val adapter = StoreMyListAdapter(this, activity!!, furnitureList)
        gridView.adapter = adapter

        btnFurniture.setOnClickListener { view ->
            val adapter = StoreMyListAdapter(this, activity!!, furnitureList)
            gridView.adapter = adapter
        }
        btnThema.setOnClickListener { view ->
            val adapter = StoreMyListAdapter(this, activity!!, themaList)
            gridView.adapter = adapter
        }
        btnAvatar.setOnClickListener { view ->
            val adapter = StoreMyListAdapter(this, activity!!, avatarList)
            gridView.adapter = adapter
        }

        return view
    }
}