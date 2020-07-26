package com.exomind.technical_test.ui.photos_list

import com.exomind.technical_test.R
import com.exomind.technical_test.domain.model.Either
import com.exomind.technical_test.domain.usecase.GetPhotosListUseCase
import com.exomind.technical_test.ui.albums_list.model.AlbumUi
import com.exomind.technical_test.ui.common.BaseViewModel
import com.exomind.technical_test.ui.photos_list.mapper.PhotoUiMapper
import com.exomind.technical_test.ui.photos_list.model.PagePhotosListErrorUi
import com.exomind.technical_test.ui.photos_list.model.PagePhotosListSuccessUi
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.rxkotlin.subscribeBy
import io.reactivex.schedulers.Schedulers

class PhotosListViewModel(
    private val getPhotosListUseCase: GetPhotosListUseCase,
    private val photoUiMapper: PhotoUiMapper
) :
    BaseViewModel<Either<PagePhotosListSuccessUi, PagePhotosListErrorUi>>() {

    fun getPhotosForAlbum(albumUi: AlbumUi) {
        getPhotosListUseCase(albumUi.id, albumUi.id)
            .subscribeOn(Schedulers.io())
            .map { photosList ->
                photosList.map { photo ->
                    photoUiMapper.toUi(photo)
                }
            }
            .observeOn(AndroidSchedulers.mainThread())
            .subscribeBy(
                onSuccess = { photosUiList ->
                    postUiData(
                        Either.Left(
                            PagePhotosListSuccessUi(
                                photosUiList
                            )
                        )
                    )
                },
                onError = {
                    postUiData(
                        Either.Right(
                            PagePhotosListErrorUi(
                                R.string.common_error_no_connection
                            )
                        )
                    )
                }
            )
    }
}