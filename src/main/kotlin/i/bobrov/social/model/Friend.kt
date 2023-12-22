package i.bobrov.social.model

data class Friend(
    val id: Int,
    val first: User,
    val second: User,
)
