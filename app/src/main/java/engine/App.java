package engine;

import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {
  public float g = 9.8f;
  public float air_density = 1f;

  public float fps = 120;

  public Square square;
  public Square square2;
  public Ground gnd;

  private int LEFT_ARROW = 37;
  private int RIGHT_ARROW = 39;

  private boolean pressed_left = false;
  private boolean pressed_right = false;
  private float appliedForce = 30;

  public static void main(String[] args) {
    String[] appletArgs = new String[] { "engine.App" };
    PApplet.main(appletArgs);
  }

  public void settings() {
    size(1900, 500);
    this.gnd = new Ground(this, 100, 0.5f, 0.4f);
    this.square = new Square(this, 10, 0, 50);
    this.square2 = new Square(this, width / 2, 0, 100);
  }

  public void setup() {
    frameRate(this.fps);
  }

  public void draw(){
    background(64);

    this.square.updateForces();
    this.square2.updateForces();

    checkCollisions();

    this.square.updateKinematics();
    this.square2.updateKinematics();

    drawGround();
    drawSquare();
    drawSquare2();

    this.square.clearForces();
    this.square2.clearForces();
  }

  public void keyPressed() {
    if (keyCode == RIGHT_ARROW && !this.pressed_right) {
      this.square.applyForce(new PVector(this.appliedForce, 0));
      this.pressed_right = true;
    } else if (keyCode == LEFT_ARROW && !this.pressed_left) {
      this.square.applyForce(new PVector(-this.appliedForce, 0));
      this.pressed_left = true;
    }
  }

  public void keyReleased() {
    if (keyCode == RIGHT_ARROW && this.pressed_right) {
      this.square.removeForce(new PVector(this.appliedForce, 0));
      this.pressed_right = false;
    } else if (keyCode == LEFT_ARROW && this.pressed_left) {
      this.square.removeForce(new PVector(-this.appliedForce, 0));
      this.pressed_left = false;
    }
  }

  private boolean collided = false;

  private void checkCollisions() {
    if (this.square.collidesWith(this.square2)) {
      PVector p = PVector.add(this.square.momentum(), this.square2.momentum());
      float u = this.square.kinetic() + this.square2.kinetic();

      float A = (sq(this.square2.m) / (2 * this.square.m)) + (this.square2.m / 2);
      float B = -1 * this.square2.m * p.x / this.square.m;
      float C = (sq(p.x) / (2 * this.square.m)) - u;

      float v2 = ((-1 * B) + sqrt(sq(B) - (4 * A * C))) / (2 * A);
      float v1 = (p.x / this.square.m) - (this.square2.m * v2 / this.square.m);

      this.square.setV(new PVector(v1, 0));
      this.square2.setV(new PVector(v2, 0));

      if (Math.abs(this.square.right() - this.square2.left()) <= Math.abs(this.square.left() - this.square2.right())) {
        this.square.setX(this.square2.left() - this.square.s);
      } else {
        this.square.setX(this.square2.right());
      }

      this.collided = !this.collided;
    }
  }

  private void drawGround() {
    fill(255, 255, 255);
    this.gnd.render();
    noFill();
  }

  private void drawSquare() {
    fill(255, 0, 0);
    this.square.render();
    noFill();
  }

  private void drawSquare2() {
    fill(0, 255, 0);
    this.square2.render();
    noFill();
  }
}
