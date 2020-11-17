package com.appwork.mandiapp.fragments

import android.content.Context
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.TextureView
import android.view.View
import android.view.ViewGroup
import com.appwork.mandiapp.R
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import kotlinx.android.synthetic.main.fragment_change_value.*


class ChangeValueFragment : BottomSheetDialogFragment(), TextWatcher {
    private var callback: ChangeValueCallback? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_change_value, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        isCancelable = false
        setUpView()
    }

    private fun setUpView() {
        closeDialog.setOnClickListener {
            dismiss()
        }
        val args = arguments
        val oldValue = args?.getString("oldValue")
        edtOlderValue.setText(oldValue)
        edtOlderValue.isEnabled = false


        btnDoneChanges.setOnClickListener {
            if (edtNewValue.text.isNullOrEmpty()) {
                tilNewValue.isErrorEnabled = true
                tilNewValue.error = getString(R.string.txt_enter_new_value)
                return@setOnClickListener
            }
            callback?.getValues(edtOlderValue.text.toString(), edtNewValue.text.toString())
            dismiss()
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if (context is ChangeValueCallback) {
            callback = context as ChangeValueCallback
        } else {
            throw RuntimeException(
                context.toString()
                    .toString() + " must implement ItemClickListener"
            )
        }
    }

    override fun onDetach() {
        super.onDetach()
        callback = null
    }

    companion object {
        @JvmStatic
        fun newInstance(bundle: Bundle) =
            ChangeValueFragment().apply {
                arguments = Bundle().apply {

                }
            }
    }

    interface ChangeValueCallback {
        fun getValues(oldValue: String, newValue: String)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (char.toString().isNotEmpty()) {
            if (tilNewValue.isErrorEnabled) {
                tilNewValue.isErrorEnabled = false
                tilNewValue.error = ""
            }

        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}