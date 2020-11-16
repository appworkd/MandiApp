package com.appwork.mandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import com.appwork.util.Constants.PIECE_VAL
import com.appwork.util.Constants.PIECE_WT_VAL
import com.appwork.util.Constants.REMAIN_WT_VAL
import com.appwork.util.Constants.TOTAL_WT_VAL
import kotlinx.android.synthetic.main.activity_weight.*
import kotlinx.android.synthetic.main.container_seller.*

class WeightActivity : AppCompatActivity(), TextWatcher {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_weight)
        edtRemainWt.addTextChangedListener(this)
        floatingActionButton.setOnClickListener {
            if (edtPieces.text.isNullOrEmpty()) {
                tilPieces.isErrorEnabled = true
                tilPieces.error = "Enter Pieces"
                return@setOnClickListener
            }
            if (edtPiecesWt.text.isNullOrEmpty()) {
                tilPiecesWt.isErrorEnabled = true
                tilPiecesWt.error = "Enter Pieces Wt"
                return@setOnClickListener
            }
            if (edtRemainWt.text.isNullOrEmpty()) {
                tilRemainWt.isErrorEnabled = true
                tilRemainWt.error = "Enter Remain wt"
                return@setOnClickListener
            }
            calculate()
        }
    }

    private fun calculate() {
        val pieceValue = edtPieces.text?.trim().toString().toInt()
        val pieceWtValue = edtPiecesWt.text?.trim().toString().toInt()
        val remainWtValue = edtRemainWt.text?.trim().toString().toInt()
        val totalWt = (pieceValue * pieceWtValue) + remainWtValue
        val intent = Intent(this, RatesActivity::class.java)
        intent.putExtra(TOTAL_WT_VAL,totalWt)
        intent.putExtra(PIECE_VAL,pieceValue)
        intent.putExtra(PIECE_WT_VAL,pieceWtValue)
        intent.putExtra(REMAIN_WT_VAL,remainWtValue)
        startActivity(intent)
    }

    override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {

    }

    override fun onTextChanged(char: CharSequence?, p1: Int, p2: Int, p3: Int) {
        if (char?.length!! > 0) {
            floatingActionButton.visibility = View.VISIBLE
        }
    }

    override fun afterTextChanged(p0: Editable?) {

    }
}