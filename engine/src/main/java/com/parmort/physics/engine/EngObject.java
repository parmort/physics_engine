package com.parmort.physics.engine;

import processing.core.PVector;
import processing.core.PConstants;

public abstract class EngObject {
  protected Engine eng;
  public float m;

  public float width;
  protected float height;

  protected float drag_coeff;

  protected PVector p;
  protected PVector v;
  protected PVector a;

  protected PVector f_net;
  protected PVector normalForce;
  protected PVector friction;
  protected PVector drag;

  EngObject(Engine eng, float density, float drag_coeff, PVector pos, float width, float height) {
    this.eng = eng;
    this.m = density * width * height;
    this.drag_coeff = drag_coeff;

    this.width = width;
    this.height = height;

    this.p = pos;
    this.v = new PVector(0, 0);
    this.a = new PVector(0, 0);

    this.f_net = new PVector(0, 0);
    this.normalForce = new PVector(0, 0);
    this.friction = new PVector(0, 0);
    this.drag = new PVector(0, 0);

    applyForce(new PVector(0, this.m * this.eng.getG()));
  }

  EngObject(Engine eng, float density, float drag_coeff, PVector pos, float s) {
    this(eng, density, drag_coeff, pos, s, s);
  }

  public abstract void render(float red, float green, float blue);

  public void updateForces() {
    calculateDragForce();
    applyForce(this.drag);

    calculateNormalForce();
    applyForce(this.normalForce);

    calculateFriction();
    applyForce(this.friction);
  }

  public void updateKinematics() {
    this.a.set(PVector.div(this.f_net, this.m));

    PVector delta_v = PVector.div(this.a, this.eng.getFPS());
    this.v.add(delta_v);

    PVector delta_p = PVector.div(this.v, this.eng.getFPS());
    this.p.add(v);

    if (right() >= this.eng.getWidth()) {
      this.p.x = this.eng.getWidth() - this.width;
      this.v.mult(-1);
    }

    if (left() <= 0) {
      this.p.x = 0;
      this.v.mult(-1);
    }
  }

  public void clearForces() {
    removeForce(this.friction);
    removeForce(this.normalForce);
    removeForce(this.drag);
  }

  public void applyForce(PVector force) {
    this.f_net.add(force);
  }

  public void removeForce(PVector force) {
    this.f_net.sub(force);
  }

  public PVector momentum() {
    return PVector.mult(this.v, this.m);
  }

  public float kinetic() {
    return this.m * this.v.magSq() / 2;
  }

  public void setV(PVector v) {
    this.v.set(v);
  }

  public void setX(float x) {
    this.p.x = x;
  }

  public boolean collidesWith(Square obj) {
    if (this.right() > obj.left() && this.right() < obj.right()) return true;
    if (this.left() < obj.right() && this.left() > obj.left()) return true;
    return false;
  }

  private void calculateDragForce() {
    float force = 0.5f * this.drag_coeff * this.eng.getAirDensity() * this.height * Util.sq(this.v.mag());
    PVector.fromAngle(oppose(this.v), this.drag);
    this.drag.setMag(force);
  }

  private void calculateNormalForce() {
    if (collidesWithGround()) {
      this.normalForce.set(0, -this.f_net.y);
      this.v.add(new PVector(0, -this.v.y));
      this.p.y = this.eng.getGnd().height() - this.height;
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
    return this.eng.getGnd().mu(type) * this.normalForce.mag();
  }

  private boolean collidesWithGround() {
    return this.bottom() >= this.eng.getGnd().height();
  }

  public float top() {
    return this.p.y;
  }

  public float bottom() {
    return this.p.y + this.height;
  }

  public float left() {
    return this.p.x;
  }

  public float right() {
    return this.p.x + this.width;
  }

  private float oppose(float value) {
    if (value > 0)
      return -1;
    else if (value < 0)
      return 1;
    else
      return 0;
  }

  private float oppose(PVector vector) {
    return vector.heading() + PConstants.PI;
  }
}
