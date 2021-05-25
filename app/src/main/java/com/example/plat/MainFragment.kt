package com.example.plat

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.drawable.Drawable
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.util.Log
import android.util.TypedValue
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.annotation.RequiresApi
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.content.res.AppCompatResources.getDrawable
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat.getSystemService
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.fragment_main.*
import kotlinx.android.synthetic.main.list_item_plat_list.view.*
import kotlinx.android.synthetic.main.plat_funiture.*
import java.util.*
import kotlin.collections.ArrayList
import androidx.fragment.app.FragmentTransaction

/**
 * A simple [Fragment] subclass.
 * 메인화면
 */
class MainFragment : Fragment() {
    //임시로 만든 arraylist
    private val myList = arrayListOf<PlatListItem>(
        PlatListItem(1),
        PlatListItem(2),
        PlatListItem(3),
        PlatListItem(4),
        PlatListItem(5),
        PlatListItem(6),
        PlatListItem(7),
        PlatListItem(8),
        PlatListItem(9),
        PlatListItem(10),
        PlatListItem(11),
        PlatListItem(12),
        PlatListItem(13)
    )

    val cha_num = 5 //캐릭터 수-1
    val fun_num = 20 // 가구 수 -1

    var k = 0 // 애니메이션 for 문에 필요한 변수

    var dirx = 0f //캐릭터 이동위한 x
    var diry = 0f // 캐릭터 이동위한 y

    //쓰레드 돌리기 위한 변수
    var handler1 = Handler()
    var handler2: Handler = Handler()
    var handler3 = Handler()
    var handler4 = Handler()
    var runnable: Runnable = Runnable {}
    //지연 함수


    var cha_run_motion = java.util.ArrayList<Drawable?>() //캐릭터 움직임 애니메이션 위한 배열

    //캐릭터 파츠 객체 담기위한 변수
    var cha_capsule = arrayOfNulls<FrameLayout>(6)
    var tooltip = arrayOfNulls<ImageView>(6)
    var tooltip_text = arrayOfNulls<TextView>(6)
    var like_button = arrayOfNulls<Button>(6)
    var cha_bundle = arrayOfNulls<FrameLayout>(6)
    var head = arrayOfNulls<ImageView>(6)
    var body = arrayOfNulls<ImageView>(6)
    var shose = arrayOfNulls<ImageView>(6)

    var flat_funiture_areas = arrayOfNulls<FrameLayout>(21) //plat 가구 들어있는 배열


    // ID값 부여 변수*****************************************
    var tempID_head: Int = 0
    var tempID_body: Int = 0

    var tempID_shose: Int = 0
    var tempID_bundle_capsule: Int = 0
    var tempID_tooltip: Int = 0
    var tempID_tooltext: Int = 0
    var tempID_likebutton: Int = 0
    var flat_funiture_button_ID: Int = 0 // plat 가구 임시 ID
    var tempID_character_bundle: Int = 0
    var tempID_shose_moving1 = 0
    var tempID_plat_funiture : Int = 0
    var tempwidth : Int = 0  // 캐릭터와 가구 충돌조절변수 - 가로
    var tempheight : Int = 0 // 캐릭터와 가구 충돌조절변수 - 세로

    //움직임 위한 각 캐릭터 신발 배열 *************************************
    var character_shose_moving1 = java.util.ArrayList<Drawable?>()
    var character_shose_moving2 = java.util.ArrayList<Drawable?>()

    @SuppressLint("ServiceCast", "CutPasteId")
    @RequiresApi(Build.VERSION_CODES.LOLLIPOP)

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        val view: View = inflater.inflate(R.layout.fragment_main, null)
        val layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        val platlistView = view.findViewById<RecyclerView>(R.id.platListView)
        platlistView.layoutManager = layoutManager
        val platListAdapter = PlatListAdapter(myList)

        platlistView.adapter = platListAdapter
        val mainWriteButton = view.findViewById<Button>(R.id.mainWriteButton)

        mainWriteButton.setOnClickListener{ view ->
            val makeContext = DialogMakeContext()
            makeContext.show(childFragmentManager.beginTransaction(), makeContext.tag)
        }
        val tempGoFuniturePut = view.findViewById<Button>(R.id.tempGoFuniturePut)
        tempGoFuniturePut.setOnClickListener{ view ->
            val makePutFragment = DialogPutFragment()
            makePutFragment.show(childFragmentManager.beginTransaction(), makePutFragment.tag)
        }


