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
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_funiture_put.*
import kotlinx.android.synthetic.main.plat_funiture.*

var headflag =0

class DialogChangeCloth : DialogFragment() {

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

        val head = view.findViewById<Button>(R.id.head)
        val body = view.findViewById<Button>(R.id.body)
        val shose = view.findViewById<Button>(R.id.shose)
        val head_xbutton = view.findViewById<Button>(R.id.head_xbox)
        val body_xbutton = view.findViewById<Button>(R.id.body_xbox)
        val shose_xbutton = view.findViewById<Button>(R.id.shose_xbox)

        head.setBackgroundColor(YELLOW)
        head.setOnClickListener {

            if (headflag == 1) {

                //백에서 ary 값 받아와서!
                head.setBackgroundColor(RED)
                head_xbutton.visibility = View.VISIBLE
                head_temp_view?.setBackgroundColor(0)

                headflag = 0
            }
            else if(headflag==0){
                Toast.makeText(view!!.context, "선택된 아이템이 없습니다", Toast.LENGTH_SHORT).show()

            }

        }

        head_xbutton.setOnClickListener {
            //삭제

            head.setBackgroundColor(YELLOW)
            head_xbutton.visibility = View.INVISIBLE


        }


        replaceFragment(ClothWarehouseCategoryAvatar().newInstance())
        btnAvatar.setOnClickListener { view ->
            replaceFragment(ClothWarehouseCategoryAvatar().newInstance())
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
