package simple.app.rest.config

object Constants {
    const val API_PREFIX_V1 = "/api/v1"
}

enum class ResponseCode(val code: String, val message: String) {
    OK("200", "OK");
}
