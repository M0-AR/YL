package Exercise_13._06Extra;

public class Exercise_13_06Extra {
	public static void main(String[] args) {
		Cow cow = new Cow();
		Dog dog = new Dog();
		System.out.println(cow.sound());
		System.out.println(cow.howToEat());
		System.out.println(dog.sound());
	}
}

abstract class Animal {
	public abstract String sound();
}

interface Edible {
	String howToEat();
}

class Cow extends Animal implements Edible {
	@Override
	public String sound() {
		return "Gaga";
	}

	@Override
	public String howToEat() {
		return "Roast";
	}
}

class Dog extends Animal {
	@Override
	public String sound() {
		return "Woof";
	}
}
