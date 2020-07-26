package com.exomind.technical_test.ui.users_list.mapper

import com.exomind.technical_test.domain.model.User
import com.exomind.technical_test.ui.common.BaseUiMapper
import com.exomind.technical_test.ui.users_list.model.UserUi

class UserUiMapper : BaseUiMapper<User, UserUi>() {
    override fun toUi(domain: User): UserUi {
        return UserUi(
            domain.id,
            domain.name,
            domain.username,
            domain.email,
            domain.phone,
            domain.website
        )
    }
}