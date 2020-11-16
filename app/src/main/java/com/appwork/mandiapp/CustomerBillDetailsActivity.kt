package com.appwork.mandiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_customer_bill_details.*
import kotlinx.android.synthetic.main.container_seller.*

class CustomerBillDetailsActivity : AppCompatActivity() {
    private var pieces: Int? = null
    private var pieceWt: Int? = null
    private var remainWt: Int? = null
    private var totalWt: Int? = null
    private var rates: Int? = null
    private var aadatCharge: Int? = null
    private var totalAmount: Int? = null
    private var cropAmount: Int? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_bill_details)
        if (intent.hasExtra("pieces")) {
            pieces = intent.getIntExtra("pieces", 0)
            cPieces.text=pieces.toString()
        }
        if (intent.hasExtra("pieceWt")) {
            pieceWt = intent.getIntExtra("pieceWt", 0)
            cWt.text=pieceWt.toString()
        }
        if (intent.hasExtra("remainWt")) {
            remainWt = intent.getIntExtra("remainWt", 0)
            cRemainWt.text=remainWt.toString()
        }
        if (intent.hasExtra("total")) {
            totalWt = intent.getIntExtra("total", 0)
            cTotalWt.text=totalWt.toString()
        }
        if (intent.hasExtra("rates")) {
            rates = intent.getIntExtra("rates", 0)
            cRates.text=rates.toString()
        }
        if (intent.hasExtra("cropAmount")) {
            cropAmount = intent.getIntExtra("cropAmount", 0)
            tvcCropAmount.text=cropAmount.toString()
        }
        if (intent.hasExtra("aadatCharge")) {
            aadatCharge = intent.getIntExtra("aadatCharge", 0)
            tvCAadatTotal.text="- "+aadatCharge.toString()
        }
        if (intent.hasExtra("finalTotal")) {
            totalAmount = intent.getIntExtra("finalTotal", 0)
            tvCAmount.text=totalAmount.toString()
        }
    }
}