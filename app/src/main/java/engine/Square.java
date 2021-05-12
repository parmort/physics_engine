package engine;

import processing.core.PVector;
import java.lang.Math;

public class Square {
  private App app;
  private int s;
  private float m;

  private PVector p;
  private PVector v;
  private PVector a;
  private PVector f_net;
  private PVector normalForce;
  private PVector friction;

  public Square(App app, float x, float y, int s) {
    this.app = app;
    this.s = s;
    this.m = 1f;

    this.p = new PVector(x, y);
    this.v = new PVector(0, 0);
    this.a = new PVector(0, 0);

    this.f_net = new PVector(0, 0);
    this.normalForce = new PVector(0, 0);
    this.friction = new PVector(0, 0);

    applyForce(new PVector(0, this.m * this.app.g));
  }

  public void update() {
    this.calculateNormalForce();
    this.applyForce(this.normalForce);

    this.calculateFriction();
    this.applyForce(this.friction);

    this.a.set(PVector.div(this.f_net, this.m));

    PVector delta_v = PVector.div(this.a, this.app.fps);
    this.v.add(delta_v);

    PVector delta_p = PVector.div(this.v, this.app.fps);
    this.p.add(v);

    this.removeForce(this.friction);
    this.removeForce(this.normalForce);
  }

  public void render() {
    app.square(this.p.x, this.p.y, this.s);
  }

  public void applyForce(PVector force) {
    this.f_net.add(force);
  }

  public void removeForce(PVector force) {
    this.f_net.sub(force);
  }

  public float top() {
    return this.p.y;
  }

  public float bottom() {
    return this.p.y + this.s;
  }

  public float left() {
    return this.p.x;
  }

  public float right() {
    return this.p.x + this.s;
  }

  private void calculateNormalForce() {
    if (collidesWithGround()) {
      this.normalForce.set(0, -this.f_net.y);
      this.v.add(new PVector(0, -this.v.y));
      this.p.set(new PVector(this.p.x, this.app.gnd.height() - this.s));
    } else {
      this.normalForce.set(0, 0);
    }
  }

  private void calculateFriction() {
    if (collidesWithGround()) {
      if (Math.abs(this.v.x) >= 0.025) {
        this.friction.set(oppose(this.v.x) * this.maxFrictionForce('k'), 0);
      } else if (Math.abs(this.f_net.x) > this.maxFrictionForce('s')) {
        this.friction.set(oppose(this.f_net.x) * this.maxFrictionForce('k'), 0);
      } else {
        // Not moving and static friction is not broken
        this.friction.set(-this.f_net.x, 0);
        this.v.x = 0;
      }
    } else {
      this.friction.set(0, 0);
    }
  }

  private float maxFrictionForce(char type) {
    return this.app.gnd.mu(type) * this.normalForce.mag();
  }

  private boolean collidesWithGround() {
    return this.bottom() >= this.app.gnd.height();
  }

  private int oppose(float value) {
    if (value > 0)
      return -1;
    else if (value < 0)
      return 1;
    else
      return 0;
  }
}
