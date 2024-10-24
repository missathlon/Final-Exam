import java.util.ArrayList;

class AnimalRegistry {
    private ArrayList<Animal> animals = new ArrayList<>();

    public void initAnimals(ArrayList<Animal> animals) {
        this.animals = animals;
    }

    public void listAnimals() {
        System.out.println("List of all animals:");
        for (Animal a : animals) {
            System.out.println(a);
        }
    }

    public Animal getAnimal(String name) {
        for (Animal a : animals) {
            if (a.getName().equals(name)) {
                return a;
            }
        }
        return null;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
        System.out.println("Animal added: " + animal.getName());
    }
}
