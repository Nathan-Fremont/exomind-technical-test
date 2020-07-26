package com.exomind.technical_test.domain.usecase

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.domain.model.User
import io.reactivex.Single

class GetUsersListUseCase(private val repository: Repository) : BaseUseCase<List<User>>() {
    override fun invoke(): Single<List<User>> {
        return repository.getUsersList()
    }
}