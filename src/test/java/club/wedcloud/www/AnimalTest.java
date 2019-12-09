package club.wedcloud.www;

class Animal {
}


class Dog extends Animal {
}


class Cat extends Animal {
}


public class AnimalTest {
  public static void main(String[] args) {
    Animal ai = new Dog();
    if (ai instanceof Dog) {
      Dog dog = (Dog) ai;
      System.out.println(dog);
    }
    if (ai instanceof Cat) {
      Cat cat = (Cat) ai;
      System.out.println(cat);
    }
  }
}
