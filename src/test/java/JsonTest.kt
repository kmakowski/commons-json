
import io.github.kmakowski.json.Json
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import java.time.Instant

class JsonTest {
    data class Person(val name: String, val age: Int)
    
    @Test
    fun testJsonParsing() {
        val jsonString = """{"name": "John", "age": 30}"""
        val jsonObject = Json.deserialize<Person>(jsonString)

        assertThat(jsonObject).isEqualTo(Person("John", 30))
    }

    @Test
    fun testJsonSerialization() {
        val person = mapOf("name" to "Jane", "age" to 25, "date" to Instant.ofEpochMilli(0))
        val jsonString = Json.serialize(person)

        assertThat("""{"name":"Jane","age":25,"date":"1970-01-01T00:00:00Z"}""").isEqualTo(jsonString)
    }
}
