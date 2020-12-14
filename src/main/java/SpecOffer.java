import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;

public class SpecOffer {
//    public final Element BODY;
//    public final String LINK;

    public SpecOffer(Element element) {
//        BODY = element;
        System.out.println(element.getElementsByClass("ds-params-block").first().getElementsByClass("ds-param").get(1));
//        Attributes attributes = element.getElementsByClass("offer__click-area").first().attributes();
//        LINK = attributes.get("href");
    }

}
