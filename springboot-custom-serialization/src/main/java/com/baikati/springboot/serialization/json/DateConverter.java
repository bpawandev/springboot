package com.baikati.springboot.serialization.json;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

import org.springframework.boot.jackson.JsonComponent;

import com.baikati.springboot.serialization.model.Employee;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

@JsonComponent
public class DateConverter {
	private static final String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
	private static final SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMAT);

	public static class Serialize extends JsonSerializer<Instant> {

		@Override
		public void serialize(Instant value, JsonGenerator generator, SerializerProvider serializers)
				throws IOException {
			try {
				if (value == null) {
					generator.writeNull();
				} else {
					generator.writeString(
							DateTimeFormatter.ofPattern(DATE_FORMAT).withZone(ZoneId.systemDefault()).format(value));
				}
			} catch (Exception e) {
				e.printStackTrace();
			}

		}

	}

	public static class Deserialize extends JsonDeserializer<Instant> {

		@Override
		public Instant deserialize(JsonParser parser, DeserializationContext context)
				throws IOException, JsonProcessingException {
			try {
				String dateAsString = parser.getText();
				if(dateAsString==null) {
					return null;
				}else {
					return Instant.ofEpochMilli(sdf1.parse(dateAsString).getTime()); 
				}
			}catch(Exception e) {
				e.printStackTrace();
			}
			return null;
		}

	}

}
