package com.exomind.technical_test.ui.photos_list.view

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.exomind.technical_test.ui.photos_list.model.PhotoUi
import kotlinx.android.synthetic.main.holder_photos_list.view.*

class PhotosListViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    fun bind(photoUi: PhotoUi) {
        itemView.apply {
            holder_photos_list_title.text = photoUi.title
        }
    }
}