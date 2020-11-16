package com.appwork.model

import com.appwork.mandiapp.R
import com.xwray.groupie.kotlinandroidextensions.GroupieViewHolder
import com.xwray.groupie.kotlinandroidextensions.Item

/**
 * Created by Vivek Kumar belongs to APP WORK  on 15-11-2020.
 */
data class UserModel(
     val id: String,
     val name: String,
     val address: String,
     val imageUri: String,
     val createdOn: Double,
     val updatedOn: Double,
     val amount: Double
)