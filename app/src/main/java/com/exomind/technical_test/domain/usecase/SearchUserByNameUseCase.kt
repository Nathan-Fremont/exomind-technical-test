package com.exomind.technical_test.domain.usecase

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.domain.model.User
import io.reactivex.Single

class SearchUserByNameUseCase(private val repository: Repository) : BaseUseCase<User>() {
    operator fun invoke(username: String): Single<List<User>> {
        return repository.searchUserByName(username)
    }
}