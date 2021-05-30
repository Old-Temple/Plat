package com.example.plat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import com.apollographql.apollo.ApolloClient
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.bumptech.glide.load.engine.Resource
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
        val btnFuniture: Button = view.findViewById(R.id.btnStoreFurniture)
        val btnThema: Button = view.findViewById(R.id.btnStoreThema)
        val btnAvatar: Button = view.findViewById(R.id.btnStoreAvatar)
        val btnMylist: Button = view.findViewById(R.id.btnStoreMylist)

        val apolloClient = apolloClient(mainActivity.applicationContext)
        val scope = CoroutineScope(Dispatchers.IO)

        btnFuniture.setOnClickListener { view ->
            scope.launch {
                val response : Response<SeeTypeQuery.Data> =
                    apolloClient.query(SeeTypeQuery("furniture")).await()

                val result : List<SeeTypeQuery.ItemInfo>? = response.data?.seeType?.itemInfos

                replaceFragment(StoreCategoryFurniture(result).newInstance())
            }
        }
        btnThema.setOnClickListener { view ->
            scope.launch {
                val response : Response<SeeTypeQuery.Data> =
                    apolloClient.query(SeeTypeQuery("furniture")).await()

                val result : List<SeeTypeQuery.ItemInfo>? = response.data?.seeType?.itemInfos

                replaceFragment(StoreCategoryThema(result).newInstance())
            }
        }
        btnAvatar.setOnClickListener { view ->
            scope.launch {
                val response : Response<SeeTypeQuery.Data> =
                    apolloClient.query(SeeTypeQuery("furniture")).await()

                val result : List<SeeTypeQuery.ItemInfo>? = response.data?.seeType?.itemInfos

                replaceFragment(StoreCategoryAvatar(result).newInstance())
            }
        }
        btnMylist.setOnClickListener { view ->
            scope.launch {
                val response : Response<SeeTypeQuery.Data> =
                    apolloClient.query(SeeTypeQuery("furniture")).await()

                val result : List<SeeTypeQuery.ItemInfo>? = response.data?.seeType?.itemInfos

                replaceFragment(StoreCategoryMylist(result).newInstance())
            }
        }


        return view
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.storeChileFragment, fragment)
        fragmentTransactionListener.commit()
    }
}
