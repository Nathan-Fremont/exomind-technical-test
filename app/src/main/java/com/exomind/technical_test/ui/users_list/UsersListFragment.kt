package com.exomind.technical_test.ui.users_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.SearchView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exomind.technical_test.R
import com.exomind.technical_test.domain.model.Either
import com.exomind.technical_test.ui.users_list.view.adapter.UsersListAdapter
import kotlinx.android.synthetic.main.fragment_users_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel
import timber.log.Timber

class UsersListFragment : Fragment() {

    private val viewModel: UsersListViewModel by viewModel()
    private val usersListAdapter: UsersListAdapter by lazy { UsersListAdapter() }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_users_list, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        fragment_users_list_recycler_view.apply {
            layoutManager = LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)
            adapter = usersListAdapter
        }

        viewModel.uiData.observe(viewLifecycleOwner, Observer { eitherUiData ->

            when (eitherUiData) {
                is Either.Left -> {
                    Timber.d("Got users = ${eitherUiData.value.users.size}")
                    usersListAdapter.submitList(eitherUiData.value.users)

                    if (eitherUiData.value.message != null) {
                        Toast.makeText(context, eitherUiData.value.message, Toast.LENGTH_SHORT)
                            .show()
                    }
                }
                is Either.Right -> {
                    Timber.d("Got error")
                    Toast.makeText(context, eitherUiData.value.error, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getUsers()

        fragment_users_list_search_view.setOnQueryTextListener(object :
            SearchView.OnQueryTextListener,
            androidx.appcompat.widget.SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                Timber.d("onQueryTextSubmit Search user with name $query")
                viewModel.searchUserByName(query!!)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }
        })

        fragment_users_list_search_view.setOnCloseListener {
            Timber.d("onCloseListener Get all users")
            viewModel.getUsers()
            false
        }
    }
}