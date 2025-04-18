public abstract class Pet {
    private String name;
    private int age;
    private String species;
    private String breed;
    private String ownerName;
    private String color;
    private String careMessage; // careMessage to store care info

    public Pet(String name, int age, String species, String breed, String ownerName, String color) {
        this.name = name;
        this.age = age;
        this.species = species;
        this.breed = breed;
        this.ownerName = ownerName;
        this.color = color;
        this.careMessage = "-";
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getSpecies() {
        return species;
    }

    public String getBreed() {
        return breed;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public String getColor() {
        return color;
    }

    public String getCareMessage() {
        return careMessage;
    }

    public void setCareMessage(String careMessage) {
        this.careMessage = careMessage;
    }

    // Abstract method for displaying care message.
    // Polymorphism allows each subclass (Dog or Cat) to implement its own version of displayCareMessage(),
    public abstract void displayCareMessage();

    public String toString() {
        return name + " (" + species + ") - Age: " + age + ", Breed: " + breed + ", Owner: " + ownerName + ", Color: " + color;
    }
}
