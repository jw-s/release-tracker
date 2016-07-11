package com.github.joelws.release.tracker.configuration

import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import com.github.joelws.release.tracker.handler.ReleaseTrackerExceptionHandler
import com.github.joelws.release.tracker.handler.RuntimeExceptionHandler
import org.apache.cxf.endpoint.Server
import org.apache.cxf.feature.LoggingFeature
import org.apache.cxf.jaxrs.JAXRSServerFactoryBean
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import

@Configuration
@Import(EndpointConfig::class)
open class AppConfig {

    @Autowired
    lateinit var endpointConfig: EndpointConfig

    @Bean
    open fun jsonProvider() = JacksonJsonProvider(objectMapper())

    @Bean
    open fun objectMapper() = ObjectMapper().registerKotlinModule()


    @Value("\${rest.service.url}")
    lateinit var serverAddress: String

    @Bean
    open fun releaseTrackerServer(): Server {
        val factory = JAXRSServerFactoryBean()
        factory.address = serverAddress
        factory.providers = listOf(jsonProvider(), RuntimeExceptionHandler(), ReleaseTrackerExceptionHandler())
        factory.setServiceBeans(listOf(endpointConfig.createArtifactEndpoint(),
                endpointConfig.readArtifactEndpoint(),
                endpointConfig.updateArtifactEndpoint(),
                endpointConfig.deleteArtifactEndpoint(),
                endpointConfig.createReleaseEndpoint(),
                endpointConfig.readReleaseEndpoint(),
                endpointConfig.updateReleaseEndpoint(),
                endpointConfig.deleteReleaseEndpoint(),
                endpointConfig.listReleaseEndpoint()))
        factory.features = listOf(LoggingFeature())
        return factory.create()
    }


}