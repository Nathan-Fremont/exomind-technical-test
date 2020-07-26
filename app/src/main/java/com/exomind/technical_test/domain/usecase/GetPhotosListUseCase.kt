package com.exomind.technical_test.domain.usecase

import com.exomind.technical_test.domain.Repository
import com.exomind.technical_test.domain.model.Photo
import io.reactivex.Single

class GetPhotosListUseCase(private val repository: Repository) : BaseUseCase<Photo>() {
    operator fun invoke(userId: Int, albumId: Int) : Single<List<Photo>> {
        return repository.getPhotosForAlbum(userId, albumId)
    }
}