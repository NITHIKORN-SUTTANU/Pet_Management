public class Cat extends Pet {
    public Cat(String name, int age, String breed, String ownerName, String color) {
        super(name, age, "Cat", breed, ownerName, color);
    }

    @Override
    public void displayCareMessage() {
        System.out.println("Cat Care: " + getCareMessage());
    }
}
