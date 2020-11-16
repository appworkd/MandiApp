package com.appwork.holderitems

import com.appwork.mandiapp.R
import com.appwork.model.UserModel
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item
import kotlinx.android.synthetic.main.iv_user.*

/**
 * Created by Vivek Kumar belongs to APP WORK  on 15-11-2020.
 */
class UserItem(private val user: UserModel) : Item() {
    override fun bind(viewHolder: GroupieViewHolder, position: Int) {
        viewHolder.let {
            it.tvUserName.text = user.name
            it.tvUserAddress.text = user.address
            it.tvTotalUserAmount.text = user.amount.toString()
        }
    }

    override fun getLayout(): Int {
        return R.layout.iv_user
    }

}