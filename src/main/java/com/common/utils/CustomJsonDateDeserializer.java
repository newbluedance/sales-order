package com.common.utils;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

/**
 * @author lichunfeng
 * @create 2019-09-06 17:50
 **/
public class CustomJsonDateDeserializer extends JsonDeserializer<LocalDateTime> {

    @Override
    public LocalDateTime deserialize(JsonParser jp, DeserializationContext ctxt) throws IOException, JsonProcessingException {
        DateTimeFormatter format = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String source=StringUtils.trim(jp.getText());
        try {
            return LocalDateTime.parse(source,format);
        } catch (Exception e1) {
            format =DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'");
            try {
                return LocalDateTime.parse(source,format);
            } catch (Exception e2) {
                format = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                try {
                    LocalDate parse = LocalDate.parse(source, format);
                    return LocalDateTime.of(parse, LocalTime.MIN);
                } catch (Exception e3) {
                    throw new RuntimeException(e3);
                }
            }
        }
    }
}
