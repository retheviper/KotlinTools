# Kotlin Tools

![kotlintools](./misc/kotlintools.jpg)

<a href="https://foojay.io/works-with-openjdk"><img align="right" src="https://github.com/foojayio/badges/raw/main/works_with_openjdk/Works-with-OpenJDK.png" width="100"></a>

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