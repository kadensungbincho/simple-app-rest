package simple.app.rest

import kotlinx.coroutines.delay
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import simple.app.rest.config.Constants.API_PREFIX_V1
import simple.app.rest.dto.ResponseDTO

@RestController
@RequestMapping(API_PREFIX_V1)
class MockController {

    @GetMapping
    suspend fun sample(): ResponseDTO<Void> {
        delay(1000)
        return ResponseDTO.success()
    }
}
