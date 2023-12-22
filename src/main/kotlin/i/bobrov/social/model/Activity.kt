package i.bobrov.social.model

import java.util.Calendar

data class Activity(
    val id: Int,
    val user: User,
    val post: Post,
    val createTime: Calendar,
)
