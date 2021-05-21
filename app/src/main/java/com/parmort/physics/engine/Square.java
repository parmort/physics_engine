package com.parmort.physics.engine;

import processing.core.PVector;

public class Square extends EngObject {
  Square(App app, PVector pos, int s) {
    super(app, 0.0004f, 0.2f, pos, s);
  }

  public void render(float red, float green, float blue) {
    this.app.fill(red, green, blue);
    this.app.square(this.p.x, this.p.y, this.width);
    this.app.noFill();
  }
}
