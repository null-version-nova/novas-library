Coming soon to a Modrinth near you!
# Using as a dependency
The repository this mod is stored in can be declared as follows,
```
repositories {
    maven {
        name = "Nova's Library"
        setUrl("https://null-version-nova.github.io/novas-library/")
    }
}
```
Then it can be declared like so,

```
modApi("nullversionnova:novaslibrary-[MOD LOADER]-[MINECRAFT VERSION]}:[NOVAS LIBRARY VERSION]") { isTransitive = false }
```

For example, if you were to use the Fabric version of this mod for 1.20.1 and were targeting version 1.0.0 of the mod, you would type `modApi("nullversionnova:novaslibrary-fabric-1.20.1}:1.0.0") { isTransitive = false }`

The transitivity setting does not seem to be required for the Fabric version, yet I've had issues without it in the Forge development environment.
If you're using Architectury Gradle, which this mod was made for, you can also omit the loader to target the common version.

```
modApi("nullversionnova:novaslibrary-[MINECRAFT VERSION]}:[NOVAS LIBRARY VERSION]") { isTransitive = false }
```

Like so.
