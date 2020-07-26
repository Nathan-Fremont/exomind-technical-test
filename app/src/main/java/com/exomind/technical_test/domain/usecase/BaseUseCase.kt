package com.exomind.technical_test.domain.usecase

import io.reactivex.Single

abstract class BaseUseCase<DOMAIN> {
    abstract operator fun invoke(): Single<DOMAIN>
}