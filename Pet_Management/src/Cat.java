public class Cat extends Pet {
    public Cat(String name, int age, String breed, String ownerName, String color) {
        super(name, age, "Cat", breed, ownerName, color); //use "super" to call superclass(Pet) constructor
    }

    // This is method overriding – an example of polymorphism where each pet type provides a specific care message.
    @Override
    public void displayCareMessage() {
        System.out.println("Cat Care: " + getCareMessage());
    }
}
