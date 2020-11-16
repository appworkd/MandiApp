package com.appwork.mandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.appwork.util.Constants.AADAT_CHARGES_VAL
import com.appwork.util.Constants.PIECE_FOR_CAL_VAL
import com.appwork.util.Constants.PIECE_VAL
import com.appwork.util.Constants.PIECE_WT_VAL
import com.appwork.util.Constants.RATES_VAL
import com.appwork.util.Constants.REMAIN_WT_VAL
import com.appwork.util.Constants.TOTAL_WT_VAL
import com.appwork.util.Constants.WT_CHARGES_VAL
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
    private var piecesCal: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_aadat_charge)
        edtAdatCharge.addTextChangedListener(this)

        if (intent.hasExtra(PIECE_VAL)) {
            pieces = intent.getIntExtra(PIECE_VAL, 0)
        }
        if (intent.hasExtra(RATES_VAL)) {
            rates = intent.getIntExtra(RATES_VAL, 0)
        }
        if (intent.hasExtra(TOTAL_WT_VAL)) {
            total = intent.getIntExtra(TOTAL_WT_VAL, 0)
        }
        if (intent.hasExtra(WT_CHARGES_VAL)) {
            wtCharges = intent.getIntExtra(WT_CHARGES_VAL, 0)
        }
        if (intent.hasExtra(PIECE_WT_VAL)) {
            pieceWt = intent.getIntExtra(PIECE_WT_VAL, 0)
        }
        if (intent.hasExtra(REMAIN_WT_VAL)) {
            remainWt = intent.getIntExtra(REMAIN_WT_VAL, 0)
        }
        if (intent.hasExtra(PIECE_FOR_CAL_VAL)) {
            piecesCal = intent.getIntExtra(PIECE_FOR_CAL_VAL, 0)
        }
        fabAdat.setOnClickListener {
            if (edtAdatCharge.text.isNullOrEmpty()) {
                tilAdatCharge.isErrorEnabled = true
                tilAdatCharge.error = "Enter charges"
                return@setOnClickListener
            }
            val intent = Intent(this, ConsentActivity::class.java)
            intent.putExtra(PIECE_VAL, pieces)
            intent.putExtra(RATES_VAL, rates)
            intent.putExtra(TOTAL_WT_VAL, total)
            intent.putExtra(PIECE_WT_VAL, pieceWt)
            intent.putExtra(REMAIN_WT_VAL, remainWt)
            intent.putExtra(WT_CHARGES_VAL, wtCharges)
            intent.putExtra(PIECE_FOR_CAL_VAL, piecesCal)
            intent.putExtra(AADAT_CHARGES_VAL, edtAdatCharge.text.toString().toInt())
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