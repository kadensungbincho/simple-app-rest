package simple.app.rest.dto

import simple.app.rest.config.ResponseCode

data class ResponseDTO<T>(
    val code: String,
    var message: String,
    var data: T? = null
) {

    constructor(responseCode: ResponseCode) : this(responseCode.code, responseCode.message)

    companion object {
        fun <T> success(): ResponseDTO<T> {
            return ResponseDTO(ResponseCode.OK)
        }
    }
}
