package com.exomind.technical_test.ui.users_list

import com.exomind.technical_test.R
import com.exomind.technical_test.domain.model.Either
import com.exomind.technical_test.domain.usecase.GetUsersListUseCase
import com.exomind.technical_test.ui.common.BaseViewModel
import com.exomind.technical_test.ui.users_list.mapper.UserUiMapper
import com.exomind.technical_test.ui.users_list.model.PageUsersListErrorUi
import com.exomind.technical_test.ui.users_list.model.PageUsersListSuccessUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class UsersListViewModel(
    private val usersListUseCase: GetUsersListUseCase,
    private val userUiMapper: UserUiMapper
) :
    BaseViewModel<Either<PageUsersListSuccessUi, PageUsersListErrorUi>>() {

    fun getUsers() {
        usersListUseCase()
            .subscribeOn(Schedulers.io())
            .map { usersList ->
                usersList.map { user ->
                    userUiMapper.toUi(user)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { listUsers ->
                    postUiData(Either.Left(PageUsersListSuccessUi((listUsers))))
                },
                onError = {
                    postUiData(
                        Either.Right(
                            PageUsersListErrorUi(
                                R.string.common_error_no_connection
                            )
                        )
                    )
                }
            )
    }
}