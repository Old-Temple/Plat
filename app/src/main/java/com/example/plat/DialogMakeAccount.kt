package com.example.plat

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment

// TODO: Rename parameter arguments, choose names that match

class DialogMakeAccount : DialogFragment() {
    // TODO

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_make_account, container, false)

        val getUserName = view.findViewById<EditText>(R.id.createAccountUserName)
        val getemail = view.findViewById<EditText>(R.id.createAccountEmail)
        val btnEmail = view.findViewById<Button>(R.id.btncreateAccountEmail)
        val getFirstName = view.findViewById<EditText>(R.id.createAccountFirstName)
        val getLastName = view.findViewById<EditText>(R.id.createAccountLastName)
        val errorMessage = view.findViewById<TextView>(R.id.createAccountErrorM)
        val btnsubmit = view.findViewById<Button>(R.id.btncreateAccountSubmit)
        var correctCode = false
        btnEmail.setOnClickListener { view ->
            //todo : 이메일 보내기
            var email = getemail.text.toString()
        }
        btnsubmit.setOnClickListener { view ->
            if(getFirstName.toString() == ""){
                errorMessage.text = "First Name을 입력해 주세요"
            }
            if(getUserName.toString() == ""){
                errorMessage.text = "User Name을 입력해 주세요"
            }
            else if (correctCode == false){
                errorMessage.text = "이메일 인증을 완료해 주세요"
            }
            else {
                //todo : 회원가입
                var userName = getUserName.toString()
                var email = getemail.toString()
                var firstName = getFirstName.toString()
                var lastName = getLastName.toString()
            }
        }

        return view.rootView
    }

}