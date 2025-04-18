import java.io.*;
import java.util.*;

class PetManager {
    // Using collections like ArrayList makes it easy to store, manage, and iterate over dynamic data (multiple pets),
    // which is more flexible than using arrays.
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
                System.out.println();
            }
        }
    }

    public void loadPetsFromFile() {
        // Using file reading allows data persistence – pets added will be saved even after the program ends.
        // This is important in real-world apps where user data must be stored and retrieved later.
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] details = line.split(",");
                if (details[2].equals("Dog")) {
                    // Check if the file contains a care message (7th field)
                    Dog dog = new Dog(details[0], Integer.parseInt(details[1]), details[3], details[4], details[5]);
                    dog.setCareMessage(details.length > 6 ? details[6] : "");  // Set care message if it exists
                    pets.add(dog);
                } else if (details[2].equals("Cat")) {
                    // Check if the file contains a care message (7th field)
                    Cat cat = new Cat(details[0], Integer.parseInt(details[1]), details[3], details[4], details[5]);
                    cat.setCareMessage(details.length > 6 ? details[6] : "");  // Set care message if it exists
                    pets.add(cat);
                }
            }
        } catch (IOException e) {
            System.out.println("Error loading pets data.");
        }
    }

    public void savePetsToFile() {
        // Using file writing ensures that the latest data is saved whenever a pet is added, deleted, or updated.
        // This makes the system reliable and consistent between sessions.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Pet pet : pets) {
                if (pet instanceof Dog) {
                    Dog dog = (Dog) pet;
                    bw.write(dog.getName() + "," + dog.getAge() + "," + dog.getSpecies() + "," + dog.getBreed() + ","
                            + dog.getOwnerName() + "," + dog.getColor() + "," + dog.getCareMessage());  // Save care message
                } else if (pet instanceof Cat) {
                    Cat cat = (Cat) pet;
                    bw.write(cat.getName() + "," + cat.getAge() + "," + cat.getSpecies() + "," + cat.getBreed() + ","
                            + cat.getOwnerName() + "," + cat.getColor() + "," + cat.getCareMessage());  // Save care message
                }
                bw.newLine();  // Write a new line for each pet
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
            System.out.println();
            System.out.println("Pet named " + "\033[4m" + petName + "\033[0m" + " has been deleted.");
        } else {
            System.out.println();
            System.out.println("Pet with the name " + "\"" + petName + "\"" +" is not found.");
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
