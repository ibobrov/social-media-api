package i.bobrov.social.exception

import org.springframework.http.HttpStatus

abstract class BaseException(
    val errorCode: String,
    override val message: String,
    val status: HttpStatus,
) : RuntimeException(message)

class ObjectNotFoundException(
    id: Int,
) : BaseException(
        errorCode = "object.not.found",
        message = "Object with id = $id not found",
        status = HttpStatus.NOT_FOUND,
    )

class ObjectNotCreatedException(
    obj: Any,
) : BaseException(
        errorCode = "object.not.created",
        message = "Object = $obj not created",
        status = HttpStatus.BAD_REQUEST,
    )
