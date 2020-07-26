package com.exomind.technical_test.ui.photos_list.view.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import com.exomind.technical_test.R
import com.exomind.technical_test.ui.photos_list.model.PhotoUi
import com.exomind.technical_test.ui.photos_list.view.PhotosListViewHolder

class PhotosListAdapter : ListAdapter<PhotoUi, PhotosListViewHolder>(diffCallback) {
    
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotosListViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.holder_photos_list, parent, false)
        
        val holder = PhotosListViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: PhotosListViewHolder, position: Int) {
        val photoUi = getItem(position)
        holder.bind(photoUi)
    }

    companion object {
        private val diffCallback = object : DiffUtil.ItemCallback<PhotoUi>() {
            override fun areItemsTheSame(
                oldItem: PhotoUi,
                newItem: PhotoUi
            ): Boolean {
                return oldItem.hashCode() == newItem.hashCode()
            }

            override fun areContentsTheSame(
                oldItem: PhotoUi,
                newItem: PhotoUi
            ): Boolean {
                return oldItem == newItem
            }
        }
    }
}