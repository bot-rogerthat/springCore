package spring.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimestampPropertyEditor extends PropertyEditorSupport {
    private static final Logger log = LoggerFactory.getLogger(TimestampPropertyEditor.class);
    public static final String DEFAULT_BATCH_PATTERN = "yyyy-MM-dd";
    private final SimpleDateFormat sdf;

    public TimestampPropertyEditor() {
        this.sdf = new SimpleDateFormat(TimestampPropertyEditor.DEFAULT_BATCH_PATTERN);
    }

    public TimestampPropertyEditor(String pattern) {
        this.sdf = new SimpleDateFormat(pattern);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(new Timestamp(this.sdf.parse(text).getTime()));
        } catch (ParseException ex) {
            log.error("Could not parse date", ex);
        }
    }

    @Override
    public String getAsText() {
        Timestamp value = (Timestamp) getValue();
        return (value != null ? this.sdf.format(value) : "");
    }

}
