import java.util.Arrays;
import java.util.Calendar;
import java.util.Scanner;

public class SimpleCalendar {
    public static void main(String[] args) {
        SimpleCalendar calendar = new SimpleCalendar();
        calendar.start();
    }

    private final String[][] months = {
            {"January", "31"}, {"February", "28"}, {"March", "31"}, {"April", "30"},
            {"May", "31"}, {"June", "30"}, {"July", "31"}, {"August", "31"},
            {"September", "30"}, {"October", "31"}, {"November", "30"}, {"December", "31"}
    };
    private final String days = "Mon Tue Wed Thu Fri Sat Sun";
    private int year = 2024;

    public void start() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print("Enter the year: ");
            if (scanner.hasNextInt()) {
                year = scanner.nextInt();
                if (year > 0) {
                    break;
                } else {
                    System.out.println("Please enter a valid positive year.");
                }
            } else {
                System.out.println("Invalid input. Please enter a valid year.");
                scanner.next();
            }
        }
        scanner.close();
        System.out.println("\n"+year);
        int startDay = getStartDayOfYear(year);// 0=Mon, 1=Tue, ..., 6=Sun. January 1st, 2024 is a Monday.
        for (String[] month : months) {
            System.out.println(month[0]);
            System.out.println(days);

            int quantityOfDays = Integer.parseInt(month[1]);
            if (month[0].equals("February") && checkForLeapYear(year)) {
                quantityOfDays = 29;
            }

            printMonth(quantityOfDays, startDay);

            startDay = (startDay + quantityOfDays) % 7;
        }
    }

    private void printMonth(int quantityOfDays, int startDay) {
        int dayOfWeek = 0;
        for (int i = 0; i < startDay; i++) {
            System.out.print("    ");
            dayOfWeek++;
        }

        for (int day = 1; day <= quantityOfDays; day++) {
            System.out.printf("%3d ", day);
            dayOfWeek++;
            if (dayOfWeek == 7) {
                System.out.println();
                dayOfWeek = 0;
            }
        }

        if (dayOfWeek != 0) {
            System.out.println();
        }
    }

    public boolean checkForLeapYear(int year) {
        if (year % 4 == 0) {
            if (year % 100 == 0) {
                return year % 400 == 0;
            } else {
                return true;
            }
        } else {
            return false;
        }
    }
    private int getStartDayOfYear(int year) {
        Calendar calendar = Calendar.getInstance();
        calendar.set(year, Calendar.JANUARY, 1);
        int dayOfWeek = calendar.get(Calendar.DAY_OF_WEEK);
        // Convert Calendar's day of week (1=Sunday, ..., 7=Saturday) to the format (0=Monday, ..., 6=Sunday)
        return (dayOfWeek + 5) % 7;
    }
}
