import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileOutputStream;
import java.util.ArrayList;

import java.io.IOException;

/**
 * It is a class to wrapping SpecOffer and automate processes.
 * Class handle download and sava data to .xls file.
 */
public class OtomotoScraper {
    protected static final String baseUrl = "https://www.otomoto.pl/";

    protected String url; // Whole URL
    protected Document doc; // Whole subpage

    public ArrayList<SpecOffer> offers = new ArrayList<>();


    // Constructors to choose from to color
    public OtomotoScraper() {}

    public OtomotoScraper(String restOfUrl) {
        url = baseUrl.concat(restOfUrl);
    }

    public OtomotoScraper(String restOfUrl, boolean process) {
        url = baseUrl.concat(restOfUrl);
        if (process) this.process();
    }

    public OtomotoScraper(String restOfUrl, boolean process, String filePath) {
        url = baseUrl.concat(restOfUrl);
        if (process) {
            this.process();
            this.save(filePath);
        }
    }

    /** Method to fill 'offers' array with data */
    public void process() {
        if (connect()) {
            for (Element element : doc.getElementsByClass("offer-item")) {
                offers.add(new SpecOffer(element));
            }
        }
    }

    /** Method to save data to .xsl file */
    public void save(String filePath) {
        Workbook wb = new HSSFWorkbook(); // Creating workbook
        Sheet sh = wb.createSheet("cars"); // Creating sheet of workbook

        // Prepare font for header (row - 0)
        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        // Apply font to cell style
        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);

        // Prepare header data to insert
        Row headerRow = sh.createRow(0);
        String[] headerValues = {
                "Title",
                "Subtitle",
                "Year",
                "Mileage",
                "Engine Capacity",
                "Price",
                "Fuel Type",
                "Price Currency",
                "Location",
                "Link"
        };

        // Inserting header data
        for (int i = 0; i < headerValues.length; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(headerValues[i]);
            cell.setCellStyle(headerCellStyle);
        }

        // Inserting car attributes
        int rowNum = 1;
        for (SpecOffer offer: offers) {
            Row row = sh.createRow(rowNum++);
            int cellNum = 0;
            for (String attribute: offer.getInArray()) {
                row.createCell(cellNum++).setCellValue(attribute);
            }
        }

        // Resize all columns to fit the content size
        for (int i = 0; i < offers.size(); i++) {
            sh.autoSizeColumn(i);
        }

        // Write the output to a file
        try {
            FileOutputStream fileOut = new FileOutputStream(filePath);
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected boolean connect() {
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
