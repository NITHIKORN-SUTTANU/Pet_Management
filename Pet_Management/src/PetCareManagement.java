import java.util.Scanner;

public class PetCareManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PetManager manager = new PetManager();
        manager.loadPetsFromFile();
        boolean running = true;

        while (running) {
            System.out.println("\nPet Care Management System");
            System.out.println("1. Add Pet");
            System.out.println("2. View All Pets");
            System.out.println("3. Search Pet");
            System.out.println("4. Care for Pet");
            System.out.println("5. Delete Pet");
            System.out.println("6. Quit");
            System.out.print("Choose an option: ");

            int option = 0;
            try {
                option = Integer.parseInt(sc.nextLine()); // Read input as String, then convert
            } catch (NumberFormatException e) {
                System.out.println("Invalid input! Please enter a number (1-6).");
                continue; // Restart loop
            }

            switch (option) {
                case 1:
                    System.out.println("------------------------------------------------------------");
                    System.out.print("Enter Pet Name: ");
                    String name = sc.nextLine();
                    System.out.print("Enter Age: ");
                    int age = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Enter Breed: ");
                    String breed = sc.nextLine();
                    System.out.print("Enter Owner Name: ");
                    String owner = sc.nextLine();
                    System.out.print("Is it a Dog or a Cat? ");
                    String type = sc.nextLine();

                    if (type.equalsIgnoreCase("Dog")) {
                        System.out.print("Enter Dog's Color: ");
                        String color = sc.nextLine();
                        manager.addPet(new Dog(name, age, breed, owner, color));
                    } else if (type.equalsIgnoreCase("Cat")) {
                        System.out.print("Enter Cat's Color: ");
                        String color = sc.nextLine();
                        manager.addPet(new Cat(name, age, breed, owner, color));
                    }
                    System.out.println("------------------------------------------------------------");
                    System.out.println(name + " is added.");
                    break;
                case 2:
                    System.out.println("------------------------------------------------------------");
                    manager.displayPets();
                    System.out.println("------------------------------------------------------------");
                    break;
                case 3:
                    System.out.println("------------------------------------------------------------");
                    System.out.print("Enter the name of the pet to search: ");
                    String petNameToSearch = sc.nextLine();
                    Pet petFound = manager.searchPet(petNameToSearch);
                    if (petFound != null) {
                        System.out.println("Pet found: " + petFound);
                        petFound.displayCareMessage();
                    } else {
                        System.out.println("Pet with the name " + petNameToSearch + " not found.");
                    }
                    System.out.println("------------------------------------------------------------");
                    break;
                case 4:
                    System.out.println("------------------------------------------------------------");
                    System.out.print("Enter the name of the pet to care for: ");
                    String petNameToCare = sc.nextLine();
                    Pet petToCare = manager.searchPet(petNameToCare);
                    if (petToCare != null) {
                        System.out.print("Enter a personalized care message for " + petNameToCare + ": ");
                        String careMessage = sc.nextLine();
                        petToCare.setCareMessage(careMessage);
                        System.out.println("Care message set for " + petNameToCare + ": " + careMessage);
                    } else {
                        System.out.println("Pet not found.");
                    }
                    System.out.println("------------------------------------------------------------");
                    break;
                case 5:
                    System.out.println("------------------------------------------------------------");
                    System.out.print("Enter the name of the pet to delete: ");
                    String petNameToDelete = sc.nextLine();
                    manager.deletePet(petNameToDelete);
                    System.out.println("------------------------------------------------------------");
                    break;
                case 6:
                    running = false;
                    System.out.println("Exit the system.");
                    break;
                default:
                    System.out.println("Invalid input! Please enter a number (1-6).");
            }
        }

        sc.close();
    }
}
