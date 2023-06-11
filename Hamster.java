import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Hamster extends Animal {
    public Hamster(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }

    public Hamster(String name, LocalDate birthDate) {
        super(name, birthDate, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Хомяк " + super.toString();
    }

    @Override
    public String commandlessString() {
        return "Хомяк " + super.commandlessString();
    }
}