        tempwidth =  fromDpToPx(activity!!, 30)
        tempheight =  fromDpToPx(activity!!, 60)


        ////////////////////////////
        val fun_arys_flat = resources.obtainTypedArray(R.array.funi_arys_plat) // arrays에서 가져오기위함

        ///////////////////

        //************************************

        //****************************************

        /* if(가구에 0 있으면 생성하고 위치 맞춰서..){

         }
 */

        //각 걷는 배열에 맞는거 넣기//
        var temp_shosemoving_ID :Int = 0




        for (i in 1..12){

            if(i%2==0){
                temp_shosemoving_ID = 0
            }
            else{
                temp_shosemoving_ID = 1
            }
            when (temp_shosemoving_ID){
                0-> {
                    character_shose_moving1.add(getDrawable(activity!!, R.drawable.shose_pink_1))
                    character_shose_moving2.add(getDrawable(activity!!, R.drawable.shose_pink_2))

                }
                1->{
                    character_shose_moving1.add(getDrawable(activity!!, R.drawable.gray_shose_1))
                    character_shose_moving2.add(getDrawable(activity!!, R.drawable.gray_shose_2))
                }
            }
        }



        /////////////////////////////// 동적생성에 필요한 변수////////////////////////////////////////

        var bundle_margin_top = 20
        var bundle_margin_start = 1

        var funiture_margin_top = 50
        var funiture_margin_start = 0

        //////////////////////////////////////////////////////////////////////////////////////////////

