package com.appwork.mandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.appwork.util.Constants.PIECE_VAL
import com.appwork.util.Constants.PIECE_WT_VAL
import com.appwork.util.Constants.RATES_VAL
import com.appwork.util.Constants.REMAIN_WT_VAL
import com.appwork.util.Constants.TOTAL_WT_VAL
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
        if (intent.hasExtra(TOTAL_WT_VAL)) {
            totalWt = intent.getIntExtra(TOTAL_WT_VAL, 0)
        }
        if (intent.hasExtra(PIECE_VAL)) {
            pieces = intent.getIntExtra(PIECE_VAL, 0)
        }
        if (intent.hasExtra(PIECE_WT_VAL)) {
            pieceWt = intent.getIntExtra(PIECE_WT_VAL, 0)
        }
        if (intent.hasExtra(REMAIN_WT_VAL)) {
            remainWt = intent.getIntExtra(REMAIN_WT_VAL, 0)
        }
        fabDone.setOnClickListener {
            if (edtRates.text.toString().isEmpty()) {
                tilRates.isErrorEnabled = true
                tilRates.error = "Please enter rates"
                return@setOnClickListener
            }
            val rates = edtRates.text.toString().toInt()
            val intent = Intent(this, ChargesActivity::class.java)
            intent.putExtra(PIECE_VAL, pieces)
            intent.putExtra(RATES_VAL, rates)
            intent.putExtra(TOTAL_WT_VAL, totalWt)
            intent.putExtra(PIECE_WT_VAL,pieceWt)
            intent.putExtra(REMAIN_WT_VAL,remainWt)
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