import java.sql.*;
import java.util.Scanner;

public class Menu {
    private static String nick = "";

    public static void main(String[] args) throws SQLException {

        // LOGOWAONIE -> WYBÓR -> LINK -> WYJSCIE
        if (loginMenu()) {
            switch (displayMenu()) {
                case 1:
                    System.out.println("Wybrano 1");
                    break;
                case 2:
                    System.out.println("Wybrano 2");
                    break;
                default:
                    System.out.println("Taka opcja nie instnieje");
            }
        }
        else {
            System.out.println("Niepoprawne dane logowania - Odmowa dostepu");
        }

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
        System.out.println("2. Zapisz do SQL");

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

}
