import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        OtomotoScraper scraper = new OtomotoScraper("/osobowe/bmw/m4/", true);

        for (SpecOffer offer : scraper.offers) {
            System.out.println(offer);
        }

    }
}
