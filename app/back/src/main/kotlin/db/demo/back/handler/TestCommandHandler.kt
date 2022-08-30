package db.demo.back.handler

import db.demo.back.model.TestEntity
import db.demo.back.repository.TestRepository
import db.demo.loadFactor
import db.demo.logger
import db.demo.mapper
import db.demo.model.TestCommand
import db.demo.model.Topics.testCommand
import org.springframework.kafka.annotation.KafkaListener
import org.springframework.messaging.handler.annotation.Payload
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import kotlin.math.atan
import kotlin.math.tan
import kotlin.time.ExperimentalTime
import kotlin.time.measureTime

@Service
class TestCommandHandler(
  private val testRepository: TestRepository
) {

  private val log = logger()

  @OptIn(ExperimentalTime::class)
  @KafkaListener(
    topics = [testCommand],
    concurrency = "2"
  )
  fun process(@Payload command: TestCommand) {
    log.info("Received: ${mapper.writeValueAsString(command)}")
    val seed = Pair(0, command.calculated)

    when {
      command.loadBack != 0 -> {
        measureTime {
          val calc = generateSequence(seed) {
            val seq = it.first
            val value = if (seq % 2 == 0) tan(it.second) else atan(it.second)
            Pair(it.first + 1, value)
          }
            .take(command.loadBack * loadFactor)
            .lastOrNull() ?: seed
          log.info("Calculated: ${calc.second} in ${calc.first + 1} iterations")
          saveProcessed(message = command.message, calculated = calc.second)
        }.apply {
          log.info("Time: $this")
        }
      }
      else -> {
        saveProcessed(message = command.message, calculated = command.calculated)
      }
    }
  }

  @Transactional
  fun saveProcessed(message: String?, calculated: Double) {
    val newEntity = TestEntity()
    newEntity.message = message
    newEntity.calculated = calculated
    testRepository.save(newEntity).apply { log.info("Saved: ${mapper.writeValueAsString(this)}") }
  }
}