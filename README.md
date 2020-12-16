# javaDataScraping - Scraping data about cars from otomoto.pl
## Whole code has a great comments so I hope you will not get lost
### All you really need for use is in src/main/java/Main.java
```java
// Pure constructor
OtomotoScraper scraper1 = new OtomotoScraper();
scraper1.setRestOfUrl("osobowe/bmw/m4/");
scraper1.process();
scraper1.save("result/m2.xls");

// Link constructor
OtomotoScraper scraper2 = new OtomotoScraper("osobowe/audi/rs3/");
scraper2.process();
scraper2.save("result/rs3.xls");

// Link and process constructor
OtomotoScraper scraper3 = new OtomotoScraper("osobowe/mercedes-benz/amg-gt/", true);
scraper3.save("result/amgGt.xls");

// Full constructors
new OtomotoScraper("osobowe/opel/insignia/", true, "result/insignia.xls");
```

