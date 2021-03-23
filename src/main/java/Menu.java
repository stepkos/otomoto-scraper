import java.sql.*;

public class Menu {
    private static boolean logged = false;

    public static void main(String[] args) throws SQLException {

        while (!logged) {
            displayMenu("Nie zalogowany");

        }

    }

    public static void displayMenu(String nick) {
        System.out.println("-----------------------------------------");
        System.out.println("    Zalogowano jako: " + nick);
        System.out.println("-----------------------------------------");
        System.out.println("Wybierz opcję");

        if (logged) {
            System.out.println("1. Uruchom Scraper");
            System.out.println("2. Wyloguj");

        }
        else
            System.out.println("1. Zaloguj");

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
