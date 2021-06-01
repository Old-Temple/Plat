package com.example.plat

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Color
import android.graphics.Color.*
import android.os.Build
import android.os.Bundle
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.Toast
import androidx.annotation.RequiresApi
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentTransaction
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.bumptech.glide.Glide
import kotlinx.android.synthetic.main.fragment_funiture_put.*
import kotlinx.android.synthetic.main.plat_funiture.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//todo : 아이템이 먼저 터치되고 장소가 터치되는지 확인 위함
var funi_flag =0

//todo: 가구, 가구 지우기 위한 x버튼 들어있는 배열
var plat_funiture_put_areas_Difuni = arrayOfNulls<FrameLayout>(21)
var plat_funiture_button_Difuni = arrayOfNulls<Button>(21)
var plat_funiture_xbutton_Difuni = arrayOfNulls<Button>(21)
var plat_funiture_img_Difuni = arrayOfNulls<ImageView>(21)
object furniture {
    var url : String? = null
}

class DialogPutFragment(val mainActivity: MainActivity, val furnitures : MutableList<SeeItemQuery.SeeItem>?) : DialogFragment() {
    object a{
        var items = mutableListOf<SeeItemQuery.SeeItem>()
    }
    val userName = PlatPrefs.prefs.getValue("userName","")
    //todo: 가구 배치 위한 변수들
    val fun_num = 20
    var funiture_margin_top = 50
    var funiture_margin_start = 0
    var tempID_plat_funiture : Int = 0


    @SuppressLint("ServiceCast", "CutPasteId")
    @RequiresApi(Build.VERSION_CODES.JELLY_BEAN_MR1)
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val apolloClient = apolloClient(mainActivity.applicationContext)

        val view = inflater.inflate(R.layout.fragment_funiture_put, container, false)
        val btnClose = view.findViewById<Button>(R.id.btnwarehouseClose)
        val putOk = view.findViewById<Button>(R.id.put_ok)
        //todo : 가구 이미지 담기위한 임시배열
        //val fun_imgs = resources.obtainTypedArray(R.array.funi_imgs)
        //val fun_arys = resources.obtainTypedArray(R.array.funi_arys)
        replaceFragment(WarehouseCategoryFurnitureChange(furnitures).newInstance())



        btnClose.setOnClickListener { view ->

            dismiss()
        }


        for(i in 0..fun_num){
            //todo : 배경 이미지 간격 맞추느라 임시로 갖다놓은 이미지 [지워질친구]
            val funiture_put_root:ConstraintLayout = view.findViewById(R.id.funiture_put_root)
            //funiture_put_root.setBackgroundResource(R.drawable.grass_background)

            //todo : 가구 동적생성 하기위한 함수
            fun funiture_maker(): View {

                val plat_funiture = inflater.inflate(R.layout.plat_funiture_put, plat_funiture_area, false) as FrameLayout

                val plat_funiture_lp =
                    ConstraintLayout.LayoutParams(fromDpToPx(view.context, 80), fromDpToPx(view.context, 80))
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
            //todo : 배치 간격맞추기
            if(i%3==0){

                funiture_margin_start = fromDpToPx(view.context.applicationContext,9)
            }

            if(i%3==0 && i!=0){
                funiture_margin_top += fromDpToPx(view.context.applicationContext,50)

            }
            funiture_put_root.addView(funiture_maker())

            //todo : 나중에 편하게 쓰려고 가구랑 버튼들 배열에 넣어놓음
            plat_funiture_put_areas_Difuni[i] = view.findViewById(tempID_plat_funiture)
            funiture_margin_start += fromDpToPx(view.context.applicationContext,50)

            plat_funiture_button_Difuni[i] = plat_funiture_put_areas_Difuni[i]?.findViewById(R.id.funiture_put_bt)
            plat_funiture_xbutton_Difuni[i] = plat_funiture_put_areas_Difuni[i]?.findViewById(R.id.xButton)
            plat_funiture_img_Difuni[i] = plat_funiture_put_areas_Difuni[i]?.findViewById(R.id.funiture_img)
            plat_funiture_put_areas_Difuni[i]?.setBackgroundColor(YELLOW)

        }

        //todo : ex)아이템창 터치 확인(flag) 하고 가구 0번째 배열 터치하면 임시로 색 변화(후에 이미지로 교체)
        for(i in 0..20) {
            plat_funiture_button_Difuni[i]?.setOnClickListener {

                if (funi_flag == 1) {

                    plat_funiture_img_Difuni[i]?.setVisibility(View.VISIBLE)
                    plat_funiture_img_Difuni[i]?.let { it1 ->
                        Glide.with(view).load(furniture.url).into(
                            it1
                        )
                    }
                   // plat_funiture_put_areas_Difuni[i]?.setBackgroundResource(IdMaker(itemData?.itemInfo?.id.toString()))
                    plat_funiture_xbutton_Difuni[i]?.setVisibility(View.VISIBLE)
                    temp_view?.setBackgroundColor(0)

                    Toast.makeText(context, furniture.url.toString(), Toast.LENGTH_LONG).show()
                    val scope = CoroutineScope(Dispatchers.IO)

                    val groupId = mainActivity.clickedName


                    scope.launch {
                        val result: Response<PlaceItemMutation.Data> =
                            apolloClient.mutate(PlaceItemMutation(groupId = groupId, itemId = itemData?.itemId.toString(), grid = i)).await()

                        if(result.data?.placeItem?.ok == true) {
                            Log.d("AAQ", itemData?.itemInfo?.id.toString())

                        } else {
                            Log.d("aaa", result.data?.placeItem?.error.toString())
                        }
                    }
                    temp_view?.setBackgroundColor(0)

                    funi_flag = 0
                } else if (funi_flag == 0) {
                    Toast.makeText(view!!.context, "선택된 아이템이 없습니다", Toast.LENGTH_SHORT).show()

                }

            }
        }
//class SetItemFragment(String = itemData?.itemInfo?.id.toString() 받기) {
//
//}

//*************************************************************


      for (i in 0..fun_num){
            plat_funiture_xbutton_Difuni[i]?.setOnClickListener {
                plat_funiture_img_Difuni[i]?.setVisibility(View.INVISIBLE)
              plat_funiture_xbutton_Difuni[i]?.setVisibility(View.INVISIBLE)

                val scope = CoroutineScope(Dispatchers.IO)
                val groupId = mainActivity.clickedName
                scope.launch {
                    Log.d("RSGroupName", groupId)
                    val result: Response<RemoveItemMutation.Data> =
                        apolloClient.mutate(RemoveItemMutation(groupId = groupId, grid = i)).await()
                    Log.d("RS", result.data?.removeItem.toString())
                    if(result.data?.removeItem?.ok == true) {
                        Log.d("RS", "true")

                    } else {
                        Log.d("RS", result.data?.removeItem?.error.toString())
                    }
                }
      }



        }



        return view.rootView
    }

    private fun replaceFragment(fragment: Fragment){
        val fragmentTransactionListener: FragmentTransaction = childFragmentManager.beginTransaction()
        fragmentTransactionListener.replace(R.id.warehouseChildFragment_funchange, fragment)
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

    fun IdMaker (name : String? ) : Int{
        val my_res : Int = resources.getIdentifier(
            name,
            "drawable", activity!!.packageName
        )

        return my_res
    }

}
