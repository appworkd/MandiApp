package com.appwork.mandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_rates.*

class RatesActivity : AppCompatActivity(), TextWatcher {
    private var totalWt: Int? = null
    private var pieces: Int? = null
    private var pieceWt: Int? = null
    private var remainWt: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_rates)
        edtRates.addTextChangedListener(this)
        if (intent.hasExtra("total")) {
            totalWt = intent.getIntExtra("total", 0)
        }
        if (intent.hasExtra("pieces")) {
            pieces = intent.getIntExtra("pieces", 0)
        }
        if (intent.hasExtra("pieceWt")) {
            pieceWt = intent.getIntExtra("pieceWt", 0)
        }
        if (intent.hasExtra("remainWt")) {
            remainWt = intent.getIntExtra("remainWt", 0)
        }
        fabDone.setOnClickListener {
            if (edtRates.text.toString().isEmpty()) {
                tilRates.isErrorEnabled = true
                tilRates.error = "Please enter rates"
                return@setOnClickListener
            }
            val rates = edtRates.text.toString().toInt()
            val intent = Intent(this, ChargesActivity::class.java)
            intent.putExtra("pieces", pieces)
            intent.putExtra("rates", rates)
            intent.putExtra("total", totalWt)
            intent.putExtra("pieceWt",pieceWt)
            intent.putExtra("remainWt",remainWt)
            startActivity(intent)
        }
    }

    override fun beforeTextChanged(input: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (input.toString().isNotEmpty()) {
            if (tilRates.isErrorEnabled) {
                tilRates.isErrorEnabled = false
                tilRates.error = ""
            }
            fabDone.visibility = View.VISIBLE
        } else {
            fabDone.visibility = View.GONE
        }
    }

    override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun afterTextChanged(p0: Editable?) {

    }
}