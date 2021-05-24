package org.b4.yesol_part

import android.content.Context
import android.graphics.Color
import android.graphics.Color.BLUE
import android.graphics.Color.RED
import android.os.Bundle
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.FrameLayout
import androidx.appcompat.app.AppCompatActivity


class funiture_put : AppCompatActivity() {

    var funiture_ID: Int = 0
    var funiture_area_ID: Int = 0
    var funiture_button_ID: Int = 0
    var xbox_ID: Int = 0

    var funitures = arrayOfNulls<Button>(16)
    var funiture_areas = arrayOfNulls<FrameLayout>(16)
    var xbox_buttons = arrayOfNulls<Button>(16)
    var funiture_Button = arrayOfNulls<Button>(16)

    var flag = true


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_funiture_put)


        val fun_imgs = resources.obtainTypedArray(R.array.funi_imgs)
        val fun_arys = resources.obtainTypedArray(R.array.funi_arys)
///////////////////////////////////////////////////

        val X_BUTTON_WIDTH = fromDpToPx(this, 35)
        val X_BUTTON_HEIGHT = fromDpToPx(this, 35)

////////////////////////////////////////////////////


        // 가구 배열에 담기
        for (i in 0..15) {
            funiture_ID = getResources().getIdentifier(
                "item" + (i),
                "id", packageName
            )
            // 후에 아이템 넘버따라 case 문 추가
            funitures[i] = findViewById(funiture_ID)
            funitures[i]?.setBackgroundResource(fun_imgs.getResourceId(0, -1))
        }

        // 가구놓을자리 배열에 담기
        for (i in 0..15) {
            funiture_area_ID = getResources().getIdentifier(
                "funiture_area_" + (i),
                "id", packageName
            )
            funiture_areas[i] = findViewById(funiture_area_ID)
            funiture_areas[i]?.setBackgroundColor(Color.GREEN)

            // 후에 아이템 넘버따라 case 문 추가
        }


        //가구안에 누를 버튼 배열에 담기


//후에 조건따라 i 변경
        for (i in 0..15) {
            fun CreateXBox(): View {

                // val CHA_HEAD_WIDTH =dp2px(40)
                val x_box_button = Button(this)
                val x_button_lp = FrameLayout.LayoutParams(X_BUTTON_WIDTH, X_BUTTON_HEIGHT)
                x_button_lp.setMargins(fromDpToPx(this, 40), 0, 0, 0)

                x_box_button.layoutParams = x_button_lp

                xbox_ID = getResources().getIdentifier(
                    "xbox_button_" + (i),
                    "id", packageName
                )
                x_box_button.setId(xbox_ID)
                x_box_button.setBackgroundColor(Color.GREEN)

                return x_box_button
            }

            funiture_areas[i]?.addView(CreateXBox())

            xbox_buttons[i] = findViewById(xbox_ID)
          //  xbox_buttons[i]?.setBackgroundColor(RED)


        }
        // 가구안에 클릭될 버튼
        for (i in 0..15) {
            funiture_button_ID = getResources().getIdentifier(
                "funiture_bt_" + (i),
                "id", packageName
            )

            // 후에 아이템 넘버따라 case 문 추가

            funiture_Button[i] = findViewById(funiture_button_ID)


        }
        //////////////////////////////////////////////////////////////삭제

        ///////////////////////////////////////////////////각 버튼값 -> 간소화할수있으면 간소화

        var zero = 0
        var one = 1

        funitures[1]?.setBackgroundResource(fun_arys.getResourceId(one, -1))

        funitures[0]?.setOnClickListener {
            //백에서 ary 값 받아와서 변수에 저장,
            flag = true;
            one = 1 //임시
        }
//***********************************************************

        funiture_Button[0]?.setOnClickListener {

            if (flag == true) {
                //삭제
                xbox_buttons[0]?.setBackgroundColor(0)
                //xbox_buttons[0]?.setBackgroundColor(BLUE)

                //백에서 ary 값 받아와서!
                funiture_areas[0]?.setBackgroundResource(fun_arys.getResourceId(one, -1))
                xbox_buttons[0]?.setVisibility(View.VISIBLE)
                xbox_buttons[0]?.setBackgroundColor(Color.GREEN)

                flag == false
            }

        }


//*************************************************************

        xbox_buttons[0]?.setOnClickListener {
            //삭제

            funiture_areas[0]?.setBackgroundColor(Color.GREEN)
            xbox_buttons[0]?.setVisibility(View.INVISIBLE)
            xbox_buttons[0]?.setBackgroundColor(Color.RED);

        }


        // xbox_buttons[0]?.setVisibility(View.VISIBLE);


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
