import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class Animal {
    private final String name;
    private final LocalDate birthDate;
    private final List<String> commands;

    public Animal(String name, LocalDate birthDate, List<String> commands) {
        this.name = name;
        this.commands = commands;
        this.birthDate = birthDate;
    }

    public List<String> getCommands() {
        return new ArrayList<>(commands);
    }

    public String getName() {
        return name;
    }

    public void teachNewCommand(String command) throws AnimalCommandException {
        if (commands.contains(command)) {
            throw new AnimalCommandException(name + " уже знает команду " + command + '.');
        }
        commands.add(command);
    }

    @Override
    public String toString() {
        String commandsString = commands.isEmpty() ?
                "не знает никаких команд" :
                String.format("знает команды: %s", String.join(", ", commands));
        return String.format("по кличке %s %s года рождения (%s).",
                name, birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")), commandsString);
    }

    public String commandlessString() {
        return String.format("по кличке %s %s года рождения.",
                name, birthDate.format(DateTimeFormatter.ofPattern("dd.MM.yyyy")));
    }
}
