package com.example.plat

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction

class DialogWarehouse : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_warehouse, container, false)

        val btnFurniture = view.findViewById<Button>(R.id.btnWarehouseFurniture)
        val btnThema = view.findViewById<Button>(R.id.btnWarehouseThema)
        val btnAvatar = view.findViewById<Button>(R.id.btnWarehouseAvatar)
        val btnClose = view.findViewById<Button>(R.id.btnwarehouseClose)

        btnFurniture.setOnClickListener { view ->
            replaceFragment(WarehouseCategoryFurniture().newInstance())
        }
        btnThema.setOnClickListener { view ->
            replaceFragment(WarehouseCategoryThema().newInstance())
        }
        btnAvatar.setOnClickListener { view ->
            replaceFragment(WarehouseCategoryAvatar().newInstance())
        }
        btnClose.setOnClickListener { view ->
            dismiss()
        }

        return view.rootView
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.warehouseChildFragment, fragment)
        fragmentTransactionListener.commit()
    }
}