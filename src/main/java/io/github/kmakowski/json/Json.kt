package io.github.kmakowski.json

import com.fasterxml.jackson.databind.DeserializationFeature
import com.fasterxml.jackson.databind.SerializationFeature
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule
import com.fasterxml.jackson.module.kotlin.jacksonObjectMapper
import com.fasterxml.jackson.module.kotlin.readValue
import org.slf4j.LoggerFactory

object Json {
    private val log = LoggerFactory.getLogger(Json::class.java)

    val mapper = jacksonObjectMapper()
        .registerModule(JavaTimeModule())
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        .configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false)!!

    fun serialize(any: Any): String = mapper.writeValueAsString(any)
    fun toPrettyJson(any: Any): String =
        mapper.writerWithDefaultPrettyPrinter().writeValueAsString(any)

    inline fun <reified T> deserialize(input: String): T = mapper.readValue(input)
    fun <T> deserialize(input: String, clazz: Class<T>): T {
        try {
            return mapper.readValue(input, clazz)
        } catch (e: Exception) {
            log.error("Could not deserialize input $input into $clazz")
            throw IllegalArgumentException(e)
        }
    }
}
