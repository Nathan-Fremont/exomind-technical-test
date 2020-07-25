package com.exomind.technical_test.domain.usecase

import com.exomind.technical_test.domain.Repository
import io.reactivex.Single

abstract class BaseUseCase<DOMAIN> {
    abstract operator fun invoke(): Single<DOMAIN>
}