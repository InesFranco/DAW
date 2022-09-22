package Battleship.demo

import Battleship.demo.filters.interceptor
import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication
import org.springframework.web.servlet.config.annotation.InterceptorRegistry
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer

@SpringBootApplication
class DemoApplication(
	private val exampleHandlerInterceptor: interceptor.ExampleHandlerInterceptor

) : WebMvcConfigurer {

	override fun addInterceptors(registry: InterceptorRegistry) {
		registry.addInterceptor(exampleHandlerInterceptor)
	}
}


fun main(args: Array<String>) {
	runApplication<DemoApplication>(*args)
}
