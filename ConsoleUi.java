import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

public class ConsoleUi implements UI {

    private Scanner in;

    @Override
    public void showMessage(String message) {
        System.out.println(message);
    }

    @Override
    public String getString(String prompt) {
        System.out.println(prompt);
        return in.nextLine();
    }

    @Override
    public int getInt(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Integer.parseInt(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не целое число, попробуйте снова.");
            }
        }
    }

    @Override
    public double getDouble(String prompt) {
        System.out.println(prompt);
        while (true) {
            try {
                return Double.parseDouble(in.nextLine());
            } catch (NumberFormatException e) {
                System.out.println("Вы ввели не число, попробуйте снова.");
            }
        }
    }

    @Override
    public int getChoice(List<String> options) {
        while (true) {
            for (int i = 0; i < options.size(); i++) {
                System.out.println((i + 1) + " - " + options.get(i));
            }
            int choice = getInt("Введите число, соответствующее выбранному вами варианту:");
            if (choice >= 1 && choice <= options.size()) {
                return choice - 1;
            }
            System.out.println("Вы ввели некорректное число, попробуйте снова.");
        }
    }

    @Override
    public LocalDate getDate(String prompt) {
        boolean isCurrentYear = false;
        int currentYear = LocalDate.now().getYear();
        int year;
        System.out.println(prompt);
        while (true) {
            year = getInt("Введите год:");
            if (year < 0) {
                System.out.println("Год не может быть отрицательным, попробуйте снова.");
                continue;
            }
            if (year > currentYear) {
                System.out.println("Этот год ещё не наступил, попробуйте снова.");
                continue;
            }
            isCurrentYear = year == currentYear;
            break;
        }
        int month = 0;
        do {
            String monthString = getString("Введите месяц:");
            switch (monthString.toLowerCase()) {
                case "декабрь":
                case "12":
                    month++;
                case "ноябрь":
                case "11":
                    month++;
                case "октябрь":
                case "10":
                    month++;
                case "сентябрь":
                case "09":
                case "9":
                    month++;
                case "август":
                case "08":
                case "8":
                    month++;
                case "июль":
                case "07":
                case "7":
                    month++;
                case "июнь":
                case "06":
                case "6":
                    month++;
                case "май":
                case "05":
                case "5":
                    month++;
                case "апрель":
                case "04":
                case "4":
                    month++;
                case "март":
                case "03":
                case "3":
                    month++;
                case "февраль":
                case "02":
                case "2":
                    month++;
                case "январь":
                case "01":
                case "1":
                    month++;
                    break;
                default:
                    System.out.println("Вы ввели некорректный месяц, попробуйте снова.");
            }
        } while (month == 0);
        int day;
        while (true) {
            day = getInt("Введите день:");
            if (day < 1 || day > 31) {
                System.out.println("Вы ввели невозможное значение дня, попробуйте снова.");
                continue;
            }
            if (((month < 7 && month % 2 == 0) || month == 9 || month == 11) && day == 31) {
                System.out.println("В указанном вами месяце нет 31 дня, попробуйте снова.");
                continue;
            }
            if (month == 2 && day == 30) {
                System.out.println("В феврале нет 30 дня, попробуйте снова.");
                continue;
            }
            if (year % 4 != 0 && day == 29) {
                System.out.println("Указанный вами год не високосный, в его феврале нет 29 дня, попробуйте снова.");
                continue;
            }
            break;
        }
        return LocalDate.parse(String.format("%04d-%02d-%02d", year, month, day));
    }

    @Override
    public void init() {
        in = new Scanner(System.in);
    }

    @Override
    public void close() {
        in.close();
    }
}
