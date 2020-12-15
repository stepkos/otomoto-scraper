import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;


/**
* It is a class to collect data about specific car offer from otomoto.pl page.
* Attributes are final and used to read only.
*/
public class SpecOffer {

    // Attributes
    public final String LINK;
    public final String TITLE;
    public final String SUBTITLE;
    public final String YEAR;
    public final String MILEAGE;
    public final String ENGINE_CAPACITY;
    public final String FUEL_TYPE;
    public final String PRICE;
    public final String PRICE_CURRENCY;
    public final String LOCATION;

    /** Constructor used to scraping and set constants. */
    public SpecOffer(Element offer) {

        // Declare handles to get attributes faster and clearly
        Element offerContent = offer.getElementsByClass("offer-item__content").first();
        Element title = offerContent.getElementsByClass("offer-item__title").first();
        Elements parameters = offerContent
                .getElementsByClass("ds-params-block")
                .first()
                .getElementsByClass("ds-param");


        // Not every offer has a subtitles
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

        // Sometimes when car is new could has not a mileage parameter in html
        if (parameters.size() < 4) {
            YEAR = parameters.get(0).select("span").text();
            ENGINE_CAPACITY = parameters.get(1).select("span").text();
            FUEL_TYPE = parameters.get(2).select("span").text();
            MILEAGE = "1 km";
        }
        else {
            YEAR = parameters.get(0).select("span").text();
            MILEAGE = parameters.get(1).select("span").text();
            ENGINE_CAPACITY = parameters.get(2).select("span").text();
            FUEL_TYPE = parameters.get(3).select("span").text();
        }

        Elements price = offerContent.select(".offer-price__number span");
        PRICE = price.get(0).text();
        PRICE_CURRENCY = price.get(1).text();

        LOCATION = offerContent.getElementsByTag("h4").first().text();
    }

    public String[] getInArray() {
        return new String[] {
                LINK,
                TITLE,
                SUBTITLE,
                YEAR,
                MILEAGE,
                ENGINE_CAPACITY,
                PRICE,
                FUEL_TYPE,
                PRICE_CURRENCY,
                LOCATION
        };
    }

    public HashMap<String, String> getInHashMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("link", LINK);
        map.put("title", TITLE);
        map.put("subtitle", SUBTITLE);
        map.put("year", YEAR);
        map.put("mileage", MILEAGE);
        map.put("engine_capacity", ENGINE_CAPACITY);
        map.put("price", PRICE);
        map.put("fuel_type", FUEL_TYPE);
        map.put("price_currency", PRICE_CURRENCY);
        map.put("location", LOCATION);
        return map;
    }

    public void printAll() {
        for (String attribute : getInArray()) {
            System.out.println(attribute);
        }
        System.out.println("--------------");
    }

}
