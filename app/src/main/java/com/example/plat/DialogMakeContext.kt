package com.example.plat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager

class DialogMakeContext : DialogFragment() {
    // todo : 텍스트 입력 창

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_make_context, container, false)

        val btn = view.findViewById<Button>(R.id.sendContext)
        val editText = view.findViewById<EditText>(R.id.makeContext)

        btn.setOnClickListener {
            val text = editText.text.toString()

            dismiss()
        }
        return view.rootView
    }

}