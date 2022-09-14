package com.kylix.mobiledeveloperintern2022suitmedia.util

import com.kylix.mobiledeveloperintern2022suitmedia.data.source.remote.response.UserResponse
import com.kylix.mobiledeveloperintern2022suitmedia.model.User

fun UserResponse.toUser() = User(
    id, email, firstName, lastName, avatar
)