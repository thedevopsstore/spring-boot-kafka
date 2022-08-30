package db.demo.back

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.kafka.annotation.EnableKafka
import org.springframework.scheduling.annotation.EnableAsync


@EnableKafka
@EnableAsync
@SpringBootApplication
class BackApp {

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<BackApp>(*args)
    }
  }
}