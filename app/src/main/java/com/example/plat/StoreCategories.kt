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
    //todo : 스토어 가구 목록
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
    ): View {
        val list = loadItems()
        val view: View = inflater.inflate(R.layout.fragment_store_category_furniture, null)
        val storeFurnitureListView = view.findViewById<GridView>(R.id.storeFurnitureGridView)
        val storeFurnitureAdapter = StoreFurnitureAdapter(this, activity!!, list)

        storeFurnitureListView.adapter = storeFurnitureAdapter

        return view
    }

    fun loadItems():ArrayList<StoreItems>{
        //todo : 서버에서 가구 리스트 불러와야함
        val furnitureList = arrayListOf<StoreItems>(
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
        return furnitureList
    }
}

class StoreCategoryThema : Fragment() {
    // todo : 스토어 테마 목록임
    fun newInstance(): Fragment {
        return StoreCategoryThema()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val list = loadItems()
        val view: View = inflater.inflate(R.layout.fragment_store_category_thema, container, false)
        val gridView = view.findViewById<GridView>(R.id.storeThemaGridView)
        val adapter = StoreThemaAdapter(this, activity!!, list)

        gridView.adapter = adapter

        return view
    }

    fun loadItems():ArrayList<StoreItems>{
        //todo : 서버에서 테마 리스트 불러와야함
        val themaList = arrayListOf<StoreItems>(
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

        return themaList
    }
}

class StoreCategoryAvatar : Fragment() {
    //todo : 스토어 아바타 목록
    final val head = 0
    final val body = 1
    final val shoes = 2


    fun newInstance(): Fragment {
        return StoreCategoryAvatar()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var list = loadItems(head)
        val view: View = inflater.inflate(R.layout.fragment_store_category_avatar, container, false)
        val gridView = view.findViewById<GridView>(R.id.storeAvatarGridView)

        val btnBody: Button = view.findViewById(R.id.btnStoreAvatarBody)
        val btnHead: Button = view.findViewById(R.id.btnStoreAvatarHead)
        val btnShoes: Button = view.findViewById(R.id.btnStoreAvatarShoes)

        val adapter = StoreAvatarAdapter(this, activity!!, list)
        gridView.adapter = adapter

        btnBody.setOnClickListener { view ->
            list = loadItems(body)
            val adapter = StoreAvatarAdapter(this, activity!!, list)
            gridView.adapter = adapter
        }
        btnHead.setOnClickListener { view ->
            list = loadItems(head)
            val adapter = StoreAvatarAdapter(this, activity!!, list)
            gridView.adapter = adapter
        }
        btnShoes.setOnClickListener { view ->
            list = loadItems(shoes)
            val adapter = StoreAvatarAdapter(this, activity!!, list)
            gridView.adapter = adapter
        }

        return view
    }

    fun loadItems(type:Int):ArrayList<StoreItems>{
        //todo : 서버에서 부위별 아바타 리스트 불러와야함
        var list = arrayListOf<StoreItems>()
        if(type == body) {
            list = arrayListOf<StoreItems>(
                StoreAvatarItems("BImage1", "Text1"),
                StoreAvatarItems("BImage2", "Text2"),
                StoreAvatarItems("BImage3", "Text3"),
                StoreAvatarItems("BImage4", "Text4"),
                StoreAvatarItems("BImage5", "Text5"),
                StoreAvatarItems("BImage6", "Text6"),
                StoreAvatarItems("BImage7", "Text7")
            )
        }
        else if (type == head) {
            list = arrayListOf<StoreItems>(
                StoreAvatarItems("HImage8", "Text8"),
                StoreAvatarItems("HImage9", "Text9"),
                StoreAvatarItems("HImage0", "Text0"),
                StoreAvatarItems("HImage9", "Text9"),
                StoreAvatarItems("HImage8", "Text8"),
                StoreAvatarItems("HImage7", "Text7"),
                StoreAvatarItems("HImage6", "Text6"),
                StoreAvatarItems("HImage5", "Text5"),
                StoreAvatarItems("hImage4", "Text4"),
                StoreAvatarItems("hImage3", "Text3"),
                StoreAvatarItems("hImage2", "Text2")
            )
        }
        else if (type == shoes) {
            list = arrayListOf<StoreItems>(
                StoreAvatarItems("Image1", "Text1"),
                StoreAvatarItems("Image2", "Text2"),
                StoreAvatarItems("Image3", "Text3"),
                StoreAvatarItems("Image4", "Text4"),
                StoreAvatarItems("Image5", "Text5")
            )
        }

        return list
    }
}

class StoreCategoryMylist : Fragment() {
    // todo : 스토어의 마이 리스트 항목

