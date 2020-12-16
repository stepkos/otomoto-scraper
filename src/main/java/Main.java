import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        OtomotoScraper scraper = new OtomotoScraper();
        scraper.setRestOfUrl("/osobowe/audi/s6/?search%5Bfilter_float_price%3Afrom%5D=35000&search%5Bfilter_float_price%3Ato%5D=7500000&search%5Bfilter_enum_generation%5D=gen-c7-2011");
        scraper.process();
        scraper.save();

    }
}
