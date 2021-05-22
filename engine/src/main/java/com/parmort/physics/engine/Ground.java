package com.parmort.physics.engine;

public class Ground {
  private Engine eng;
  private float height;
  private float mu_s;
  private float mu_k;
  private boolean friction = true;

  public Ground(Engine eng, float heightAboveBottom, float mu_s, float mu_k) {
    this.eng = eng;
    this.height = this.eng.getHeight() - heightAboveBottom;
    this.mu_s = mu_s;
    this.mu_k = mu_k;
  }

  public Ground(Engine eng, float heightAboveBottom) {
    this(eng, heightAboveBottom, 0, 0);
  }

  public Ground(Engine eng) {
    this(eng, 0, 0, 0);
  }

  public boolean toggleFriction() {
    this.friction = !this.friction;
    return this.friction;
  }

  public float height() {
    return this.height;
  }

  public void render() {
    this.eng.getApp().rect(0, this.height, this.eng.getWidth(), this.eng.getHeight());
  }

  public float mu(char type) {
    if (!this.friction) return 0;

    if (type == 's')
      return this.mu_s;
    else
      return this.mu_k;
  }
}
