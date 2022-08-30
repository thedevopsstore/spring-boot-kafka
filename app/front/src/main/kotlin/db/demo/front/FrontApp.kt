package db.demo.front

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.context.annotation.Bean
import org.springframework.scheduling.annotation.EnableAsync
import java.util.concurrent.ExecutorService
import java.util.concurrent.Executors

@EnableAsync
@SpringBootApplication
class FrontApp {

  @Bean("eventExecutor")
  fun auditExecutor(): ExecutorService {
    return Executors.newFixedThreadPool(4)
  }

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<FrontApp>(*args)
    }
  }
}