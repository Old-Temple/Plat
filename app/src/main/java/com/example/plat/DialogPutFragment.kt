package com.example.plat

import android.annotation.SuppressLint
import android.content.Context
import android.os.Build
import android.os.Bundle
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import kotlinx.android.synthetic.main.fragment_funiture_put.*
import kotlinx.android.synthetic.main.plat_funiture.*

var flag =0

class DialogPutFragment : DialogFragment() {
    val fun_num = 20
    var funiture_margin_top = 50
    var funiture_margin_start = 0
    var tempID_plat_funiture : Int = 0
    var plat_funiture_put_areas = arrayOfNulls<FrameLayout>(21) //plat 가구 들어있는 배열
    var plat_funiture_button = arrayOfNulls<Button>(21)
    var plat_funiture_xbutton = arrayOfNulls<Button>(21)
    var flag = 0
    @SuppressLint("ServiceCast", "CutPasteId")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_funiture_put, container, false)

        val btnFurniture = view.findViewById<Button>(R.id.btnWarehouseFurniture)
        val btnThema = view.findViewById<Button>(R.id.btnWarehouseThema)
        val btnAvatar = view.findViewById<Button>(R.id.btnWarehouseAvatar)
        val btnClose = view.findViewById<Button>(R.id.btnwarehouseClose)

        replaceFragment(WarehouseCategoryFurnitureChange().newInstance())


        btnClose.setOnClickListener { view ->
            dismiss()
        }


        if (flag==1){

        }
        for(i in 0..fun_num){
            val funiture_put_root:ConstraintLayout = view.findViewById(R.id.funiture_put_root)
            funiture_put_root.setBackgroundResource(R.drawable.grass_background)





            fun funiture_maker(): View {

                val plat_funiture = inflater.inflate(R.layout.plat_funiture_put, plat_funiture_area, false) as FrameLayout

                val plat_funiture_lp =
                    ConstraintLayout.LayoutParams(fromDpToPx(view.context, 70), fromDpToPx(view.context, 70))
                plat_funiture_lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                plat_funiture_lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                plat_funiture_lp.topMargin = fromDpToPx(view.context, funiture_margin_top)
                plat_funiture_lp.marginStart = fromDpToPx(view.context, funiture_margin_start)
                // binding.cardViews.setLayoutParams(layoutParams)

                plat_funiture.layoutParams = plat_funiture_lp
                tempID_plat_funiture = getResources().getIdentifier(
                    "plat_funiture_put_area_" + (i),
                    "id", activity!!.packageName
                )
                plat_funiture.setId(tempID_plat_funiture)

                return plat_funiture

            }
            if(i%3==0){

                funiture_margin_start = fromDpToPx(view.context.applicationContext,9)
            }

            if(i%3==0 && i!=0){
                funiture_margin_top += fromDpToPx(view.context.applicationContext,50)

            }
            funiture_put_root.addView(funiture_maker())
            plat_funiture_put_areas[i] = view.findViewById(tempID_plat_funiture)
            funiture_margin_start += fromDpToPx(view.context.applicationContext,50)

            plat_funiture_button[i] = plat_funiture_put_areas[i]?.findViewById(R.id.funiture_put_bt)
            plat_funiture_xbutton[i] = plat_funiture_put_areas[i]?.findViewById(R.id.xButton)


        }






        return view.rootView
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.warehouseChildFragment2, fragment)
        fragmentTransactionListener.commit()
    }

    private fun fromDpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        )
            .toInt()
    }

}
