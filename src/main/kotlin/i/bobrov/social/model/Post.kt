package i.bobrov.social.model

import java.util.Calendar

data class Post(
    var id: Int,
    val title: String,
    val text: String,
    val imageUrl: String,
    val createTime: Calendar,
    val userId: Long,
)
