package com.exomind.technical_test.ui.users_list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.exomind.technical_test.R
import com.exomind.technical_test.ui.users_list.model.UserUi
import com.exomind.technical_test.ui.users_list.view.UsersListViewHolder
import timber.log.Timber

class UsersListAdapter : ListAdapter<UserUi, UsersListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): UsersListViewHolder {
        val view =
            LayoutInflater
                .from(parent.context)
                .inflate(R.layout.holder_users_list, parent, false)
        val holder = UsersListViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: UsersListViewHolder, position: Int) {
        val userUi = getItem(position)
        holder.bind(userUi)
    }

    override fun getItemId(position: Int): Long {
        val userUi = getItem(position)
        return userUi.hashCode().toLong()
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<UserUi>() {
            override fun areItemsTheSame(
                oldItem: UserUi,
                newItem: UserUi
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: UserUi,
                newItem: UserUi
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}