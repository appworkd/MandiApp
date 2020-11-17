package com.appwork.mandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.appwork.util.Constants.PIECE_FOR_CAL_VAL
import com.appwork.util.Constants.PIECE_VAL
import com.appwork.util.Constants.PIECE_WT_VAL
import com.appwork.util.Constants.RATES_VAL
import com.appwork.util.Constants.REMAIN_WT_VAL
import com.appwork.util.Constants.TOTAL_WT_VAL
import com.appwork.util.Constants.WT_CHARGES_VAL
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
        if (intent.hasExtra(PIECE_VAL)) {
            pieces = intent.getIntExtra(PIECE_VAL, 0)
        }
        if (intent.hasExtra(RATES_VAL)) {
            rates = intent.getIntExtra(RATES_VAL, 0)
        }
        if (intent.hasExtra(PIECE_WT_VAL)) {
            pieceWt = intent.getIntExtra(PIECE_WT_VAL, 0)
        }
        if (intent.hasExtra(REMAIN_WT_VAL)) {
            remainWt = intent.getIntExtra(REMAIN_WT_VAL, 0)
        }
        if (intent.hasExtra(TOTAL_WT_VAL)) {
            total = intent.getIntExtra(TOTAL_WT_VAL, 0)
        }
        edtCalPieces.setText(pieces.toString())
        fabGo.setOnClickListener {
            if (edtWtCharges.text.isNullOrEmpty()) {
                tilWtCharge.isErrorEnabled = true
                tilWtCharge.error = getString(R.string.txt_enter_wt_charges)
                return@setOnClickListener
            }
            if (edtCalPieces.text.isNullOrEmpty()) {
                tilCalPieces.isErrorEnabled = true
                tilCalPieces.error = getString(R.string.txt_enter_pieces)
                return@setOnClickListener
            }
            val intent = Intent(this, AadatChargeActivity::class.java)
            intent.putExtra(PIECE_VAL, pieces)
            intent.putExtra(RATES_VAL, rates)
            intent.putExtra(TOTAL_WT_VAL, total)
            intent.putExtra(PIECE_WT_VAL, pieceWt)
            intent.putExtra(REMAIN_WT_VAL, remainWt)
            intent.putExtra(WT_CHARGES_VAL, edtWtCharges.text.toString().toInt())
            intent.putExtra(PIECE_FOR_CAL_VAL, edtCalPieces.text.toString().toInt())
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