import java.time.LocalDate;
import java.util.List;

public interface UI {
    void showMessage(String message);

    String getString(String prompt);

    int getInt(String prompt);

    double getDouble(String prompt);

    int getChoice(List<String> options);

    LocalDate getDate(String prompt);

    void init();

    void close();
}
