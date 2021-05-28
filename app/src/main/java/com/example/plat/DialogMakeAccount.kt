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
import android.widget.Toast
import androidx.fragment.app.DialogFragment
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

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
        val getFirstName = view.findViewById<EditText>(R.id.createAccountFirstName)
        val getLastName = view.findViewById<EditText>(R.id.createAccountLastName)
        val errorMessage = view.findViewById<TextView>(R.id.createAccountErrorM)
        val btnsubmit = view.findViewById<Button>(R.id.btncreateAccountSubmit)

        btnsubmit.setOnClickListener { view ->
            if(getFirstName.toString() == ""){
                errorMessage.text = "First Name을 입력해 주세요"
            }
            if(getUserName.toString() == ""){
                errorMessage.text = "User Name을 입력해 주세요"
            }
            if(getemail.toString() == ""){
                errorMessage.text = "Email을 입력해 주세요"
            }
            else {
                //todo : 회원가입
                var userName = getUserName.text.toString()
                var email = getemail.text.toString()
                var firstName = getFirstName.text.toString()
                var lastName = getLastName.text.toString()

                val apolloClient = apolloClient(activity!!)

                val scope = CoroutineScope(Dispatchers.IO)
                try {
                    scope.launch {
                        val response: Response<CreateAccountMutation.Data> =
                            apolloClient.mutate(
                                CreateAccountMutation
                                    (
                                    firstName = firstName,
                                    lastName = Input.fromNullable(lastName),
                                    userName = userName,
                                    email = email
                                )
                            ).await()
                        if(response.data?.createAccount?.error != null){
                            errorMessage.setText(response.data?.createAccount?.error)
                        }
                        if(response.data?.createAccount?.ok==true){
                            dismiss()
                        }
                    }
                } catch (e : Throwable) {
                    Log.d("mymyException", e.toString())
                }
            }
        }

        return view.rootView
    }

}