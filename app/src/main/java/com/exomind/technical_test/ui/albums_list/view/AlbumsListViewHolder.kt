package com.exomind.technical_test.ui.albums_list.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exomind.technical_test.ui.albums_list.model.AlbumUi
import kotlinx.android.synthetic.main.holder_albums_list.view.*

class AlbumsListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun bind(albumUi: AlbumUi) {
        itemView.apply {
            holder_albums_list_number.text = "${albumUi.id}"
            holder_albums_list_title.text = albumUi.title
        }
    }
}