package db.demo.front.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import springfox.documentation.builders.ApiInfoBuilder
import springfox.documentation.builders.PathSelectors
import springfox.documentation.service.ApiInfo
import springfox.documentation.spi.DocumentationType
import springfox.documentation.spring.web.plugins.Docket
import springfox.documentation.swagger2.annotations.EnableSwagger2

@Configuration
@EnableSwagger2
class SwaggerConfig {

  @Bean
  fun questionnaires(): Docket {
    return Docket(DocumentationType.SWAGGER_2)
      .groupName("db-demo-front")
      .apiInfo(demoInfo())
      .select()
      .paths(PathSelectors.regex("/api/v1/.*"))
      .build()
  }

  private fun demoInfo(): ApiInfo = ApiInfoBuilder()
    .title("DB Demo with Swagger")
    .description("DB Demo with Swagger")
    .version("1.0")
    .build()
}