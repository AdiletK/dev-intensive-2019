package ru.skillbranch.devintensive.extension

import ru.skillbranch.devintensive.models.User
import ru.skillbranch.devintensive.models.UserView
import ru.skillbranch.devintensive.utils.Utils
import java.util.*


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



