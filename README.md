# A Physics Engine

A school project. This is a simulation with a cube.

## Installing

To run it on a unix-based operating system, run:

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

The left and right arrow keys apply forces on the red square. The spacebar
toggles friction and air resistance.

## Physics Principles

1. Elastic collisions conserve momentum and energy
2. Air resistance eventually cancels out the other forces acting on an object,
   resulting in terminal velocity
3. Static friction force is equal and opposite to the sum of the other forces in
   the plane of the surface
4. Normal force is equal and opposite to the net force exerted on the ground
5. With a constant force, acceleration is constant, and velocity continuously
   increases

## Goals

- [X] Don't worry about heat
- [X] Make idealistic assumptions
- [ ] Update to a up-to-date version of Java (for LSP purposes)
  - [ ] Decouple from Processing
  - [ ] Move to a different graphics package
- [ ] Rotational Motion
- [ ] Simple Harmonic Motion
