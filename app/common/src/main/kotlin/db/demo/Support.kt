package db.demo

import com.fasterxml.jackson.datatype.jdk8.Jdk8Module
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import java.util.*
import kotlin.random.Random

inline fun <reified T> T.logger(): Logger {
  return LoggerFactory.getLogger(T::class.java)
}

val mapper = jacksonObjectMapper()
  .registerModule(Jdk8Module())
  .registerModule(JavaTimeModule())!!

val rnd = Random(Date().time)

const val loadFactor = 200000

inline fun <reified T> T.toJson(pretty: Boolean = true) =
  when {
    pretty -> mapper.writerWithDefaultPrettyPrinter().writeValueAsString(this)!!
    else -> mapper.writeValueAsString(this)!!
  }