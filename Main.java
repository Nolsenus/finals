import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {

    private static final List<String> mainMenuCommands =
            Arrays.asList("Добавить животное",
            "Увидеть список животных",
            "Увидеть список команд животного",
            "Обучить животное новой команде",
            "Закончить работу");

    public static void main(String[] args) {
        AnimalRoster ar = new AnimalRoster();
        UI ui = new ConsoleUi();
        ui.init();
        try (Counter counter = new Counter()) {
            boolean continuing = true;
            while (continuing) {
                int choice = ui.getChoice(mainMenuCommands);
                switch (choice) {
                    case 0: {
                        Animal animal = createAnimal(ui);
                        ar.addAnimal(animal);
                        counter.add();
                        break;
                    }
                    case 1: {
                        List<Animal> animals = ar.getAnimals();
                        showAnimals(ui, animals);
                        break;
                    }
                    case 2: {
                        showAnimalCommands(ui, chooseAnimal(ui, ar));
                        break;
                    }
                    case 3: {
                        teachNewCommand(ui, chooseAnimal(ui, ar));
                        break;
                    }
                    case 4:
                        continuing = false;
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        ui.close();
    }

    private static final List<String> animalTypes = Arrays.asList("Собака", "Кошка", "Хомяк");

    private static Animal createAnimal(UI ui) {
        ui.showMessage("Выберите какое животное вы хотите добавить:");
        int choice = ui.getChoice(animalTypes);
        String name = ui.getString(String.format("Введите кличку %s:",
                choice == 0 ? "собаки": choice == 1 ? "кошки" : "хомяка"));
        LocalDate birthDate = ui.getDate("Когда родился " + name + "?:");
        Animal animal;
        if (choice == 0) {
            animal = new Dog(name, birthDate);
        } else if (choice == 1) {
            animal = new Cat(name, birthDate);
        } else {
            animal = new Hamster(name, birthDate);
        }
        return animal;
    }

    private static void showAnimals(UI ui, List<Animal> animals) {
        if (animals.isEmpty()) {
            ui.showMessage("Пока что нет животных.");
        } else {
            for (Animal animal : animals) {
                ui.showMessage(animal.toString());
            }
        }
    }

    private static Animal chooseAnimal(UI ui, AnimalRoster ar) {
        List<Animal> animals = ar.getAnimals();
        List<String> animalStrings = new ArrayList<>(animals.size());
        for (Animal animal : animals) {
            animalStrings.add(animal.commandlessString());
        }
        ui.showMessage("Выберите животное, чьи команды хотите посмотреть:");
        return animals.get(ui.getChoice(animalStrings));
    }

    private static void showAnimalCommands(UI ui, Animal animal) {
        List<String> commands = animal.getCommands();
        if (commands.isEmpty()) {
            ui.showMessage(String.format("%s не знает никаких команд.", animal.getName()));
        } else {
            for (String command : commands) {
                ui.showMessage(command);
            }
        }
    }

    private static void teachNewCommand(UI ui, Animal animal) {
        String newCommand = ui.getString(String.format("Введите команду, которой хотите научить %s:", animal.getName()));
        try {
            animal.teachNewCommand(newCommand);
        } catch (AnimalCommandException e) {
            ui.showMessage(e.getMessage());
        }
    }
}
