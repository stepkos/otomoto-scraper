import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;

public class OtomotoScraper {
    private static final String baseUrl = "https://www.otomoto.pl";

    private String url;
    private Document doc;

    public ArrayList<SpecOffer> offers = new ArrayList<>();

    public OtomotoScraper() {}

    public OtomotoScraper(String restOfUrl) {
        url = baseUrl.concat(restOfUrl);
    }

    public OtomotoScraper(String restOfUrl, boolean process) {
        url = baseUrl.concat(restOfUrl);
        if (process) this.process();
    }

    public OtomotoScraper(String restOfUrl, boolean process, boolean save) {
        url = baseUrl.concat(restOfUrl);
        if (process) this.process();
        if (save) this.save();
    }

    public void save() {

    }

    public void process() {
        if (connect()) {
            for (Element element : doc.getElementsByClass("offer-item")) {
                offers.add(new SpecOffer(element));
            }
        }
    }

    private boolean connect() {
        try {
            doc = Jsoup.connect(url).get();
        }
        catch (IOException error) {
            error.printStackTrace();
            return false;
        }
        return true;
    }

    public void setRestOfUrl(String restOfUrl) {
        url = baseUrl.concat(restOfUrl);
    }

    public String getFullUrl() {
        return url;
    }


}
