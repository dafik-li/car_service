package com.solvd.carservice.domain.parse;

import com.fasterxml.jackson.core.JacksonException;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class JsonDateAdapter extends JsonDeserializer<Date> {
    @Override
    public Date deserialize(JsonParser jsonParser, DeserializationContext deserializationContext) throws IOException, JacksonException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        String dateAsString = jsonParser.getValueAsString();
        ObjectMapper mapper = new ObjectMapper();
        mapper.setDateFormat(dateFormat);
        try {
            return dateFormat.parse(dateAsString);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
}
