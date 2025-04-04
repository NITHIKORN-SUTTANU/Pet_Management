import java.io.*;
import java.util.*;

class PetManager {
    private List<Pet> pets = new ArrayList<>();
    private static final String FILE_NAME = "pets.txt";

    public void addPet(Pet pet) {
        pets.add(pet);
        savePetsToFile();
    }

    public void displayPets() {
        if (pets.isEmpty()) {
            System.out.println("No pets available.");
        } else {
            for (Pet pet : pets) {
                System.out.println(pet);
                pet.displayCareMessage(); // Display care message for each pet
            }
        }
    }

    public void loadPetsFromFile() {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details[2].equals("Dog")) {
                    pets.add(new Dog(details[0], Integer.parseInt(details[1]), details[3], details[4], details[5]));
                } else if (details[2].equals("Cat")) {
                    pets.add(new Cat(details[0], Integer.parseInt(details[1]), details[3], details[4], details[5]));
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading pets data.");
        }
    }

    public void savePetsToFile() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pet pet : pets) {
                if (pet instanceof Dog) {
                    Dog dog = (Dog) pet;
                    bw.write(dog.getName() + "," + dog.getAge() + "," + dog.getSpecies() + "," + dog.getBreed() + ","
                            + dog.getOwnerName() + "," + dog.getColor());
                } else if (pet instanceof Cat) {
                    Cat cat = (Cat) pet;
                    bw.write(cat.getName() + "," + cat.getAge() + "," + cat.getSpecies() + "," + cat.getBreed() + ","
                            + cat.getOwnerName() + "," + cat.getColor());
                }
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("Error saving pets data.");
        }
    }

    public void deletePet(String petName) {
        Pet petToDelete = null;
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(petName)) {
                petToDelete = pet;
                break;
            }
        }

        if (petToDelete != null) {
            pets.remove(petToDelete);
            savePetsToFile();
            System.out.println("Pet " + petName + " has been deleted.");
        } else {
            System.out.println("Pet with the name " + petName + " is not found.");
        }
    }

    public Pet searchPet(String petName) {
        for (Pet pet : pets) {
            if (pet.getName().equalsIgnoreCase(petName)) {
                return pet;
            }
        }
        return null;
    }
}
