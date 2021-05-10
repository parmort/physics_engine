package engine;

import processing.core.PVector;

public class Square {
  private App app;
  private int s;
  private float m;

  private PVector p;
  private PVector v;
  public PVector a;
  private PVector F_net;

  public Square(App app, float x, float y, int s) {
    this.app = app;
    this.s = s;
    this.m = 1f;

    this.p = new PVector(x, y);
    this.v = new PVector(0, 0);
    this.a = new PVector(0, 0);
    this.F_net = new PVector(0, 0);

    apply_force(new PVector(0, this.m * this.app.g));
  }

  public void step() {
    this.a.set(PVector.div(this.F_net, this.m));

    PVector delta_v = PVector.div(a, 60);
    this.v.add(delta_v);

    PVector delta_p = PVector.div(v, 60);
    this.p.add(v);
  }

  public void render() {
    if (this.p.y + this.s > this.app.ground_height) {
      this.p.y = this.app.ground_height - this.s;
    }

    app.square(this.p.x, this.p.y, this.s);
  }

  public void apply_force(PVector force) {
    this.F_net.add(force);
  }

  public void remove_force(PVector force) {
    this.F_net.sub(force);
  }
}
