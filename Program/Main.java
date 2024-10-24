import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        ArrayList<Animal> anml = new ArrayList<>();
        AnimalRegistry animalregistry = new AnimalRegistry();
        Counter counter = new Counter();

        boolean start = true;

        anml.add(new Cat("Murka", LocalDate.of(2021, 5, 14), "Cat", "Knows how to open doors"));
        anml.add(new Dog("Sherlock", LocalDate.of(2020, 11, 1), "Dog", "Can find lost items"));
        anml.add(new Hamster("Barney", LocalDate.of(2022, 7, 17), "Hamster", "Spins in place on command"));
        anml.add(new Horse("Zeus", LocalDate.of(2023, 2, 28), "Horse", "Can jump over obstacles"));
        anml.add(new Camel("Charlie", LocalDate.of(2020, 9, 10), "Camel", "Balances on two legs skillfully"));
        anml.add(new Donkey("Mila", LocalDate.of(2023, 6, 25), "Donkey", "Can pull a cart"));
        anml.add(new Cat("Tigra", LocalDate.of(2021, 8, 8), "Cat", "Can jump high"));
        anml.add(new Dog("Byron", LocalDate.of(2022, 12, 11), "Dog", "Can find its way through a maze"));

        for (Animal animal : anml) {
            animalregistry.addAnimal(animal);
        }

        while (start) {
            System.out.println();
            System.out.println("Welcome to the Animal Registry!\n\nHow can we help you?");
            System.out.println("\n1. Show the list of animals.\n2. Select an animal.");
            System.out.println("3. Add a new animal.\n4. Teach the animal new commands.");
            System.out.println("5. Exit.");
            System.out.println("\nSelect an option:");

            int option = scanner.nextInt();
            scanner.nextLine();

            switch (option) {
                case 1:
                    animalregistry.listAnimals();
                    break;
                case 2:
                    System.out.println("Enter the animal's name: ");
                    String name = scanner.nextLine().trim();
                    Animal animal = animalregistry.getAnimal(name);
                    if (animal != null) {
                        System.out.println(animal);
                    } else {
                        System.out.println("Animal not found.");
                    }
                    break;
                case 3:
                    try {
                        System.out.println("Enter the animal's name: ");
                        String newName = scanner.nextLine();
                        System.out.println("Enter your date of birth (year-month-day): ");
                        String newBirth = scanner.nextLine();
                        LocalDate birth = LocalDate.parse(newBirth);
                        System.out.println("What kind of animal is this? ");
                        String newType = scanner.nextLine().toLowerCase();
                        System.out.println("What commands does it know? ");
                        String newCommand = scanner.nextLine();

                        if (newName.isEmpty() || birth == null || newType.isEmpty()) {
                            System.out.println("Please fill in all fields!");
                            continue;
                        }

                        Animal newAnimal;

                        if (newType.equals("cat") || newType.equals("dog") || newType.equals("hamster")) {
                            newAnimal = new DomesticAnimal(newName, birth, newType, newCommand);
                        } else if (newType.equals("horse") || newType.equals("camel") || newType.equals("donkey")) {
                            newAnimal = new PackAnimal(newName, birth, newType, newCommand);
                        } else {
                            newAnimal = new Animal(newName, birth, newType, newCommand);
                        }

                        animalregistry.addAnimal(newAnimal);
                        counter.add();
                        System.out.println("Number of animals registrated: " + counter.toString());

                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Enter the animal's name: ");
                    String animalName = scanner.nextLine();
                    Animal selectedAnimal = animalregistry.getAnimal(animalName);
                    if (selectedAnimal != null) {
                        System.out.println("Enter new command/commands: ");
                        String newCommands = scanner.nextLine();
                        selectedAnimal.addCommands(newCommands);
                        System.out.println("Command/commands added.");
                        ;
                    } else {
                        System.out.println("Animal not found.");
                    }
                    break;
                case 5:
                    start = false;
                    break;
                default:
                    System.out.println("Please select a number from 1 to 5.");
            }
        }
        scanner.close();
        counter.close();
        System.out.println("See you!");
    }

}