    final val furniture = 0
    final val avatar = 1
    final val thema = 2

    fun newInstance(): Fragment {
        return StoreCategoryMylist()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        var list = loadItems(furniture)
        val view: View = inflater.inflate(R.layout.fragment_store_category_mylist, container, false)
        val gridView = view.findViewById<GridView>(R.id.storeMyListGridView)

        val btnFurniture: Button = view.findViewById(R.id.btnStoreMylistFurniture)
        val btnThema: Button = view.findViewById(R.id.btnStoreMylistThema)
        val btnAvatar: Button = view.findViewById(R.id.btnStoreMylistAvatar)

        val adapter = StoreMyListAdapter(this, activity!!, list)
        gridView.adapter = adapter

        btnFurniture.setOnClickListener { view ->
            list = loadItems(furniture)
            val adapter = StoreMyListAdapter(this, activity!!, list)
            gridView.adapter = adapter
        }
        btnThema.setOnClickListener { view ->
            list = loadItems(thema)
            val adapter = StoreMyListAdapter(this, activity!!, list)
            gridView.adapter = adapter
        }
        btnAvatar.setOnClickListener { view ->
            list = loadItems(avatar)
            val adapter = StoreMyListAdapter(this, activity!!, list)
            gridView.adapter = adapter
        }

        return view
    }

    fun loadItems(type:Int):ArrayList<StoreItems>{
        //todo : 서버에서 항목에 맞는 찜목록 불러와야함
        var list = arrayListOf<StoreItems>()
        if (type == furniture) {
            list = arrayListOf<StoreItems>(
                StoreMyListItems("FurnitureImage1", "Text1"),
                StoreMyListItems("FurnitureImage2", "Text2"),
                StoreMyListItems("FurnitureImage3", "Text3"),
                StoreMyListItems("FurnitureImage4", "Text4"),
                StoreMyListItems("FurnitureImage5", "Text5"),
                StoreMyListItems("FurnitureImage6", "Text6"),
                StoreMyListItems("FurnitureImage7", "Text7")
            )
        }
        else if (type == thema) {
            list = arrayListOf<StoreItems>(
                StoreMyListItems("ThemaImage8", "Text8"),
                StoreMyListItems("ThemaImage9", "Text9"),
                StoreMyListItems("ThemaImage0", "Text0"),
                StoreMyListItems("ThemaImage9", "Text9"),
                StoreMyListItems("ThemaImage8", "Text8"),
                StoreMyListItems("ThemaImage7", "Text7"),
                StoreMyListItems("ThemaImage6", "Text6"),
                StoreMyListItems("ThemaImage5", "Text5"),
                StoreMyListItems("ThemaImage4", "Text4"),
                StoreMyListItems("ThemaImage3", "Text3"),
                StoreMyListItems("ThemaImage2", "Text2")
            )
        }
        else if (type == avatar) {
            list = arrayListOf<StoreItems>(
                StoreMyListItems("AvatarImage1", "Text1"),
                StoreMyListItems("AvatarImage2", "Text2"),
                StoreMyListItems("AvatarImage3", "Text3"),
                StoreMyListItems("AvatarImage4", "Text4"),
                StoreMyListItems("AvatarImage5", "Text5")
            )
        }

        return list
    }
}