package com.example.plat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Color.*
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_funiture_put.*
import kotlinx.android.synthetic.main.plat_funiture.*

var flag2 =0

class DialogChangeCloth : DialogFragment() {
    val fun_num = 20
    var funiture_margin_top = 50
    var funiture_margin_start = 0
    var tempID_plat_funiture : Int = 0
    var plat_funiture_put_areas = arrayOfNulls<FrameLayout>(21) //plat 가구 들어있는 배열
    var plat_funiture_button = arrayOfNulls<Button>(21)
    var plat_funiture_xbutton = arrayOfNulls<Button>(21)

    @SuppressLint("ServiceCast", "CutPasteId")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_change_cloth, container, false)
        val btnThema = view.findViewById<Button>(R.id.btnWarehouseThema)
        val btnAvatar = view.findViewById<Button>(R.id.btnWarehouseAvatar)
        val btnClose = view.findViewById<Button>(R.id.btnwarehouseClose)

        replaceFragment(WarehouseCategoryFurniture().newInstance())

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
        fragmentTransactionListener.replace(R.id.warehouseChildFragment3, fragment)
        fragmentTransactionListener.commit()
    }
}
