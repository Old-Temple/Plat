package com.example.plat

import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Button
import android.widget.GridView
import android.widget.Toast
import androidx.annotation.RequiresApi
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * A simple [Fragment] subclass.
 * Use the [StoreCategoryFurniture.newInstance] factory method to
 * create an instance of this fragment.
 */
class StoreCategoryFurniture(val mainActivity: MainActivity, val list: List<SeeTypeQuery.ItemInfo>?) : Fragment() {
    //todo : 스토어 가구 목록
    fun newInstance(): Fragment {
        return StoreCategoryFurniture(mainActivity, list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store_category_furniture, null)
        val storeFurnitureListView = view.findViewById<GridView>(R.id.storeFurnitureGridView)
        val storeFurnitureAdapter = StoreFurnitureAdapter(mainActivity, this, activity!!, list)

        storeFurnitureListView.adapter = storeFurnitureAdapter

        return view
    }
}

class StoreCategoryThema(val mainActivity: MainActivity, val list: List<SeeTypeQuery.ItemInfo>?) : Fragment() {
    // todo : 스토어 테마 목록임
    fun newInstance(): Fragment {
        return StoreCategoryThema(mainActivity, list)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_store_category_thema, container, false)
        val gridView = view.findViewById<GridView>(R.id.storeThemaGridView)
        val adapter = StoreThemaAdapter(mainActivity, this, activity!!, list)

        gridView.adapter = adapter

        return view
    }
}

class StoreCategoryAvatar(
    val mainActivity: MainActivity,
    val headlist: List<SeeTypeQuery.ItemInfo>?,
    val bodylist: List<SeeTypeQuery.ItemInfo>?,
    val legList: List<SeeTypeQuery.ItemInfo>?)
    : Fragment() {

    fun newInstance(): Fragment {
        return StoreCategoryAvatar(mainActivity, headlist, bodylist, legList)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

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

        val adapter = StoreAvatarAdapter(mainActivity, this, activity!!, headlist)
        gridView.adapter = adapter

        btnBody.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(mainActivity, this, activity!!, bodylist)
            gridView.adapter = adapter
        }
        btnHead.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(mainActivity, this, activity!!, headlist)
            gridView.adapter = adapter
        }
        btnShoes.setOnClickListener { view ->
            val adapter = StoreAvatarAdapter(mainActivity, this, activity!!, legList)
            gridView.adapter = adapter
        }

        return view
    }
}