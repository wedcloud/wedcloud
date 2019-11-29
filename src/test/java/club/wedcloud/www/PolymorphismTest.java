package club.wedcloud.www;

public class PolymorphismTest {

  public static void main(String[] args) {
    Shape c = new Circle();
    doStuff(c);
  }

  public static void doStuff(Shape s) {
    s.erase();
    s.draw();
  }
}


class Shape {
  public void draw() {
    System.out.println("Shape draw");
  }

  public void erase() {
    System.out.println("Shape erase");
  }
}


class Circle extends Shape {
  public void draw() {
    System.out.println("Circle draw");
  }

  public void erase() {
    System.out.println("Circle erase");
  }
}
