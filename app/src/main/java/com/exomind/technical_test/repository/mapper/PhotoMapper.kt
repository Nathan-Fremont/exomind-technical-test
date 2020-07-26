package com.exomind.technical_test.repository.mapper

import com.exomind.technical_test.domain.model.Photo
import com.exomind.technical_test.repository.api.model.PhotoApi

class PhotoMapper : BaseDomainMapper<PhotoApi, Photo>() {
    override fun toDomain(api: PhotoApi): Photo {
        return Photo(
            api.id,
            api.albumId,
            api.title,
            api.url,
            api.thumbnailUrl
        )
    }
}