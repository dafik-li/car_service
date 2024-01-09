package com.solvd.carservice.domain.parse;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateAdapter extends XmlAdapter<String, Date> {
    private static final String CUSTOM_FORMAT_STRING = "yyyy-MM-dd";

    @Override
    public Date unmarshal(String s) throws Exception {
        return new SimpleDateFormat(CUSTOM_FORMAT_STRING).parse(s);
    }
    @Override
    public String marshal(Date date) throws Exception {
        return new SimpleDateFormat(CUSTOM_FORMAT_STRING).format(date);
    }
}
