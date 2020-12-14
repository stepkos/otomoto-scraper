import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {

        String urlBase = "https://www.otomoto.pl";
        String url = urlBase.concat("/osobowe/bmw/m4/");
        Document document;

        try {
            document = Jsoup.connect(url).get();
        }
        catch (IOException error) {
            error.printStackTrace();
            return;
        }


        Elements elements = document.getElementsByClass("offer-item");
//        System.out.println(elements);

        ArrayList<SpecOffer> offers = new ArrayList<>();
        for (Element element : elements) {
            offers.add(new SpecOffer(element));
        }

//        System.out.println(offers.get(0).LINK);
//        System.out.println(offers.size());


    }
}
