import java.util.Scanner;

public class PetCareManagement {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in); // Keyboard input (Scanner) allows real-time user interaction
        PetManager manager = new PetManager();
        manager.loadPetsFromFile();
        boolean running = true;

        while (running) {
            System.out.println("\n\033[1;95mPet Care Management System\033[0m");
            System.out.println("1. Add Pet");
            System.out.println("2. View All Pets");
            System.out.println("3. Search Pet");
            System.out.println("4. Add Care Message for Pet");
            System.out.println("5. Delete Pet");
            System.out.println("6. Quit");
            System.out.print("\033[1mChoose an option:\033[0m ");

            int option = 0;
            try {   // use try-catch prevents the system from crashing due to invalid input
                option = Integer.parseInt(sc.nextLine()); // Read input as String, then convert it to int
            } catch (NumberFormatException e) {
                System.out.println();
                System.out.println("Invalid input! Please enter a number (1-6).");
                System.out.println("\033[32m------------------------------------------------------------\033[0m");
                continue; // Restart loop
            }

            switch (option) {
                case 1:
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    // Validate pet name (letters and spaces only)
                    String name = "";
                    boolean validName = false;
                    while (!validName) {
                        try {
                            System.out.print("Enter Pet Name: ");
                            name = sc.nextLine();
                            if (!name.matches("[a-zA-Z ]+")) {
                                throw new InvalidPetInputException("Pet name should only contain letters and spaces.");
                            }   // Custom exceptions to provide specific and readable error messages.
                            validName = true;
                        } catch (InvalidPetInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Validate age (must be a number)
                    int age = 0;
                    boolean validAge = false;
                    while (!validAge) {
                        try {
                            System.out.print("Enter Age: ");
                            age = Integer.parseInt(sc.nextLine());
                            if (age < 0) {
                                System.out.println("Invalid age! Please enter a valid number for age (integer).");
                            } else {
                                validAge = true;
                            }
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid age! Please enter a valid number for age (integer).");
                        }
                    }
                    
                    // Validate breed (letters and spaces only)
                    String breed = "";
                    boolean validBreed = false;
                    while (!validBreed) {
                        try {
                            System.out.print("Enter Breed: ");
                            breed = sc.nextLine();
                            if (!breed.matches("[a-zA-Z ]+")) {
                                throw new InvalidPetInputException("Breed should only contain letters and spaces.");
                            }
                            validBreed = true;
                        } catch (InvalidPetInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Validate owner name (letters and spaces only)
                    String owner = "";
                    boolean validOwner = false;
                    while (!validOwner) {
                        try {
                            System.out.print("Enter Owner Name: ");
                            owner = sc.nextLine();
                            if (!owner.matches("[a-zA-Z ]+")) {
                                throw new InvalidPetInputException("Owner name should only contain letters and spaces.");
                            }
                            validOwner = true;
                        } catch (InvalidPetInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                   // Validate pet type (Dog or Cat)
                    String type = "";
                    boolean validType = false;
                    while (!validType) {
                        try {
                            System.out.print("Is it a Dog or a Cat? ");
                            type = sc.nextLine();
                            if (!type.equalsIgnoreCase("Dog") && !type.equalsIgnoreCase("Cat")) {
                                throw new InvalidPetInputException("Type should be either 'Dog' or 'Cat'.");
                            }
                            validType = true;
                        } catch (InvalidPetInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Validate color (letters and spaces only)
                    String color = "";
                    boolean validColor = false;
                    while (!validColor) {
                        try {
                            System.out.print("Enter " + type + "'s Color: ");
                            color = sc.nextLine();
                            if (!color.matches("[a-zA-Z ]+")) {
                                throw new InvalidPetInputException("Color should only contain letters and spaces.");
                            }
                            validColor = true;
                        } catch (InvalidPetInputException e) {
                            System.out.println(e.getMessage());
                        }
                    }

                    // Add pet to manager
                    if (type.equalsIgnoreCase("Dog")) {
                        manager.addPet(new Dog(name, age, breed, owner, color));
                    } else if (type.equalsIgnoreCase("Cat")) {
                        manager.addPet(new Cat(name, age, breed, owner, color));
                    }
                    System.out.println();
                    System.out.println("\033[4m" + name + "\033[0m" + " has been added.");
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    break;
                case 2: // display pets
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    manager.displayPets();
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    break;
                case 3: // search pets
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    System.out.print("Enter the name of the pet to search: ");
                    String petNameToSearch = sc.nextLine();
                    Pet petFound = manager.searchPet(petNameToSearch);
                    if (petFound != null) {
                        System.out.println();
                        System.out.println("Pet found: " + petFound);
                        petFound.displayCareMessage();
                    } else {
                        System.out.println();
                        System.out.println("Pet with the name " + "\"" + petNameToSearch + "\"" + " is not found.");
                    }
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    break;
                case 4: // add care message
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    System.out.print("Enter the name of the pet to care for: ");
                    String petNameToCare = sc.nextLine();
                    Pet petToCare = manager.searchPet(petNameToCare);
                    if (petToCare != null) {
                        System.out.print("Enter a personalized care message for " + petNameToCare + ": ");
                        String careMessage = sc.nextLine();
                        petToCare.setCareMessage(careMessage);
                        manager.savePetsToFile();
                        System.out.println();
                        System.out.println("Care message set for " + "\033[4m" + petNameToCare + "\033[0m" + ": " + careMessage);
                    } else {
                        System.out.println();
                        System.out.println("Pet is not found.");
                    }
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    break;
                case 5: // delete pets
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    System.out.print("Enter the name of the pet to delete: ");
                    String petNameToDelete = sc.nextLine();
                    manager.deletePet(petNameToDelete);
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
                    break;
                case 6: // exit
                    running = false;
                    System.out.println("\033[31m-----Exit the system.-----\033[0m");
                    break;
                default:
                    System.out.println();
                    System.out.println("Invalid input! Please enter a number (1-6).");
                    System.out.println("\033[32m------------------------------------------------------------\033[0m");
            }
        }

        sc.close();
    }
}
