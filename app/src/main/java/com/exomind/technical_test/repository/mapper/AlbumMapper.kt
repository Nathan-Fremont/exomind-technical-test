package com.exomind.technical_test.repository.mapper

import com.exomind.technical_test.domain.model.Album
import com.exomind.technical_test.repository.api.model.AlbumApi

class AlbumMapper : BaseDomainMapper<AlbumApi, Album>() {
    override fun toDomain(api: AlbumApi): Album {
        return Album(
            api.id,
            api.userId,
            api.title
        )
    }
}