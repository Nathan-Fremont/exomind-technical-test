package com.exomind.technical_test.ui.albums_list.mapper

import com.exomind.technical_test.domain.model.Album
import com.exomind.technical_test.ui.albums_list.model.AlbumUi
import com.exomind.technical_test.ui.common.BaseUiMapper

class AlbumUiMapper : BaseUiMapper<Album, AlbumUi>() {
    override fun toUi(domain: Album): AlbumUi {
        return AlbumUi(
            domain.id,
            domain.title
        )
    }
}