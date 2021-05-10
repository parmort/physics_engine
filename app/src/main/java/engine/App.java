package engine;

import processing.core.PApplet;
import processing.core.PVector;

public class App extends PApplet {
  public int ground_height;
  public float g = 9.8f;

  private Square square;
  private int fps = 60;

  private int LEFT_ARROW = 37;
  private int RIGHT_ARROW = 39;

  public void settings() {
    size(500, 500);
    this.square = new Square(this, 0, 0, 50);
    this.ground_height = height - 100;
  }

  public void draw(){
    background(64);
    drawGround();
    drawSquare();
  }

  public void keyPressed() {
    if (keyCode == RIGHT_ARROW) {
      this.square.apply_force(new PVector(1, 0));
    } else if (keyCode == LEFT_ARROW) {
      this.square.apply_force(new PVector(-1, 0));
    }
  }

  public void keyReleased() {
    if (keyCode == RIGHT_ARROW) {
      this.square.remove_force(new PVector(1, 0));
    } else if (keyCode == LEFT_ARROW) {
      this.square.remove_force(new PVector(-1, 0));
    }
  }

  public void drawGround() {
    fill(255, 255, 255);
    rect(0, ground_height, width, height);
    noFill();
  }

  public void drawSquare() {
    fill(255, 0, 0);
    this.square.render();
    noFill();
    this.square.step();
  }

  public static void main(String[] args) {
    String[] appletArgs = new String[] { "engine.App" };
    PApplet.main(appletArgs);
  }
}
