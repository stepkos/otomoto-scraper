import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class OtomotoScraperToDB extends OtomotoScraper {

    protected String prepareSql = "INSERT INTO cars(title, subtitle, year, mileage, engine_capacity, price, fuel_type, price_currency, location, link) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

    public OtomotoScraperToDB() {}

    public OtomotoScraperToDB(String restOfUrl) {
        url = baseUrl.concat(restOfUrl);
    }

    public OtomotoScraperToDB(String restOfUrl, boolean process) {
        url = baseUrl.concat(restOfUrl);
        if (process) this.process();
    }

    public OtomotoScraperToDB(String restOfUrl, boolean process, boolean save) throws SQLException {
        url = baseUrl.concat(restOfUrl);
        if (process)
            this.process();
        if (save)
            this.saveToDB();
    }

    public void saveToDB() throws SQLException {
        Connection conn = DriverManager.getConnection(
                "jdbc:mysql://localhost/otomoto",
                "root",
                ""
        );

        for (SpecOffer offer: offers) {
            PreparedStatement prepare = conn.prepareStatement(prepareSql);
            int cellNum = 1;
            for (String attribute: offer.getInArray())
                prepare.setString(cellNum++, attribute);
            prepare.execute();
        }
    }
}
