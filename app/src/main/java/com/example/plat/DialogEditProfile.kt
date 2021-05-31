package com.example.plat

import android.content.Intent
import android.graphics.BitmapFactory
import android.os.Bundle
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
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_GET_CONTENT
            startActivityForResult(intent, REQUEST_CODE)
            Log.d("AAA", intent.data?.path.toString())
        }
        return view
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        Log.d("AAA", "1")
        if (requestCode == REQUEST_CODE){
            Log.d("AAA", "2")
            if (resultCode == AppCompatActivity.RESULT_OK){
                Log.d("AAA", "3")

                val temp = data?.data?.let {
                    mainActivity
                        .applicationContext
                        .contentResolver.openInputStream(it)
                }
                val img = BitmapFactory.decodeStream(temp)
                temp?.close()

                Log.d("AAA", (data?.data!!).toString())
                Log.d("AAA", data?.data!!.toString())
//                val apolloClient = apolloClient(mainActivity.applicationContext)
//                val scope = CoroutineScope(Dispatchers.IO)
//                scope.launch{
//                    val response : Response<EditProfileMutation.Data> =
//                        apolloClient.mutate(EditProfileMutation(
//                            profilePhoto = File(img)
//                        )).await()
//
//                    Log.d("AAA", "end")
//                }
            }
        }
    }

//    @Suppress("DEPRECATION")
//    @SuppressLint("Recycle")
//    fun getPath(uri: Uri) : String {
//        val projection : Array<String> = arrayOf(MediaStore.Images.Media.DATA)
//        Log.d("AAA", uri.toString())
//        val cursor = context?.contentResolver?.query(uri, projection, null, null, null)
//        mainActivity.startManagingCursor(cursor)
//        val columnIndex = cursor?.getColumnIndexOrThrow(MediaStore.Images.Media.DATA)
//        cursor?.moveToFirst()
//        Log.d("cursor", cursor.toString())
//        Log.d("column", columnIndex.toString())
//        return cursor!!.getString(columnIndex!!)
//    }

}

