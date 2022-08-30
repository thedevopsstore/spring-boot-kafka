package db.demo.front.config

import org.springframework.context.annotation.Configuration

@Configuration
class KafkaConfig {
//
//  @Bean
//  fun kafkaConfigs(): Map<String, Any> {
//    val props = HashMap<String, Any>()
//    props[ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG] = StringDeserializer::class.java
//    props[ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG] = KafkaJsonDeserializer::class.java
//    props[ConsumerConfig.ISOLATION_LEVEL_CONFIG] = "read_committed"
//    props[ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG] = false
//    props[ConsumerConfig.MAX_POLL_INTERVAL_MS_CONFIG] = 3600000
//    props[ConsumerConfig.AUTO_OFFSET_RESET_CONFIG] = "latest"
//    return props
//  }

}
//
//
//class KafkaJsonDeserializer<T : Event>(targetType: Class<*>) : JsonDeserializer<T>(targetType as Class<T>?, mapper) {
//  init {
//    addTrustedPackages("*")
//  }
//}
