package engine;

import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {
  public float g = 9.8f;
  public float air_density = 1.225f;

  public float fps = 120;

  public Square square;
  public Ground gnd;

  private int LEFT_ARROW = 37;
  private int RIGHT_ARROW = 39;

  private boolean pressed_left = false;
  private boolean pressed_right = false;
  private float appliedForce = 100;

  public void settings() {
    size(1000, 500);
    this.gnd = new Ground(this, 100, 0.5f, 0.4f);
    this.square = new Square(this, 10, 0, 50);
  }

  public void setup() {
    frameRate(this.fps);
  }

  public void draw(){
    background(64);
    drawGround();
    drawSquare();
    this.square.update();
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

  public void drawGround() {
    fill(255, 255, 255);
    this.gnd.render();
    noFill();
  }

  public void drawSquare() {
    fill(255, 0, 0);
    this.square.render();
    noFill();
  }

  public static void main(String[] args) {
    String[] appletArgs = new String[] { "engine.App" };
    PApplet.main(appletArgs);
  }
}
