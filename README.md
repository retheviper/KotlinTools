# Kotlin Tools

## TL;DR

Simple extension functions.

## To use (at Gradle)

1. Build jar.

```shell
./gradlew jar
```

2. Copy jar from `build/libs` into project folder. (ex: `libs`)

3. Import jar in `build.gradle.kts`.

```kotlin
dependencies {
    implementation(files("libs/kotlintools-1.0-SNAPSHOT.jar"))
}
```

4. Enjoy!