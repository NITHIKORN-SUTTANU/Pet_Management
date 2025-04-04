public class Dog extends Pet {
    public Dog(String name, int age, String breed, String ownerName, String color) {
        super(name, age, "Dog", breed, ownerName, color);
    }

    @Override
    public void displayCareMessage() {
        System.out.println("Dog Care: " + getCareMessage());
    }
}
