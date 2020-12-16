import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
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
        Workbook wb = new HSSFWorkbook();
        Sheet sh = wb.createSheet("cars");

        Font headerFont = wb.createFont();
        headerFont.setBold(true);
        headerFont.setFontHeightInPoints((short) 12);

        CellStyle headerCellStyle = wb.createCellStyle();
        headerCellStyle.setFont(headerFont);

        Row headerRow = sh.createRow(0);

        for(int i = 0; i < 10; i++) {
            Cell cell = headerRow.createCell(i);
            cell.setCellValue(i);
            cell.setCellStyle(headerCellStyle);
        }

        int rowNum = 1;
        for(SpecOffer offer: offers) {
            Row row = sh.createRow(rowNum++);

            row.createCell(0).setCellValue(offer.TITLE);

            row.createCell(1).setCellValue(offer.MILEAGE);

//            Cell dateOfBirthCell = row.createCell(2);
//            dateOfBirthCell.setCellValue(employee.getDateOfBirth());
//            dateOfBirthCell.setCellStyle(dateCellStyle);

            row.createCell(2).setCellValue(offer.LOCATION);
        }

        // Resize all columns to fit the content size
        for(int i = 0; i < 10; i++) {
            sh.autoSizeColumn(i);
        }

        // Write the output to a file
        try {
            FileOutputStream fileOut = new FileOutputStream("test.xls");
            wb.write(fileOut);
            fileOut.close();
            wb.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
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
