import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class AnimalRoster {
    private final List<Animal> animals;

    public AnimalRoster() {
        animals = new ArrayList<>();
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public List<Animal> getAnimals() {
        return new ArrayList<>(animals);
    }
}
