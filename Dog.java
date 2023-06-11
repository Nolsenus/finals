import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Dog extends Animal {
    public Dog(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }

    public Dog(String name, LocalDate birthDate) {
        super(name, birthDate, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Собака " + super.toString();
    }

    @Override
    public String commandlessString() {
        return "Собака " + super.commandlessString();
    }
}
