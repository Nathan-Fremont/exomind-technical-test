package com.exomind.technical_test.ui.users_list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.exomind.technical_test.R
import com.exomind.technical_test.domain.model.Either
import com.exomind.technical_test.ui.users_list.view.adapter.UsersListAdapter
import kotlinx.android.synthetic.main.fragment_users_list.*
import org.koin.androidx.viewmodel.ext.android.viewModel

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
                    usersListAdapter.submitList(eitherUiData.value.users)
                }
                is Either.Right -> {
                    Toast.makeText(context, eitherUiData.value.error, Toast.LENGTH_SHORT).show()
                }
            }
        })

        viewModel.getUsers()
    }
}