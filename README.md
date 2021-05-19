# physics_engine

A school project. This is a simulation with a cube. To run it on a unix-based
operating system, run:

```
./gradlew run
```

On Windows, run:

```
./gradlew.bat run
```

This requires having the [Processing](processing.org) Library installed and the
path to the jar files updated in `app/build.gradle` in the repositories section.

To run a distribution, download a zip file from the releases section. Unzip it,
and run the `bin/App` (or `bin/App.bat` on Windows) script.

## Controlling the simulation

Left and right arrow keys apply forces on the red square. Spacebar toggles
friction and air resistance.
