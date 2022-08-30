package db.demo.front.service

import db.demo.front.kafka.KafkaService
import db.demo.loadFactor
import db.demo.logger
import db.demo.model.Command
import db.demo.model.TestCommand
import org.springframework.scheduling.annotation.Async
import org.springframework.stereotype.Service
import kotlin.math.atan
import kotlin.math.tan
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@Service
class ProcessEventService(
  private val kafkaService: KafkaService
) {
  private val log = logger()

  @Async("eventExecutor")
  fun processCommand(command: Command) {
    when (command) {
      is TestCommand -> {
        processTestCommand(command)
      }
      else -> throw IllegalArgumentException("Unknown Event: ${command.javaClass}")
    }
  }

  @OptIn(ExperimentalTime::class)
  private fun processTestCommand(command: TestCommand) {
    if (command.loadFront != 0) {
      measureTime {
        val seed = Pair(0, command.calculated)
        val calc = generateSequence(seed) {
          val seq = it.first
          val value = if (seq % 2 == 0) tan(it.second) else atan(it.second)
          Pair(it.first + 1, value)
        }
          .take(command.loadFront * loadFactor)
          .lastOrNull() ?: seed
        log.info("Calculated: ${calc.second} in ${calc.first + 1} iterations")
        kafkaService.sendCommand(command.copy(calculated = calc.second))
      }.apply {
        log.info("Time: $this")
      }
    } else {
      kafkaService.sendCommand(command)
    }
  }
}
