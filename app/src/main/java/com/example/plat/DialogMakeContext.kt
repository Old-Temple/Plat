package com.example.plat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.FragmentManager


// TODO: Rename parameter arguments, choose names that match

class DialogMakeContext : DialogFragment() {
    // TODO


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)
        val view = inflater.inflate(R.layout.fragment_dialog_make_context, container, false)

        val btn = view.findViewById<Button>(R.id.sendContext)

        btn.setOnClickListener {
            val makeContext = DialogMakeContext()
            makeContext.show(childFragmentManager.beginTransaction(), makeContext.tag)
        }
        return view.rootView
    }

}