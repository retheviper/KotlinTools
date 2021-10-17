# Kotlin Tools

## TL;DR

Collections of simple functions, just like some shortcuts.

## To use (at Gradle)

1. Build jar.

```shell
./gradlew jar
```

2. Copy jar from `build/libs` into project folder. (ex: `libs`)

3. Import jar in `build.gradle.kts`.

```kotlin
dependencies {
    implementation(files("libs/kotlintools.jar"))
}
```

4. Enjoy!