package com.appwork.mandiapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.UserManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.appwork.holderitems.UserItem
import com.appwork.model.UserModel
import com.xwray.groupie.GroupAdapter
import com.xwray.groupie.GroupieViewHolder
import kotlinx.android.synthetic.main.activity_user_list.*

class UserListActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_user_list)
        setUpRecyclerView()
    }

    private fun setUpRecyclerView() {
    val userAdapter=   GroupAdapter<GroupieViewHolder>().apply {
            addAll(getData())
        }
        userList.apply {
            adapter = userAdapter
            layoutManager = LinearLayoutManager(this@UserListActivity,LinearLayoutManager.VERTICAL,false)
        }
    }

    fun getData(): ArrayList<UserItem> {
        val list = arrayListOf<UserItem>()
        for (i in 0 until 10) {
            val currentModel = UserModel(
                i.toString(),
                "Vivek $i",
                "H.No. $i Radha Puram",
                "",
                0.0,
                0.0,
                0.0
            )
            val userItem = UserItem(currentModel)
            list.add(userItem)
        }
        return list
    }

    fun toggleViews() {

    }
}