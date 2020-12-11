import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String urlBase = "https://www.pracuj.pl";
        String url = urlBase.concat("/praca/programista;kw?rd=30");
        Document document;

        try {
            document = Jsoup.connect(url).get();
        }
        catch (IOException error) {
            error.printStackTrace();
            return;
        }


        Elements elements = document.getElementsByClass("results__list-container-item");

        ArrayList<SpecOffer> offers = new ArrayList<>();
        for (Element element : elements) {
            offers.add(new SpecOffer(element));
        }


    }
}
