package engine;

import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {
  public float g = 9.8f;

  private Square square;
  public Ground gnd;

  private int LEFT_ARROW = 37;
  private int RIGHT_ARROW = 39;

  public void settings() {
    size(1000, 500);
    this.square = new Square(this, 0, 0, 50);
    this.gnd = new Ground(this, 100, 1, 0.75f);
  }

  public void draw(){
    background(64);
    drawGround();
    drawSquare();
    this.square.update();
  }

  public void keyPressed() {
    if (keyCode == RIGHT_ARROW) {
      this.square.applyForce(new PVector(1, 0));
    } else if (keyCode == LEFT_ARROW) {
      this.square.applyForce(new PVector(-1, 0));
    }
  }

  public void keyReleased() {
    if (keyCode == RIGHT_ARROW) {
      this.square.removeForce(new PVector(1, 0));
    } else if (keyCode == LEFT_ARROW) {
      this.square.removeForce(new PVector(-1, 0));
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
