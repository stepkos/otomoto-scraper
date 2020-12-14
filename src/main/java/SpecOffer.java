import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.function.Function;

public class SpecOffer {
    public final String LINK;
    public final String TITLE;
    public final String SUBTITLE;
    public final String YEAR;
    public final String MILEAGE;
    public final String ENGINE_CAPACITY;
    public final String FUEL_TYPE;
//    public final String PRICE;
//    public final String PRICE_CURRENCY;
//    public final String LOCATION;

    public SpecOffer(Element offer) {
        Element offerContent = offer.getElementsByClass("offer-item__content").first();
        Element title = offerContent.getElementsByClass("offer-item__title").first();

        Elements parameters = offerContent
                .getElementsByClass("ds-params-block")
                .first()
                .getElementsByClass("ds-param");

        String tmpSubtitle;

        try {
            tmpSubtitle = title.select("h3").first().text();
        }
        catch (NullPointerException error) {
            tmpSubtitle = "";
        }

        LINK = offer.attributes().get("data-href");
        TITLE = title.select("h2 a").first().text();
        SUBTITLE = tmpSubtitle;

        if (parameters.size() < 4) {
            YEAR = parameters.get(0).select("span").text();
            ENGINE_CAPACITY = parameters.get(1).select("span").text();
            FUEL_TYPE = parameters.get(2).select("span").text();
            MILEAGE = "1 km";
        }
        else
        {
            YEAR = parameters.get(0).select("span").text();
            MILEAGE = parameters.get(1).select("span").text();
            ENGINE_CAPACITY = parameters.get(2).select("span").text();
            FUEL_TYPE = parameters.get(3).select("span").text();
        }

        System.out.println(TITLE);
        System.out.println(SUBTITLE);
        System.out.println(YEAR);
        System.out.println(MILEAGE);
        System.out.println(ENGINE_CAPACITY);
        System.out.println(FUEL_TYPE);
        System.out.println("------------");
    }

}
