# Voxy Account Cache

A client-side Fabric compatibility mod for Minecraft 26.3. It gives each Minecraft account a separate Voxy cache directory, so two clients can join the same server without trying to open the same RocksDB database.

## Why this exists

RocksDB requires an exclusive lock for a database. Voxy stores the server cache beneath the game directory and server address, so two clients using the same game directory can collide on the same `LOCK` file.

This mod changes Voxy's base cache path to include the authenticated Minecraft profile UUID:

```
.voxy/saves/<server>/accounts/<profile-uuid>/...
```

Each account rebuilds and maintains its own Voxy cache. The caches are intentionally not shared.

## Requirements

- Minecraft 26.3
- Fabric Loader 0.19.5 or newer
- Voxy installed
- Java 25

## Install

1. Build the mod with `gradle build` (or download the JAR from the repository's Actions build artifact).
2. Put `voxy-account-cache-1.0.0.jar` in the `mods` folder of **both** Minecraft instances.
3. Keep Voxy installed in both instances.
4. Start both clients and join the same server.

Do not manually delete RocksDB's `LOCK` file while a client is running.

## Build

Run:

```sh
gradle build
```

The remapped mod JAR is written to `build/libs/`.

## Compatibility

This mod targets Voxy 0.2.20-beta and Minecraft 26.3. It uses a Mixin against Voxy's private `getBasePath` method, so a Voxy update that changes that method may require an update to this mod.
