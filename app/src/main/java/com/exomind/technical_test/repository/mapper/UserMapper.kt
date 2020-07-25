package com.exomind.technical_test.repository.mapper

import com.exomind.technical_test.domain.model.User
import com.exomind.technical_test.repository.api.model.UserApi

class UserMapper : BaseDomainMapper<UserApi, User>() {
    override fun toDomain(api: UserApi): User {
        return User(
            api.id,
            api.name,
            api.username,
            api.email,
            api.phone,
            api.website
        )
    }
}