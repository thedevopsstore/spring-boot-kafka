package db.demo.front.kafka

import db.demo.logger
import db.demo.mapper
import db.demo.model.Command
import db.demo.model.Topics.testCommand
import org.springframework.kafka.core.KafkaTemplate
import org.springframework.stereotype.Service


@Service
class KafkaService(
  private val template: KafkaTemplate<String, Command>
) {

  private val log = logger()

  fun sendCommand(command: Command) {
    log.info("Sending: ${mapper.writeValueAsString(command)}")
    template.send(testCommand, command)
  }
}