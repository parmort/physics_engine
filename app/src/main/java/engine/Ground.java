package engine;

public class Ground {
  private App app;
  private float height;
  private float mu_s;
  private float mu_k;

  public Ground(App app, float heightAboveBottom, float mu_s, float mu_k) {
    this.app = app;
    this.height = this.app.height - heightAboveBottom;
    this.mu_s = mu_s;
    this.mu_k = mu_k;
  }

  public Ground(App app, float heightAboveBottom) {
    this.app = app;
    this.height = this.app.height - heightAboveBottom;
    this.mu_s = 0;
    this.mu_k = 0;
  }

  public float height() {
    return this.height;
  }

  public void render() {
    app.rect(0, this.height, app.width, app.height);
  }

  public float mu(char type) {
    if (type == 's')
      return this.mu_s;
    else
      return this.mu_k;
  }
}
