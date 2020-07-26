package com.exomind.technical_test.ui.albums_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.exomind.technical_test.R
import com.exomind.technical_test.ui.common.BaseFragment
import kotlinx.android.synthetic.main.fragment_albums_list.*
import timber.log.Timber

class AlbumsListFragment : BaseFragment(true) {

    private val navArgs: AlbumsListFragmentArgs by navArgs()

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
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
        }

    }
}