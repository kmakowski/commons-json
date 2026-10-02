# commons-json

Jackson `ObjectMapper` helpers for Kotlin. Dates are written as ISO-8601 strings, and unknown properties are ignored on read.

```xml
<dependency>
    <groupId>io.github.kmakowski</groupId>
    <artifactId>commons-json</artifactId>
    <version>1.0.0</version>
</dependency>
```

```kotlin
import io.github.kmakowski.json.Json

val person = Json.deserialize<Person>("""{"name":"John","age":30}""")
val json = Json.serialize(person)
```

Publish a release to Maven Central with a GPG key and a [Central Portal](https://central.sonatype.com) user token in `~/.m2/settings.xml` under server id `central`:

```bash
mvn deploy -Pcentral
```
