package spring.core.view;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public class EventPdfViewResolver extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter writer,
                                    HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<spring.core.entity.Event> events = (List<spring.core.entity.Event>) map.get("events");
        PdfPTable table = new PdfPTable(6);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Name"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("date"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("time"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("price"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("rating"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("auditoriumId"));
        table.addCell(cell);

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        events.stream().forEach(event -> {
            table.addCell(event.getName());
            table.addCell(df.format(event.getDate()));
            table.addCell(String.valueOf(event.getTime()));
            table.addCell(String.valueOf(event.getPrice()));
            table.addCell(event.getRating().toString());
            table.addCell(String.valueOf(event.getAuditoriumId()));
        });
        document.add(table);
    }
}
