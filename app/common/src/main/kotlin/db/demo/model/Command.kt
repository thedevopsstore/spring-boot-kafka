package db.demo.model

import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonTypeInfo
import java.util.*

object Topics {
  const val testCommand = "testCommand"
  const val testEvent = "testEvent"
}

@JsonIgnoreProperties(ignoreUnknown = true)
@JsonTypeInfo(use = JsonTypeInfo.Id.CLASS, include = JsonTypeInfo.As.PROPERTY, property = "class")
abstract class Command {
  val eventId: String = UUID.randomUUID().toString()
  val timestamp: Long = System.currentTimeMillis()
}

@JsonInclude(JsonInclude.Include.NON_NULL)
data class TestCommand(
  val message: String? = null,
  val loadFront: Int = 0,
  val loadBack: Int = 0,
  val calculated: Double,
) : Command()
