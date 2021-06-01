package com.example.plat

import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.os.Environment
import android.provider.MediaStore
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import com.apollographql.apollo.api.FileUpload
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

class DialogEditProfile(
    val mainActivity: MainActivity,
    val userName : String?,
    val firstName : String?,
    val lastName : String?
) : DialogFragment() {
    var imgSrc = ""
    val REQUEST_CODE = 0
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
            val userNameText = userName.text.toString()
            var firstNameText = firstName.text.toString()
            var lastNameText = lastName.text.toString()
            if (userNameText == ""){
                userName.text = null
            }
            if (firstNameText == ""){
                firstNameText = this.firstName!!
            }
            if (lastNameText == ""){
                lastNameText = this.lastName!!
            }
            scope.launch {
                val response : Response<EditProfileMutation.Data> =
                    apolloClient.mutate(EditProfileMutation(
                        userName = Input.fromNullable(userName.text.toString()),
                        firstName = Input.fromNullable(firstNameText),
                        lastName = Input.fromNullable(lastNameText),
                        bio = Input.fromNullable(bio.isChecked().toString())
                    )).await()

                if (response.data?.editProfile?.error != null){
                    errorMessage.text = response.data?.editProfile?.error.toString()
                }
                else {
                    if (userNameText != ""){
                        PlatPrefs.prefs.setValue("userName",userNameText)
                    }
                    dismiss()
                }
            }
        }

        view.findViewById<Button>(R.id.btnEditCancle).setOnClickListener {
            dismiss()
        }

        view.findViewById<Button>(R.id.btnEditImage).setOnClickListener{
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(intent, REQUEST_CODE)
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE){
            if (resultCode == AppCompatActivity.RESULT_OK){
                val temp = data?.data?.let {
                    mainActivity
                        .applicationContext
                        .contentResolver.openInputStream(it)
                }
                temp?.close()
                imgSrc = absolutelyPath(data?.data!!)
                view!!.findViewById<TextView>(R.id.editImageSource).text = imgSrc
                val apolloClient = mainActivity.apolloClient
                val scope = CoroutineScope(Dispatchers.IO)
                scope.launch{
                    apolloClient?.mutate(EditProfileMutation(
                        profilePhoto = Input.fromNullable(FileUpload("image/jpeg", absolutelyPath(data?.data!!)))))!!.await()
                }
            }
        }
    }
    @Suppress("DEPRECATION")
    fun absolutelyPath(path: Uri): String {

        val proj: Array<String> = arrayOf(MediaStore.MediaColumns.DATA)
        val c: Cursor = mainActivity.contentResolver.query(path, proj, null, null, null)!!
        val index = c.getColumnIndexOrThrow(MediaStore.MediaColumns.DATA)
        c.moveToFirst()

        val result = c.getString(index)

        return result
    }

}

