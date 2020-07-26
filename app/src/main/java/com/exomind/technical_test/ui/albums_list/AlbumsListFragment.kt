package com.exomind.technical_test.ui.albums_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.exomind.technical_test.R
import com.exomind.technical_test.domain.model.Either
import com.exomind.technical_test.ui.albums_list.view.adapter.AlbumsListAdapter
import com.exomind.technical_test.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_albums_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class AlbumsListFragment : BaseFragment(true) {

    private val albumsViewModel: AlbumsListViewModel by viewModel()
    private val navArgs: AlbumsListFragmentArgs by navArgs()
    private val albumsListAdapter: AlbumsListAdapter by lazy { AlbumsListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_albums_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        Timber.d("Arrive in list of albums with user ${navArgs.selectedUserUi.name} [${navArgs.selectedUserUi.id}]")
        fragment_albums_list_recycler_view.apply {
            layoutManager = GridLayoutManager(context, 2, LinearLayoutManager.VERTICAL, false)
            adapter = albumsListAdapter
        }

        albumsViewModel.uiData.observe(viewLifecycleOwner, Observer { eitherUiPageData ->

            when (eitherUiPageData) {
                is Either.Left -> {
                    albumsListAdapter.submitList(eitherUiPageData.value.albums)
                }

                is Either.Right -> {
                    Toast.makeText(context, eitherUiPageData.value.error, Toast.LENGTH_SHORT).show()
                    findNavController().popBackStack()
                }
            }
        })

        albumsViewModel.getAlbumsForUser(navArgs.selectedUserUi.id)
    }
}