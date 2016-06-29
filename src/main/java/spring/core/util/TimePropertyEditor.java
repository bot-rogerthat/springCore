package spring.core.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class TimePropertyEditor extends PropertyEditorSupport {
    private static final Logger log = LoggerFactory.getLogger(TimePropertyEditor.class);
    public static final String DEFAULT_BATCH_PATTERN = "hh:mm";
    private final SimpleDateFormat sdf;

    public TimePropertyEditor() {
        this.sdf = new SimpleDateFormat(TimePropertyEditor.DEFAULT_BATCH_PATTERN);
    }

    public TimePropertyEditor(String pattern) {
        this.sdf = new SimpleDateFormat(pattern);
    }

    @Override
    public void setAsText(String text) throws IllegalArgumentException {
        try {
            setValue(new Time(this.sdf.parse(text).getTime()));
        } catch (ParseException ex) {
            log.error("Could not parse time", ex);
        }
    }

    @Override
    public String getAsText() {
        Time value = (Time) getValue();
        return (value != null ? this.sdf.format(value) : "");
    }

}
