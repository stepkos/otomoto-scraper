public class OtomotoScraperToDB extends OtomotoScraper {

    public OtomotoScraperToDB() {}

    public OtomotoScraperToDB(String restOfUrl) {
        url = baseUrl.concat(restOfUrl);
    }

    public OtomotoScraperToDB(String restOfUrl, boolean process) {
        url = baseUrl.concat(restOfUrl);
        if (process) this.process();
    }

    public OtomotoScraperToDB(String restOfUrl, boolean process, boolean save) {
        url = baseUrl.concat(restOfUrl);
        if (process)
            this.process();
        if (save)
            this.saveToDB();
    }

    public void saveToDB() {
        System.out.println("Zapisano");
    }
}
