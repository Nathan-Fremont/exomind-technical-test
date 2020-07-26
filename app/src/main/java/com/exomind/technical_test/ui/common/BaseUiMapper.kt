package com.exomind.technical_test.ui.common

abstract class BaseUiMapper<DOMAIN, UI> {
    abstract fun toUi(domain: DOMAIN) : UI
}