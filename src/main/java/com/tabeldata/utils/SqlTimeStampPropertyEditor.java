package com.tabeldata.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.beans.PropertyEditorSupport;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

public class SqlTimeStampPropertyEditor extends PropertyEditorSupport {

    private static final Logger console = LoggerFactory.getLogger(SqlTimeStampPropertyEditor.class);
    private SimpleDateFormat formatter = null;

    public static final String DEFAULT_BATCH_PATTERN = "yyyy-MM-dd HH:mm:ss";
    private final SimpleDateFormat sdf;

    /**
     * uses default pattern dd/MM/yyyy for date parsing.
     */
    public SqlTimeStampPropertyEditor() {
        this.sdf = new SimpleDateFormat(SqlTimeStampPropertyEditor.DEFAULT_BATCH_PATTERN);
    }

    @Deprecated
    public SqlTimeStampPropertyEditor(String pattern) {
        this.sdf = new SimpleDateFormat(pattern);
    }

    @Override
    public void setAsText(String text) {
        try {
//            setValue(formatter.parse(text));
            setValue(new Timestamp(this.sdf.parse(text).getTime()));
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    @Override
    public String getAsText() {
        Timestamp value = (Timestamp) getValue();
//        return (value != null ? value.toString() : "");
        return (value != null ? this.sdf.format(value) : "");
    }
}
