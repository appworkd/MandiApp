package com.appwork.mandiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.appwork.util.Constants.AADAT_CHARGES_VAL
import com.appwork.util.Constants.CROP_AMOUNT_VAL
import com.appwork.util.Constants.FINAL_TOTAL_VAL
import com.appwork.util.Constants.PIECE_VAL
import com.appwork.util.Constants.PIECE_WT_VAL
import com.appwork.util.Constants.RATES_VAL
import com.appwork.util.Constants.REMAIN_WT_VAL
import com.appwork.util.Constants.TOTAL_WT_VAL
import kotlinx.android.synthetic.main.activity_customer_bill_details.*
import kotlinx.android.synthetic.main.container_seller.*
import org.joda.time.LocalDate

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
        if (intent.hasExtra(PIECE_VAL)) {
            pieces = intent.getIntExtra(PIECE_VAL, 0)
            cPieces.text = pieces.toString()
        }
        if (intent.hasExtra(PIECE_WT_VAL)) {
            pieceWt = intent.getIntExtra(PIECE_WT_VAL, 0)
            cWt.text = pieceWt.toString()
        }
        if (intent.hasExtra(REMAIN_WT_VAL)) {
            remainWt = intent.getIntExtra(REMAIN_WT_VAL, 0)
            cRemainWt.text = remainWt.toString()
        }
        if (intent.hasExtra(TOTAL_WT_VAL)) {
            totalWt = intent.getIntExtra(TOTAL_WT_VAL, 0)
            cTotalWt.text = totalWt.toString()
        }
        if (intent.hasExtra(RATES_VAL)) {
            rates = intent.getIntExtra(RATES_VAL, 0)
            cRates.text = rates.toString()
        }
        if (intent.hasExtra(CROP_AMOUNT_VAL)) {
            cropAmount = intent.getIntExtra(CROP_AMOUNT_VAL, 0)
            tvcCropAmount.text = cropAmount.toString()
        }
        if (intent.hasExtra(AADAT_CHARGES_VAL)) {
            aadatCharge = intent.getIntExtra(AADAT_CHARGES_VAL, 0)
            tvCAadatTotal.text = "- ${aadatCharge.toString()}"
        }
        if (intent.hasExtra(FINAL_TOTAL_VAL)) {
            totalAmount = intent.getIntExtra(FINAL_TOTAL_VAL, 0)
            tvCAmount.text = totalAmount.toString()
        }
        setDate()
    }

    private fun setDate() {
        val date = LocalDate.now()
        tvBillDate.text = date.toString()
    }
}