package com.qrcodeauth.provider;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.MapperFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;

import java.text.SimpleDateFormat;

public class JacksonObjectMapper extends ObjectMapper {

	public JacksonObjectMapper() {
		super();
		super.configure(SerializationFeature.WRAP_ROOT_VALUE, true);
		super.configure(DeserializationFeature.UNWRAP_ROOT_VALUE, true);
		super.configure(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY, true);
		super.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
		super.enable(SerializationFeature.INDENT_OUTPUT);
		super.enable(MapperFeature.USE_WRAPPER_NAME_AS_PROPERTY_NAME);
		super.enable(SerializationFeature.WRITE_EMPTY_JSON_ARRAYS);
		super.setSerializationInclusion(JsonInclude.Include.ALWAYS);
		super.setDateFormat(new SimpleDateFormat("yyyy-MM-dd"));
	}
}
