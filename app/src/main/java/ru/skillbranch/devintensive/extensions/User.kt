package ru.skillbranch.devintensive.extensions

import ru.skillbranch.devintensive.models.data.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils


fun User.toUserView(): UserView{

    val nick =  Utils.transliteration("$firstName $lastName")
    val initials = Utils.toInitials(firstName,lastName)
    val status = if (lastVisit == null) "Never been" else if (isOnline) "online" else "Last seen "

    return UserView(
        id,
        fullName = "$firstName $lastName",
        nickName = nick,
        initials = initials,
        avatar = avatar,
        status = status
    )
}



