import java.sql.*;
import java.time.Duration;
import java.time.Instant;
import java.util.Scanner;

public class Menu {
    private static String nick = "";

    public static void main(String[] args) throws SQLException {

        String url;
        Instant start = Instant.now();
        Instant end = Instant.now();

        if (loginMenu()) {
            switch (displayMenu()) {
                case 1:
                    System.out.println("Wklej reszte linku bez 'otomoto.pl/': ");
                    url = new Scanner(System.in).nextLine();
                    start = Instant.now();
                    new OtomotoScraper(url, true, "result/result.xls");
                    end = Instant.now();
                    break;

                case 2:
                    System.out.println("Wklej reszte linku bez 'otomoto.pl/': ");
                    url = new Scanner(System.in).nextLine();
                    start = Instant.now();
                    new OtomotoScraperToDB(url, true, true);
                    end = Instant.now();
                    break;

                case 3:
                    System.out.println("Wklej reszte linku bez 'otomoto.pl/': ");
                    url = new Scanner(System.in).nextLine();
                    start = Instant.now();
                    OtomotoScraper scraper = new OtomotoScraperToDB(url, true);
                    end = Instant.now();
                    for (SpecOffer offer : scraper.offers) {
                        System.out.println(offer);
                    }
                    break;

                case 4:
                    start = Instant.now();
                    ResultSet resultSet = getCarsFromDB();
                    end = Instant.now();
                    while (resultSet.next()) {
                        for (int i=1; i<=12; i++) {
                            System.out.println(resultSet.getString(i));
                        }
                        System.out.println("==================================================================");
                    }
                    break;

                default:
                    System.out.println("Taka opcja nie instnieje");
            }
        }
        else {
            System.out.println("Niepoprawne dane logowania - Odmowa dostepu");
        }

        System.out.println("Czas wykonania operacji: " + Duration.between(start, end).toMillis() + " millisekund/y");

    }

    public static boolean loginMenu() throws SQLException {
        Scanner scan = new Scanner(System.in);

        System.out.print("Login: ");
        String nick = scan.nextLine();

        System.out.print("Haslo: ");
        String password = scan.nextLine();

        if (login(nick, password)) {
            Menu.nick = nick;
            return true;
        }
        return false;
    }

    public static int displayMenu() {
        System.out.println("-----------------------------------------");
        System.out.println("    Zalogowano jako: " + Menu.nick);
        System.out.println("-----------------------------------------");
        System.out.println("Wybierz opcję");


        System.out.println("1. Zapisz do xls");
        System.out.println("2. Zapisz do bazy danych");
        System.out.println("3. Wyswietl w konsoli");
        System.out.println("4. Wyswietl zawartosc bazy danych");

        return new Scanner(System.in).nextInt();
    }

    public static boolean login(String username, String password) throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/otomoto",
                "root",
                ""
        );
        ResultSet resultSet = conn.createStatement().executeQuery("SELECT * FROM users");

        while (resultSet.next()) {
            if (username.equals(resultSet.getString("username")))
                if (password.equals(resultSet.getString("password")))
                    return true;
        }
        return false;
    }

    public static ResultSet getCarsFromDB() throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/otomoto",
                "root",
                ""
        );
        return conn.createStatement().executeQuery("SELECT * FROM cars");
    }

}
