package com.example.plat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager
import com.apollographql.apollo.api.Input
import com.apollographql.apollo.api.Response
import com.apollographql.apollo.coroutines.await
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class DialogMakeContext(val mainActivity: MainActivity) : DialogFragment() {
    // todo : 텍스트 입력 창
    val apolloClient = apolloClient(mainActivity.applicationContext)
    val scope = CoroutineScope(Dispatchers.IO)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_make_context, container, false)

        val btnSend = view.findViewById<Button>(R.id.sendContext)
        val btnCancle = view.findViewById<Button>(R.id.cancleContext)
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
                        groupId = mainActivity.clickedName
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
        return view.rootView
    }

}