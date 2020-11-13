package com.appwork.mandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_charges.*

class ChargesActivity : AppCompatActivity(), TextWatcher {
    private var pieces: Int? = null
    private var rates: Int? = null
    private var total: Int? = null
    private var pieceWt: Int? = null
    private var remainWt: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_charges)
        if (intent.hasExtra("pieces")) {
            pieces = intent.getIntExtra("pieces", 0)
        }
        if (intent.hasExtra("rates")) {
            rates = intent.getIntExtra("rates", 0)
        }
        if (intent.hasExtra("pieceWt")) {
            pieceWt = intent.getIntExtra("pieceWt", 0)
        }
        if (intent.hasExtra("remainWt")) {
            remainWt = intent.getIntExtra("remainWt", 0)
        }
        if (intent.hasExtra("total")) {
            total = intent.getIntExtra("total", 0)
        }
        fabGo.setOnClickListener {
            if (edtWtCharges.text.isNullOrEmpty()) {
                tilWtCharge.isErrorEnabled = true
                tilWtCharge.error = "Please enter wt charges"
                return@setOnClickListener
            }
            val intent = Intent(this, AadatChargeActivity::class.java)
            intent.putExtra("pieces", pieces)
            intent.putExtra("rates", rates)
            intent.putExtra("total", total)
            intent.putExtra("pieceWt",pieceWt)
            intent.putExtra("remainWt",remainWt)
            intent.putExtra("wtChanges", edtWtCharges.text.toString().toInt())
            startActivity(intent)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
        TODO("Not yet implemented")
    }

    override fun onTextChanged(data: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (data.toString().isNotEmpty()) {
            fabGo.visibility = View.VISIBLE
            if (tilWtCharge.isErrorEnabled) {
                tilWtCharge.isErrorEnabled = false
                tilWtCharge.error = ""
            }
        } else {
            fabGo.visibility = View.GONE
        }
    }

    override fun afterTextChanged(p0: Editable?) {
        TODO("Not yet implemented")
    }
}