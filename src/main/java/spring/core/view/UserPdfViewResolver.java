package spring.core.view;

import com.lowagie.text.Document;
import com.lowagie.text.Phrase;
import com.lowagie.text.pdf.PdfPCell;
import com.lowagie.text.pdf.PdfPTable;
import com.lowagie.text.pdf.PdfWriter;
import org.springframework.web.servlet.view.document.AbstractPdfView;
import spring.core.entity.User;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Map;


public class UserPdfViewResolver extends AbstractPdfView {
    @Override
    protected void buildPdfDocument(Map<String, Object> map, Document document, PdfWriter writer,
                                    HttpServletRequest req, HttpServletResponse res) throws Exception {
        List<User> users = (List<User>) map.get("users");
        PdfPTable table = new PdfPTable(3);
        table.setWidthPercentage(100.0f);
        table.setSpacingBefore(10);

        PdfPCell cell = new PdfPCell();
        cell.setBackgroundColor(Color.LIGHT_GRAY);
        cell.setPadding(5);
        cell.setPhrase(new Phrase("Name"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Email"));
        table.addCell(cell);
        cell.setPhrase(new Phrase("Birthday"));
        table.addCell(cell);

        SimpleDateFormat df = new SimpleDateFormat("dd.MM.yyyy");
        users.stream().forEach(user -> {
            table.addCell(user.getName());
            table.addCell(user.getEmail());
            table.addCell(df.format(user.getDayOfBirth()));
        });
        document.add(table);
    }
}
