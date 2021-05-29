package com.example.plat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogMakePlat(val mainActivity: MainActivity) : DialogFragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_make_plat, container, false)

        val editTextPlatName = view.findViewById<EditText>(R.id.inputPlatName)
        val editTextBio = view.findViewById<EditText>(R.id.inputTag)
        val checkBoxOpen = view.findViewById<CheckBox>(R.id.checkboxOpen)
        val errorMessage = view.findViewById<TextView>(R.id.mkPlatErrorMessage)

        view.findViewById<Button>(R.id.btnMakePlatOK).setOnClickListener { view ->
            val title = editTextPlatName.text.toString()
            val bio = editTextBio.text.toString()
            val open = checkBoxOpen.isChecked


            val apolloClient = apolloClient(mainActivity.applicationContext)

            val scope = CoroutineScope(Dispatchers.IO)
            try {
                scope.launch {
                    val response: Response<CreateGroupMutation.Data> =
                        apolloClient.mutate(
                            CreateGroupMutation
                                (
                                title= title,
                                bio= Input.fromNullable(bio),
                                open= open
                            )
                        ).await()
                    //에러가 나면 실행
                    if(response.data?.createGroup?.error != null){
                        errorMessage.setText(response.data?.createGroup?.error)
                    }
                    //에러가 나지 않고 제대로 된 값이 나오면 실행
                    if(response.data?.createGroup?.ok == true){
                        dismiss()
                    }
                }
            } catch (e : Throwable) {
                Log.d("mymyException", e.toString())
            }
        }
        view.findViewById<Button>(R.id.btnMakePlatCancle).setOnClickListener { view ->
            dismiss()
        }

        return view
    }
}