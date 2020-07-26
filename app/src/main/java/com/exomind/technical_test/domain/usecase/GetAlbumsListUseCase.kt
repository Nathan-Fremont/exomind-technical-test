package com.exomind.technical_test.domain.usecase

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.domain.model.Album
import io.reactivex.Single

class GetAlbumsListUseCase(private val repository: Repository) : BaseUseCase<Album>() {
    operator fun invoke(userId: Int): Single<List<Album>> {
        return repository.getAlbumsForUser(userId)
    }
}