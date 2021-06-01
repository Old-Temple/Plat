package com.example.plat

import android.content.Intent
import android.database.Cursor
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.provider.MediaStore
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.apollographql.apollo.api.FileUpload
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogMakeContext(val mainActivity: MainActivity) : DialogFragment() {
    // todo : 텍스트 입력 창
    val apolloClient = apolloClient(mainActivity.applicationContext)
    val scope = CoroutineScope(Dispatchers.IO)
    var imgSrc = ""
    private val REQUEST_CODE_ = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_make_context, container, false)

        val btnSend = view.findViewById<Button>(R.id.sendContext)
        val btnCancle = view.findViewById<Button>(R.id.cancleContext)
        val btnImage = view.findViewById<Button>(R.id.btnMakeContextImage)
        val editText = view.findViewById<EditText>(R.id.makeContext)
        val editTitle = view.findViewById<EditText>(R.id.mkContextTitle)


        btnSend.setOnClickListener {
            val text = editText.text.toString()
            val title = editTitle.text.toString()

            scope.launch {
                val response : Response<UploadFeedMutation.Data> =
                    apolloClient.mutate(UploadFeedMutation(
                        title = title,
                        caption = Input.fromNullable(text),
                        groupId = mainActivity.clickedName,
                        file = Input.fromNullable(FileUpload("image/jpeg", imgSrc))
                    )).await()
                if (response.data?.uploadFeed?.error != null){
                    view.findViewById<TextView>(R.id.editErrorMessage).text = response.data?.uploadFeed?.error
                }
                else{
                    dismiss()
                }
            }
        }
        btnCancle.setOnClickListener {
            dismiss()
        }
        btnImage.setOnClickListener {
            val intent = Intent()
            intent.type = "image/*"
            intent.action = Intent.ACTION_PICK
            startActivityForResult(intent, REQUEST_CODE_)
        }

        return view.rootView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == REQUEST_CODE_){
            if (resultCode == AppCompatActivity.RESULT_OK){
                val temp = data?.data?.let {
                    mainActivity
                        .applicationContext
                        .contentResolver.openInputStream(it)
                }
                temp?.close()
                imgSrc = absolutelyPath(data?.data!!)
                Glide.with(view!!).load(data.data!!).into(view!!.findViewById<ImageView>(R.id.mkContextImage))
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