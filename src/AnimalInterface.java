//Interfaces Definition
interface Walkable {
    void walk();
}

interface Swimmable {
    void swim();
}

interface EggLaying {
    void layEggs();
}

interface GiveBirth {
    void giveBirth();
}

//Animal Hierarchy
abstract class Animal {
    private String name;
    private int age;

    public Animal(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public abstract void displayInfo();
}

class Mammal extends Animal {
    public Mammal(String name, int age) {
        super(name, age);
    }

    @Override
    public void displayInfo() {
        System.out.printf("Mammal: Name=%s, Age=%d%n", getName(), getAge());
    }
}

class Fish extends Animal {
    public Fish(String name, int age) {
        super(name, age);
    }

    @Override
    public void displayInfo() {
        System.out.printf("Fish: Name=%s, Age=%d%n", getName(), getAge());
    }
}

// Specific Animal Classes Implementing Interfaces
class Tiger extends Mammal implements Walkable {
    public Tiger(String name, int age) {
        super(name, age);
    }

    @Override
    public void walk() {
        System.out.println(getName() + " the tiger is walking.");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        walk();
    }
}

class ClownFish extends Fish implements Swimmable {
    public ClownFish(String name, int age) {
        super(name, age);
    }

    @Override
    public void swim() {
        System.out.println(getName() + " the clownfish is swimming.");
    }

    @Override
    public void displayInfo() {
        super.displayInfo();
        swim();
    }
}

class FrogSwim implements Swimmable {
    @Override
    public void swim() {
        System.out.println("Swimming frog");
    }
}

class Dolphin extends Animal implements GiveBirth, Swimmable {
    public Dolphin(String name, int age) {
        super(name, age);
    }

    @Override
    public void swim() {
        System.out.println(getName() + " the dolphin is swimming.");
    }

    @Override
    public void giveBirth() {
        System.out.println(getName() + " the dolphin is giving birth.");
    }

    @Override
    public void displayInfo() {
        System.out.printf("Dolphin: Name=%s, Age=%d%n", getName(), getAge());
        swim();
        giveBirth();
    }
}

// Testing the Implementation

public class AnimalInterface {
    public static void main(String[] args) {
        Tiger tiger = new Tiger("Tiger", 5);
        tiger.displayInfo();

        ClownFish clownFish = new ClownFish("ClownFish", 1);
        clownFish.displayInfo();

        Dolphin dolphin = new Dolphin("Dolphin", 3);
        dolphin.displayInfo();

        FrogSwim frog = new FrogSwim();
        frog.swim();
    }
}