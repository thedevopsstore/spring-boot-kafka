package db.demo.reader.web

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import db.demo.logger
import db.demo.reader.model.TestEntity
import db.demo.reader.repository.TestRepository
import io.swagger.annotations.ApiModelProperty
import io.swagger.annotations.ApiOperation
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.validation.annotation.Validated
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.ModelAttribute
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import javax.validation.constraints.Min
import javax.validation.constraints.PositiveOrZero

@RestController
@RequestMapping("/api/v1/testEntity")
class TestEntityController(
  val testRepository: TestRepository
) {

  private val log = logger()

  @ApiOperation(value = "Post test command")
  @GetMapping
  fun list(@ModelAttribute @Validated page: Pagination): PageResponse<TestEntity> {
    return toPageResponse(testRepository.findAll(page.toPageRequest()))
  }
}

@JsonIgnoreProperties(ignoreUnknown = true)
data class Pagination(
  @ApiModelProperty("Defaults to '0'. Allows to specify a page with elements to get.")
  @field:PositiveOrZero(message = "can not be negative")
  val page: @javax.validation.constraints.Min(value = 0L) Int = 0,

  @ApiModelProperty("Defaults to '50'. Allows to specify how many elements to get per page.")
  @field:Min(value = 1L, message = "should be positive")
  val size: Int = 50,
)

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonPropertyOrder(
  "totalElements",
  "first",
  "last",
  "number",
  "numberOfElements",
  "size",
  "sort",
  "totalPages",
  "content",
)
data class PageResponse<T>(
  val content: List<T>,
  val first: Boolean,
  val last: Boolean,
  val number: Int,
  val size: Int,
  val numberOfElements: Int,
  val totalElements: Long,
  val totalPages: Int,
)

fun Pagination.toPageRequest(): PageRequest {
  return PageRequest.of(page, size)
}

fun <T> toPageResponse(page: Page<T>):PageResponse<T>{
  return PageResponse(
    content = page.content,
    totalPages = page.totalPages,
    totalElements = page.totalElements,
    number = page.number,
    size = page.size,
    numberOfElements = page.numberOfElements,
    first = page.isFirst,
    last = page.isLast,
  )
}
