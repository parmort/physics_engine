package com.parmort.physics.engine;

import processing.core.PVector;

public class Square extends EngObject {
  public Square(Engine eng, PVector pos, int s) {
    super(eng, 0.0004f, 0.2f, pos, s);
  }

  public void render(float red, float green, float blue) {
    this.eng.getApp().fill(red, green, blue);
    this.eng.getApp().square(this.p.x, this.p.y, this.width);
    this.eng.getApp().noFill();
  }
}
