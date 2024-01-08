package com.solvd.carservice.domain.parse;

import jakarta.xml.bind.annotation.adapters.XmlAdapter;
import java.sql.Date;

public class DateAdapter extends XmlAdapter<String, Date> {

    @Override
    public Date unmarshal(String s) throws Exception {
        return null;
    }
    @Override
    public String marshal(Date date) throws Exception {
        return null;
    }
}
