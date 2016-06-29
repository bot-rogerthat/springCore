package spring.core.view;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import spring.core.entity.Auditorium;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;


public class AuditoriumPdfViewResolver extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter writer,
                                    HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<Auditorium> auditoriums = (List<Auditorium>) map.get("auditoriums");
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Name"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Number of seats"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Vips"));
        table.addCell(cell);

        auditoriums.stream().forEach(auditorium -> {
            table.addCell(auditorium.getName());
            table.addCell(String.valueOf(auditorium.getNumberOfSeats()));
            table.addCell(auditorium.getVips().toString());
        });
        document.add(table);
    }
}
