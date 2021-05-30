package com.example.plat

import android.os.Bundle
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

class DialogEditProfile(
    val mainActivity: MainActivity,
    val userName : String?,
    val firstName : String?,
    val lastName : String?
) : DialogFragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view: View = inflater.inflate(R.layout.fragment_dialog_edit_profile, null)
        val userName = view.findViewById<EditText>(R.id.editUserName)
        val firstName = view.findViewById<EditText>(R.id.editFirstName)
        val lastName = view.findViewById<EditText>(R.id.editLastName)
        val bio = view.findViewById<CheckBox>(R.id.editCheckbox)
        val errorMessage = view.findViewById<TextView>(R.id.editErrorMessage)

        userName.hint = this.userName
        firstName.hint = this.firstName
        lastName.hint = this.lastName

        view.findViewById<Button>(R.id.btnEditSubmit).setOnClickListener {
            val apolloClient = apolloClient(mainActivity.applicationContext)
            val scope = CoroutineScope(Dispatchers.IO)
            scope.launch {
                val response : Response<EditProfileMutation.Data> =
                    apolloClient.mutate(EditProfileMutation(
                        userName = Input.fromNullable(userName.text.toString()),
                        firstName = Input.fromNullable(firstName.text.toString()),
                        lastName = Input.fromNullable(lastName.text.toString()),
                        bio = Input.fromNullable(bio.isChecked().toString())
                    )).await()

                if (response.data?.editProfile?.error != null){
                    errorMessage.text = response.data?.editProfile?.error.toString()
                }
                else {
                    dismiss()
                }
            }
        }

        view.findViewById<Button>(R.id.btnEditCancle).setOnClickListener {
            dismiss()
        }
        return view
    }
}