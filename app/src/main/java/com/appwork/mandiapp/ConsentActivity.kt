package com.appwork.mandiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_consent)
        if (intent.hasExtra("pieces")) {
            pieces = intent.getIntExtra("pieces", 0)
            tvPieces.text = pieces.toString()
        }
        if (intent.hasExtra("rates")) {
            rates = intent.getIntExtra("rates", 0)
            tvCropRateValue.text = rates.toString()
        }
        if (intent.hasExtra("total")) {
            total = intent.getIntExtra("total", 0)
            tvTotalWtValue.text = total.toString() + " Kgs"
            tvTotalWtCropValue.text = total.toString()
        }
        if (intent.hasExtra("wtChanges")) {
            wtCharges = intent.getIntExtra("wtChanges", 0)

        }
        if (intent.hasExtra("pieceWt")) {
            pieceWt = intent.getIntExtra("pieceWt", 0)
            tvWtPiecesValue.text = pieceWt.toString()
        }
        if (intent.hasExtra("remainWt")) {
            remainWt = intent.getIntExtra("remainWt", 0)
            tvRemainWtValue.text = remainWt.toString()
        }
        if (intent.hasExtra("aadatCharge")) {
            aadatCharges = intent.getIntExtra("aadatCharge", 0)
            adatChargeValue = aadatCharges!!.toFloat() / 1000
        }
        if (total != null && rates != null) {
            cropAmount = (calculateCropAmount(total!!, rates!!).toInt()) / 100;
            tvTotalCropValue.text = cropAmount!!.toString() + " Rs."
            tvCropTotal.text = cropAmount.toString()
        }
        if (pieces != null && wtCharges != null) {
            weightingCharges = calculateCropAmount(pieces!!, wtCharges!!)
            tvWtCharges.text = weightingCharges.toString()
        }
        val aadatChanrges = (cropAmount!! * adatChargeValue!!).toInt()
        tvaadatCharges.text = aadatChanrges.toString()
        val adatTotal = aadatChanrges + weightingCharges!!
        tvTotalAadatCharges.text = adatTotal.toInt().toString() + " Rs."
        tvCovFee.text = adatTotal.toString()
        val totalAmount = cropAmount!! - adatTotal
        tvTotalAMount.text = totalAmount.toInt().toString() + " Rs."

    }

    private fun calculateCropAmount(total: Int, rates: Int): Int {
        return total * rates
    }
}