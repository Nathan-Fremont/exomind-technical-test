package com.exomind.technical_test.ui.users_list.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exomind.technical_test.ui.users_list.model.UserUi
import kotlinx.android.synthetic.main.holder_users_list.view.*

class UsersListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(userUi: UserUi) {
        itemView.apply {
            holder_users_list_name.text = userUi.name
            holder_users_list_email.text = userUi.email
            holder_users_list_telephone.text = userUi.phone
            holder_users_list_website.text = userUi.website
        }
    }
}