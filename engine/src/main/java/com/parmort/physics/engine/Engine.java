package com.parmort.physics.engine;

import processing.core.PApplet;

public class Engine {
  private float width, height;

  private PApplet app;

  private float g;
  private int fps;
  private float air_density;
  public boolean drag;

  protected Ground gnd;

  public Engine(PApplet app,
      float width,
      float height,
      float g,
      float air_density,
      int fps) {
    this.app = app;
    this.width = width;
    this.height = height;
    this.g = g;
    this.air_density = air_density;
    this.fps = fps;
    this.gnd = new Ground(this);
  }

  public float getWidth() {
    return this.width;
  }

  public float getHeight() {
    return this.height;
  }

  public float getG() {
    return this.g;
  }

  public float getFPS() {
    return this.fps;
  }

  public Ground getGnd() {
    return this.gnd;
  }

  public void setGnd(Ground gnd) {
    this.gnd = gnd;
  }

  public PApplet getApp() {
    return this.app;
  }

  public float getAirDensity() {
    if (!this.drag) return 0;
    return this.air_density;
  }
}
