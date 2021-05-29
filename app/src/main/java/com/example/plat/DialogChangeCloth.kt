package com.example.plat

import android.annotation.SuppressLint
import android.graphics.Color.*
import android.os.Build
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
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


        val view = inflater.inflate(R.layout.fragment_dialog_change_cloth, container, false)


        val view = inflater.inflate(R.layout.fragment_dialog_change_cloth, container, false)
        val btnThema = view.findViewById<Button>(R.id.btnWarehouseThema)
        val btnAvatar = view.findViewById<Button>(R.id.btnWarehouseAvatar)
        val btnClose = view.findViewById<Button>(R.id.btnwarehouseClose)

        //todo: 캐릭터 부위별 버튼
        val head = view.findViewById<Button>(R.id.head)
        val body = view.findViewById<Button>(R.id.body)
        val shose = view.findViewById<Button>(R.id.shose)
        val head_xbutton = view.findViewById<Button>(R.id.head_xbox)
        val body_xbutton = view.findViewById<Button>(R.id.body_xbox)
        val shose_xbutton = view.findViewById<Button>(R.id.shose_xbox)

        //todo : 임시로 색 변하게 해놓음. 나중에 이미지로 바뀌게 고쳐야함함
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
        //todo : 머리에 끼워진 아이템 지우고 싶을때
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
