package i.bobrov.social.model

import java.util.Calendar

data class ChatMessage(
    val id: Int,
    val text: String,
    val sendTime: Calendar,
    val first: User,
    val second: User,
)