        val plat_root:FrameLayout = view.findViewById(R.id.plat_root)
        val inflater = activity!!.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        for(i in 0..fun_num){

            plat_root.setBackgroundResource(R.drawable.grass_background)


            fun funiture_maker(): View {

                val plat_funiture = inflater.inflate(R.layout.plat_funiture, plat_funiture_area, false) as FrameLayout

                val plat_funiture_lp =
                    ConstraintLayout.LayoutParams(fromDpToPx(view.context, 70), fromDpToPx(view.context, 70))
                plat_funiture_lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                plat_funiture_lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                plat_funiture_lp.topMargin = fromDpToPx(view.context, funiture_margin_top)
                plat_funiture_lp.marginStart = fromDpToPx(view.context, funiture_margin_start)
                // binding.cardViews.setLayoutParams(layoutParams)

                plat_funiture.layoutParams = plat_funiture_lp
                tempID_plat_funiture = getResources().getIdentifier(
                    "plat_funiture_area_" + (i),
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
            plat_root.addView(funiture_maker())
            flat_funiture_areas[i] = view.findViewById(tempID_plat_funiture)
            funiture_margin_start += fromDpToPx(view.context.applicationContext,50)




        }
        for (i in 0..cha_num) {
            // 배열하려는 모양 설정




            fun cha_maker(): View {
                val character_capsule = inflater.inflate(R.layout.character_bundle, plat_root, false) as FrameLayout
                val character_capsule_lp =
                    ConstraintLayout.LayoutParams(fromDpToPx(view.context, 100),fromDpToPx(view.context, 150))
                character_capsule_lp.startToStart = ConstraintLayout.LayoutParams.PARENT_ID
                character_capsule_lp.topToTop = ConstraintLayout.LayoutParams.PARENT_ID
                character_capsule_lp.topMargin = fromDpToPx(view.context, bundle_margin_top)
                character_capsule_lp.marginStart = fromDpToPx(view.context, bundle_margin_start)
                // binding.cardViews.setLayoutParams(layoutParams)

                character_capsule.layoutParams = character_capsule_lp
                tempID_bundle_capsule = getResources().getIdentifier(
                    "bundle_capsule_" + (i),
                    "id", activity!!.packageName
                )
                character_capsule.setId(tempID_bundle_capsule)

                return character_capsule



            }
            if(i%2==0){

                bundle_margin_start = fromDpToPx(view.context.applicationContext,26)
            }

            if(i%2==0 && i!=0){
                bundle_margin_top += fromDpToPx(view.context.applicationContext,52)

            }
            plat_root.addView(cha_maker())

            bundle_margin_start += fromDpToPx(view.context.applicationContext,53)

            cha_capsule[i] = view.findViewById(tempID_bundle_capsule)
            cha_bundle[i] = cha_capsule[i]?.findViewById(R.id.character_bundle)
            head[i] = cha_capsule[i]?.findViewById(R.id.head)
            body[i] = cha_capsule[i]?.findViewById(R.id.body)
            shose[i] = cha_capsule[i]?.findViewById(R.id.shose)
            tooltip[i] = cha_capsule[i]?.findViewById(R.id.tooltip)
            tooltip_text[i] = cha_capsule[i]?.findViewById(R.id.tootip_text)
            like_button[i] = cha_capsule[i]?.findViewById(R.id.like_button)

            head[i]?.setBackgroundResource(R.drawable.head_shy)
            body[i]?.setBackgroundResource(R.drawable.body)



            if(i%2==0){
                temp_shosemoving_ID = 0
            }
            else{
                temp_shosemoving_ID = 1
            }
            when (temp_shosemoving_ID){
                0-> {
                    body[i]?.setBackgroundResource(R.drawable.body_belt_skirt)

                }
                1->{

                    body[i]?.setBackgroundResource(R.drawable.body_skirt)
                }
            }


        }


        //백에서 보유가구값 받아와서??


        // 배열에 가구 넣어서 조건 맞는거 가져오기
        //임시로 0번 배열에 index0
        flat_funiture_areas[0]?.setBackgroundResource(fun_arys_flat.getResourceId(0, -1))
        flat_funiture_areas[3]?.setBackgroundResource(R.drawable.table)
        flat_funiture_areas[9]?.setBackgroundResource(R.drawable.table)

        for(i in 0..20){
            flat_funiture_areas[i]?.setBackgroundResource(R.drawable.tree)
        }


        val thread1 = AnimThread()
        val thread2 = movecha()
        val thread3 = crash()
        thread1.start()
        thread2.start()
        thread3.start()





        return view

    }

    var zero = 0

    private fun fromDpToPx(context: Context, dp: Int): Int {
        return TypedValue.applyDimension(
            TypedValue.COMPLEX_UNIT_DIP,
            dp.toFloat(),
            context.resources.displayMetrics
        )
            .toInt()
    }


    //*************************************************************************
    //애 니 메 이 션//
    //*************************************************************************

    inner class AnimThread : Thread() {
        override fun run() {
            var index = 1
            while (true) {


                handler1.post {
                    for(j in 0..5){
                        if(index%2==0){
                            val drawable = character_shose_moving1[j]
                            shose[j]?.setImageDrawable(drawable)
                        }
                        else{
                            val drawable = character_shose_moving2[j]
                            shose[j]?.setImageDrawable(drawable)
                        }

                    }
                }
                index++
                try {
                    sleep(700)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }


    inner class movecha : Thread() {
        val inflater = activity!!.getSystemService(AppCompatActivity.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        override fun run() {
            while (true) {
                handler2.post {
                    for (k in 0..cha_num) {
                        randomdir(inflater)
                        //if 가구위치 검사, 없으면->
                        //for(검사수..) 배열 = 백앤드값
                        //배열[i]. getx <=bundle[x]+dirx
                        //ex)Bundle[3]과 안박게
                        cha_capsule[k]?.let { imagemove(it, dirx, diry, 5000L) }
                        ranindex_in++
                    }
                    ranindex++

                }
                try {
                    sleep(5000L)

                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }


    fun imagemove(image1: FrameLayout, posix: Float, posiy: Float, duration1: Long) {
        runnable = object : Runnable {
            override fun run() {
                ObjectAnimator.ofFloat(image1, "translationX", posix).apply {
                    duration = duration1
                    start()
                }

                ObjectAnimator.ofFloat(image1, "translationY", posiy).apply {
                    duration = duration1
                    start()
                }
                handler3.postDelayed(runnable, duration1) //함수를 지속적으로 불러울수 있게
            }

        }
        handler3.post(runnable)


    }

    var flags = arrayOfNulls<Int>(16)

    inner class crash : Thread() {
        override fun run() {
            while (true) {
                for (a in 0..15) {
                    flags[a] = 0
                    for (b in 0..cha_num) {
                        if (cha_capsule[b]!!.getX() + tempwidth <= flat_funiture_areas[a]!!.getX()
                            && cha_capsule[b]!!.getX() + cha_capsule[b]!!.getWidth() - tempwidth>= flat_funiture_areas[a]!!.getX()
                            || flat_funiture_areas[a]!!.getX() <= cha_capsule[b]!!.getX() + tempwidth
                            && flat_funiture_areas[a]!!.getX() + flat_funiture_areas[a]!!.getWidth() >= cha_capsule[b]!!.getX() + tempwidth) {

                            if (cha_capsule[b]!!.getY() +tempheight <= flat_funiture_areas[a]!!.getY()
                                && cha_capsule[b]!!.getY() + cha_capsule[b]!!.getHeight() -tempheight >= flat_funiture_areas[a]!!.getY()
                                || flat_funiture_areas[a]!!.getY() <= cha_capsule[b]!!.getY() + tempheight
                                && flat_funiture_areas[a]!!.getY() + flat_funiture_areas[a]!!.getHeight() >= cha_capsule[b]!!.getY()) {

                                flags[a] = 1

                            }
                        }

                    }
                }


                handler4.post {
                    for (i in 0..15) {
                        if (flags[i] == 1) {
                            flat_funiture_areas[i]?.setAlpha(0.4F)
                        } else {
                            flat_funiture_areas[i]?.setAlpha(1F)
                        }
                    }
                }

                try {
                    sleep(50)
                } catch (e: InterruptedException) {
                    e.printStackTrace()
                }
            }
        }
    }



    var ranindex = 0
    var ranindex_in = 0
    var random = Random()

    fun randomdir(inflater: LayoutInflater) {
        var randomnum = random.nextInt(4)
        val view:View = inflater.inflate(R.layout.fragment_main, null)
        if (ranindex % 2 == 0) {
            when (randomnum) {
                0 -> {
                    if (cha_bundle[k]!!.getY() + diry >= 0f && cha_bundle[k]!!.getY() + diry <= fromDpToPx(
                            view.context,
                        1800)
                    ) {
                        dirx = 0f
                        diry = -(random.nextInt(2) + 1) * 50f
                    } else {
                        dirx = 0f
                        diry = 0f
                    }

                }
                1 -> {
                    if (cha_bundle[k]!!.getX() + dirx >= 0f && cha_bundle[k]!!.getX() + dirx <= fromDpToPx(
                            view.context,
                            500)
                    ) {
                        dirx = -(random.nextInt(2) + 1) * 100f
                        diry = 0f
                    } else {
                        dirx = 0f
                        diry = 0f
                    }
                }
                2 -> {
                    if (cha_bundle[k]!!.getY() + diry >= 0f && cha_bundle[k]!!.getY() + diry <= fromDpToPx(
                            view.context,
                            1000)
                    ) {
                        dirx = 0f
                        diry = (random.nextInt(2) + 1) * 100f
                    } else {
                        dirx = 0f
                        diry = 0f
                    }
                }
                3 -> {
                    if (cha_bundle[k]!!.getX() + dirx >= 0f && cha_bundle[k]!!.getX() + dirx <= fromDpToPx(
                            view.context,
                            500)
                    ) {
                        dirx = (random.nextInt(2) + 1) * 100f
                        diry = 0f
                    } else {
                        dirx = 0f
                        diry = 0f
                    }
                }
            }

        } else {
            dirx = 0f
            diry = 0f
        }

    }

}

class PlatListItem(val num: Int)

class PlatListAdapter(val items: ArrayList<PlatListItem>): RecyclerView.Adapter<PlatListAdapter.ViewHolder>(){

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PlatListAdapter.ViewHolder {
        val inflatedView = LayoutInflater.from(parent.context).inflate(R.layout.list_item_plat_list, parent, false)

        return ViewHolder(inflatedView)
    }

    override fun onBindViewHolder(holder: PlatListAdapter.ViewHolder, position: Int) {
        val num = items[position]

        holder.bind(num)
    }

    override fun getItemCount(): Int {
        return items.size
    }

    class ViewHolder(private var v: View) : RecyclerView.ViewHolder(v){
        fun bind(item: PlatListItem){
            v.item_plat_list.text = item.num.toString()
        }
    }
}