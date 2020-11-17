package com.appwork.mandiapp

import android.app.ProgressDialog.show
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.appwork.mandiapp.fragments.ChangeValueFragment
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

class ConsentActivity : AppCompatActivity(), ChangeValueFragment.ChangeValueCallback {
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
            val strTotalWt = total.toString() + getString(R.string.txt_kgs)
            tvTotalWtValue.text = strTotalWt
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
            cropAmount = (calculateCropAmount(total!!, rates!!)) / 100;
            val strCropValue = cropAmount!!.toString() + getString(R.string.txt_rs)
            tvTotalCropValue.text = strCropValue
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
        val strAdatCharge = adatTotal.toInt().toString() + getString(R.string.txt_rs)
        tvTotalAadatCharges.text = strAdatCharge
        tvCovFee.text = adatTotal.toString()
        val totalAmount = cropAmount!! - adatTotal
        val strTotalAmount = totalAmount.toString() + getString(R.string.txt_rs)
        tvTotalAMount.text = strTotalAmount
        btnSave.setOnClickListener {
            val intent = Intent(this, CustomerBillDetailsActivity::class.java)
            intent.putExtra(TOTAL_WT_VAL, total)
            intent.putExtra(PIECE_VAL, pieces)
            intent.putExtra(PIECE_WT_VAL, pieceWt)
            intent.putExtra(REMAIN_WT_VAL, remainWt)
            intent.putExtra(RATES_VAL, rates)
            intent.putExtra(CROP_AMOUNT_VAL, cropAmount)
            intent.putExtra(AADAT_CHARGES_VAL, adatTotal)
            intent.putExtra(FINAL_TOTAL_VAL, totalAmount)
            startActivity(intent)
        }
        tvTotalAMount.setOnClickListener {
           val fragment=ChangeValueFragment()
            val arg = Bundle()
                arg.putString("oldValue",strTotalAmount)
            fragment.arguments=arg
            fragment.show(supportFragmentManager,"")
        }
    }

    private fun calculateCropAmount(total: Int, rates: Int): Int {
        return total * rates
    }

    override fun getValues(oldValue: String, newValue: String) {
        tvTotalAMount.text=newValue+getString(R.string.txt_rs)
    }
}