package com.appwork.mandiapp

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appwork.util.Constants
import com.appwork.util.Constants.AADAT_CHARGES_VAL
import com.appwork.util.Constants.CROP_AMOUNT_VAL
import com.appwork.util.Constants.FINAL_TOTAL_VAL
import com.appwork.util.Constants.PIECE_FOR_CAL_VAL
import com.appwork.util.Constants.PIECE_VAL
import com.appwork.util.Constants.PIECE_WT_VAL
import com.appwork.util.Constants.RATES_VAL
import com.appwork.util.Constants.REMAIN_WT_VAL
import com.appwork.util.Constants.TOTAL_WT_VAL
import com.appwork.util.Constants.WT_CHARGES_VAL
import kotlinx.android.synthetic.main.activity_consent.*

class ConsentActivity : AppCompatActivity() {
    private var weightingCharges: Int? = null
    private var pieces: Int? = null
    private var rates: Int? = null
    private var total: Int? = null
    private var wtCharges: Int? = null
    private var pieceWt: Int? = null
    private var remainWt: Int? = null
    private var cropAmount: Int? = null
    private var aadatCharges: Int? = null
    private var adatChargeValue: Float? = null
    private var piecesCal: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consent)
        if (intent.hasExtra(PIECE_VAL)) {
            pieces = intent.getIntExtra(PIECE_VAL, 0)
            tvPieces.text = pieces.toString()
        }
        if (intent.hasExtra(PIECE_WT_VAL)) {
            pieceWt = intent.getIntExtra(PIECE_WT_VAL, 0)
            tvWtPiecesValue.text = pieceWt.toString()
        }
        if (intent.hasExtra(REMAIN_WT_VAL)) {
            remainWt = intent.getIntExtra(REMAIN_WT_VAL, 0)
            tvRemainWtValue.text = remainWt.toString()
        }
        if (intent.hasExtra(RATES_VAL)) {
            rates = intent.getIntExtra(RATES_VAL, 0)
            tvCropRateValue.text = rates.toString()
        }
        if (intent.hasExtra(TOTAL_WT_VAL)) {
            total = intent.getIntExtra(TOTAL_WT_VAL, 0)
            tvTotalWtValue.text = total.toString() + " Kgs"
            tvTotalWtCropValue.text = total.toString()
        }
        if (intent.hasExtra(WT_CHARGES_VAL)) {
            wtCharges = intent.getIntExtra(WT_CHARGES_VAL, 0)
        }
        if (intent.hasExtra(AADAT_CHARGES_VAL)) {
            aadatCharges = intent.getIntExtra(AADAT_CHARGES_VAL, 0)
            adatChargeValue = aadatCharges!!.toFloat() / 1000
        }
        if (total != null && rates != null) {
            cropAmount = (calculateCropAmount(total!!, rates!!).toInt()) / 100;
            tvTotalCropValue.text = cropAmount!!.toString() + " Rs."
            tvCropTotal.text = cropAmount.toString()
        }
        if (intent.hasExtra(PIECE_FOR_CAL_VAL)) {
            piecesCal = intent.getIntExtra(PIECE_FOR_CAL_VAL, 0)
        }
        if (piecesCal != null && wtCharges != null) {
            weightingCharges = calculateCropAmount(piecesCal!!, wtCharges!!)
            tvWtCharges.text = weightingCharges.toString()
        }

        val aadatChanrges = (cropAmount!! * adatChargeValue!!).toInt()
        tvaadatCharges.text = aadatChanrges.toString()
        val adatTotal = aadatChanrges + weightingCharges!!
        tvTotalAadatCharges.text = adatTotal.toInt().toString() + " Rs."
        tvCovFee.text = adatTotal.toString()
        val totalAmount = cropAmount!! - adatTotal
        tvTotalAMount.text = totalAmount.toInt().toString() + " Rs."
        btnSave.setOnClickListener {
            val intent = Intent(this, CustomerBillDetailsActivity::class.java)
            intent.putExtra(TOTAL_WT_VAL,total)
            intent.putExtra(PIECE_VAL,pieces)
            intent.putExtra(PIECE_WT_VAL,pieceWt)
            intent.putExtra(REMAIN_WT_VAL,remainWt)
            intent.putExtra(RATES_VAL,rates)
            intent.putExtra(CROP_AMOUNT_VAL,cropAmount)
            intent.putExtra(AADAT_CHARGES_VAL,adatTotal)
            intent.putExtra(FINAL_TOTAL_VAL,totalAmount)
            startActivity(intent)
        }
    }

    private fun calculateCropAmount(total: Int, rates: Int): Int {
        return total * rates
    }
}