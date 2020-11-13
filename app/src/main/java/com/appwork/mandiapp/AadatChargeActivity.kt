package com.appwork.mandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import kotlinx.android.synthetic.main.activity_aadat_charge.*
import kotlinx.android.synthetic.main.activity_charges.*
import kotlinx.android.synthetic.main.activity_consent.*

class AadatChargeActivity : AppCompatActivity(), TextWatcher {
    private var weightingCharges: Int? = null
    private var pieces: Int? = null
    private var rates: Int? = null
    private var total: Int? = null
    private var wtCharges: Int? = null
    private var pieceWt: Int? = null
    private var remainWt: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aadat_charge)
        edtAdatCharge.addTextChangedListener(this)

        if (intent.hasExtra("pieces")) {
            pieces = intent.getIntExtra("pieces", 0)
        }
        if (intent.hasExtra("rates")) {
            rates = intent.getIntExtra("rates", 0)
        }
        if (intent.hasExtra("total")) {
            total = intent.getIntExtra("total", 0)
        }
        if (intent.hasExtra("wtChanges")) {
            wtCharges = intent.getIntExtra("wtChanges", 0)
        }
        if (intent.hasExtra("pieceWt")) {
            pieceWt = intent.getIntExtra("pieceWt", 0)
        }
        if (intent.hasExtra("remainWt")) {
            remainWt = intent.getIntExtra("remainWt", 0)
        }
        fabAdat.setOnClickListener {
            if (edtAdatCharge.text.isNullOrEmpty()) {
                tilAdatCharge.isErrorEnabled = true
                tilAdatCharge.error = "Enter charges"
                return@setOnClickListener
            }
            val intent = Intent(this, ConsentActivity::class.java)
            intent.putExtra("pieces", pieces)
            intent.putExtra("rates", rates)
            intent.putExtra("total", total)
            intent.putExtra("pieceWt", pieceWt)
            intent.putExtra("remainWt", remainWt)
            intent.putExtra("wtChanges", wtCharges)
            intent.putExtra("aadatCharge", edtAdatCharge.text.toString().toInt())
            startActivity(intent)
        }
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (char.toString().isNotEmpty()) {
            if (tilAdatCharge.isErrorEnabled) {
                tilAdatCharge.isErrorEnabled = false
                tilAdatCharge.error = ""
            }
            fabAdat.visibility = View.VISIBLE
        } else {
            fabAdat.visibility = View.GONE
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}