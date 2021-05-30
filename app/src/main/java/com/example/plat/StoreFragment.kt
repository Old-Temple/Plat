package com.example.plat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


/**
 * A simple [Fragment] subclass.
 * 스토어 화면
 */
class StoreFragment(val mainActivity: MainActivity) : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_store, null)
        val btn1: Button = view.findViewById(R.id.btnStoreFurniture)
        val btn2: Button = view.findViewById(R.id.btnStoreThema)
        val btn3: Button = view.findViewById(R.id.btnStoreAvatar)
        val btn4: Button = view.findViewById(R.id.btnStoreMylist)

        replaceFragment(StoreCategoryFurniture().newInstance())

        btn1.setOnClickListener { view ->
            loadItems(btn1.id)
        }
        btn2.setOnClickListener { view ->
            loadItems(btn2.id)
        }
        btn3.setOnClickListener { view ->
            loadItems(btn3.id)
        }
        btn4.setOnClickListener { view ->
            loadItems(btn4.id)
        }


        return view
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.storeChileFragment, fragment)
        fragmentTransactionListener.commit()
    }

    private fun loadItems(id : Int){
        val apolloClient = apolloClient(mainActivity.applicationContext)

        val scope = CoroutineScope(Dispatchers.IO)
        scope.launch {
//            val response : List<> = scope.query().await()
//
//            result = response.data?.
//
            if (id == R.id.btnStoreFurniture){
                replaceFragment(StoreCategoryFurniture().newInstance())
            }
            else if (id == R.id.btnStoreThema){
                replaceFragment(StoreCategoryThema().newInstance())
            }
            else if (id == R.id.btnStoreAvatar){
                replaceFragment(StoreCategoryAvatar().newInstance())
            }
            else{
                replaceFragment(StoreCategoryMylist().newInstance())
            }
        }
    }
}
