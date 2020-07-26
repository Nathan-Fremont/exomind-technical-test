package com.exomind.technical_test.ui.photos_list.mapper

import com.exomind.technical_test.domain.model.Photo
import com.exomind.technical_test.ui.common.BaseUiMapper
import com.exomind.technical_test.ui.photos_list.model.PhotoUi

class PhotoUiMapper : BaseUiMapper<Photo, PhotoUi>() {
    override fun toUi(domain: Photo): PhotoUi {
        return PhotoUi(
            domain.title,
            domain.url,
            domain.thumbnailUrl
        )
    }
}