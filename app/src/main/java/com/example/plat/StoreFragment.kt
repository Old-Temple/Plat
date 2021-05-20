package com.example.plat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction


/**
 * A simple [Fragment] subclass.
 * 스토어 화면
 */
class StoreFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_store, null)
        val btn1: Button = view.findViewById(R.id.buttonFurniture)
        val btn2: Button = view.findViewById(R.id.buttonThema)
        val btn3: Button = view.findViewById(R.id.buttonAvatar)
        val btn4: Button = view.findViewById(R.id.buttonMylist)

        btn1.setOnClickListener { view ->
            replaceFragment(StoreCategoryFurniture().newInstance())
        }
        btn2.setOnClickListener { view ->
            replaceFragment(StoreCategoryThema().newInstance())
        }
        btn3.setOnClickListener { view ->
            replaceFragment(StoreCategoryAvatar().newInstance())
        }
        btn4.setOnClickListener { view ->
            replaceFragment(StoreCategoryMylist().newInstance())
        }
        return view
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.chileFragment, fragment)
        fragmentTransactionListener.commit()
    }
}
