package com.exomind.technical_test.ui.albums_list

import com.exomind.technical_test.R
import com.exomind.technical_test.domain.model.Either
import com.exomind.technical_test.domain.usecase.GetAlbumsListUseCase
import com.exomind.technical_test.ui.albums_list.mapper.AlbumUiMapper
import com.exomind.technical_test.ui.albums_list.model.PageAlbumsListErrorUi
import com.exomind.technical_test.ui.albums_list.model.PageAlbumsListSuccessUi
import com.exomind.technical_test.ui.common.BaseViewModel
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class AlbumsListViewModel(
    private val albumsListUseCase: GetAlbumsListUseCase,
    private val albumUiMapper: AlbumUiMapper
) :
    BaseViewModel<Either<PageAlbumsListSuccessUi, PageAlbumsListErrorUi>>() {

    fun getAlbumsForUser(userId: Int) {
        albumsListUseCase(userId)
            .subscribeOn(Schedulers.io())
            .map { albumsList ->
                albumsList.map { album ->
                    albumUiMapper.toUi(album)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { albumsUi ->
                    postUiData(
                        Either.Left(
                            PageAlbumsListSuccessUi(
                                albumsUi
                            )
                        )
                    )
                },
                onError = {
                    postUiData(
                        Either.Right(
                            PageAlbumsListErrorUi(
                                R.string.common_error_no_connection
                            )
                        )
                    )
                }
            )
    }
}