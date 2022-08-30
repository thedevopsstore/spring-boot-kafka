package db.demo.reader

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication


@SpringBootApplication
class ReaderApp {

  companion object {
    @JvmStatic
    fun main(args: Array<String>) {
      runApplication<ReaderApp>(*args)
    }
  }
}