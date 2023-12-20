package i.bobrov.social.exception

data class ApiError(
    val errorCode: String,
    val message: String,
)
