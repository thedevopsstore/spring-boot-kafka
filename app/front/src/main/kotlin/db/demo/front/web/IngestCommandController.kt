package db.demo.front.web

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import db.demo.front.service.ProcessEventService
import db.demo.model.TestCommand
import db.demo.rnd
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.PositiveOrZero

@RestController
@RequestMapping("/api/v1/command")
@Validated
class IngestCommandController(
  private val processEventService: ProcessEventService
) {

  @ApiOperation(value = "Post test command")
  @PostMapping
  fun postEvent(@Validated @RequestBody request: CommandRequest) {
    processEventService.processCommand(
      TestCommand(
        message = request.message,
        loadFront = request.loadFront,
        loadBack = request.loadBack,
        calculated = rnd.nextDouble()
      )
    )
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class CommandRequest(
  @ApiModelProperty(
    value = "Optional message",
    example = "Hello world",
    allowEmptyValue = true,
  )
  val message: String? = null,

  @ApiModelProperty(
    value = "Generate load on front",
    example = "100",
    allowEmptyValue = true,
  )
  @field:PositiveOrZero
  val loadFront: Int = 0,

  @ApiModelProperty(
    value = "Generate load on back",
    example = "100",
    allowEmptyValue = true,
  )
  @field:PositiveOrZero
  val loadBack: Int = 0,
)