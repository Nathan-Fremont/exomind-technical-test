package com.exomind.technical_test.ui.photos_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.exomind.technical_test.R
import com.exomind.technical_test.domain.model.Either
import com.exomind.technical_test.ui.common.BaseFragment
import com.exomind.technical_test.ui.common.BaseFragmentActionBarArgument
import com.exomind.technical_test.ui.photos_list.view.adapter.PhotosListAdapter
import kotlinx.android.synthetic.main.fragment_photos_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class PhotosListFragment : BaseFragment(
    BaseFragmentActionBarArgument(
        R.string.fragment_list_photos_title,
        shouldShowActionBar = true,
        shouldShowBackButton = true
    )
) {

    private val photosListViewModel: PhotosListViewModel by viewModel()
    private val navArgs: PhotosListFragmentArgs by navArgs()
    private val photosListAdapter: PhotosListAdapter by lazy { PhotosListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_photos_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("Will show photos for album ${navArgs.selectedAlbumUi.title} [${navArgs.selectedAlbumUi.id}]")
        fragment_photos_list_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = photosListAdapter
        }

        photosListViewModel.uiData.observe(viewLifecycleOwner, Observer { eitherUiPageData ->

            when (eitherUiPageData) {
                is Either.Left -> {
                    Timber.d("Got ${eitherUiPageData.value.photos.size} photos for the selected album ${navArgs.selectedAlbumUi.id}")
                    photosListAdapter.submitList(eitherUiPageData.value.photos)
                }

                is Either.Right -> {
                    Toast
                        .makeText(context, eitherUiPageData.value.error, Toast.LENGTH_SHORT)
                        .show()
                }
            }
        })

        photosListViewModel.getPhotosForAlbum(navArgs.selectedAlbumUi)
    }
}