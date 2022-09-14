package com.kylix.mobiledeveloperintern2022suitmedia.adapter

import com.kylix.mobiledeveloperintern2022suitmedia.base.BaseDiffUtil
import com.kylix.mobiledeveloperintern2022suitmedia.model.User

class UserDiffUtil(
    old: List<User>,
    new: List<User>
): BaseDiffUtil<User, Int>(old, new) {
    override fun User.getItemIdentifier(): Int {
        return this.id
    }
}