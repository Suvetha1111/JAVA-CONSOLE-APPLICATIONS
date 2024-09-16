/*
TO print calendar of the given month,we have to find the starting day of the month , to find starting day of month,we have to find the day of the given date.
For all these ,R.S.Agarwall method of finding the number odd of days and modulo of that gives the day of the given date.
From that we can print the calendar.

Odd days= no.of day more than complete week
 the calculateOddDays method following lam include aavum:

The number of odd days from previous years.
The number of days in each month up to the month before the target month.
The days of the current month to get the total odd days.
By taking modulo 7 of the total odd days, we can get  the result to the day of the week.



*/
import java.util.Scanner;

public class Calendar {


    public static boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }

    public static int get_days_in_month(int month, int year) {
        int[] days = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        if (month == 2 && isLeapYear(year)) {
            return 29;
        } else {
            return days[month - 1];
        }
    }

 
    public static int calculateOddDays(int year, int month, int day) {
        int oddDays = 0;

      // I'm specifying here from 1900 we can custom it by rewriting with year we wanted to start from or behind this year
        for (int y = 1900; y < year; y++) {
            oddDays += isLeapYear(y) ? 2 : 1;
        }

        // Count odd days for each month in the given year up to the previous month
        for (int m = 1; m < month; m++) {
            oddDays += get_days_in_month(m, year);
        }

        // Add odd days for the days in the current month
        oddDays += day;

        return oddDays % 7;
    }

    public static String getDayOfWeek(int year, int month, int day) {
        int oddDays = calculateOddDays(year, month, day);
        String[] daysOfWeek = {"Sun", "Mon", "Tue", "Wed", "Thu", "Fri", "Sat"};
        return daysOfWeek[oddDays];
    }

    public static void display_Calendar(int month, int year) {
        int daysInMonth = get_days_in_month(month, year);
        System.out.println("\n========================");
        System.out.println("\t " + month + "/" + year);
        System.out.println("\n========================");
        System.out.println("SUN MON TUE WED THU FRI SAT");

        int startingDay = calculateOddDays(year, month, 1); 

       
        for (int i = 0; i < startingDay; i++) {
            System.out.print("    ");  
        }

        for (int day = 1; day <= daysInMonth; day++) {
            System.out.print(String.format("%4d", day));
            startingDay++;
            if (startingDay % 7 == 0 || day == daysInMonth) {
                System.out.println();
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Year : ");
        int year = sc.nextInt();
        System.out.print("Enter Month : ");
        int month = sc.nextInt();
        System.out.print("Enter Day : ");
        int day = sc.nextInt();
        if (month < 1 || month > 12) {
            System.out.println("Invalid month");
            return;
        }
        if (year < 0) {
            System.out.println("Invalid year");
            return;
        }
        if (day < 1 || day > get_days_in_month(month, year)) {
            System.out.println("Invalid day");
            return;
        }
        System.out.println("The day of the week is: " + getDayOfWeek(year, month, day));
        display_Calendar(month, year);
    }
}
