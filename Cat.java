import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Cat extends Animal {
    public Cat(String name, LocalDate birthDate, List<String> commands) {
        super(name, birthDate, commands);
    }

    public Cat(String name, LocalDate birthDate) {
        super(name, birthDate, new ArrayList<>());
    }

    @Override
    public String toString() {
        return "Кошка " + super.toString();
    }

    @Override
    public String commandlessString() {
        return "Кошка " + super.commandlessString();
    }
}
