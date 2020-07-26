package com.exomind.technical_test.ui.albums_list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.exomind.technical_test.R
import com.exomind.technical_test.ui.albums_list.model.AlbumUi
import com.exomind.technical_test.ui.albums_list.view.AlbumsListViewHolder

class AlbumsListAdapter(private val clickOnAlbumHolder: (albumUi: AlbumUi) -> Unit) : ListAdapter<AlbumUi, AlbumsListViewHolder>(diffCallback) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AlbumsListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.holder_albums_list, parent, false)

        val holder = AlbumsListViewHolder(view).apply {
            itemView.setOnClickListener {
                val albumUi = getItem(adapterPosition)
                clickOnAlbumHolder(albumUi)
            }
        }
        return holder
    }

    override fun onBindViewHolder(holder: AlbumsListViewHolder, position: Int) {
        val albumUi = getItem(position)
        holder.bind(albumUi)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<AlbumUi>() {
            override fun areItemsTheSame(
                oldItem: AlbumUi,
                newItem: AlbumUi
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: AlbumUi,
                newItem: AlbumUi
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}