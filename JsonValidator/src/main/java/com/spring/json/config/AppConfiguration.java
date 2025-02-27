package com.spring.json.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.networknt.schema.JsonSchema;
import com.networknt.schema.JsonSchemaFactory;
import com.networknt.schema.SpecVersion;


@Configuration
public class AppConfiguration {
	
	private static final String SCHEMA_VALIDATION_FILE = "/validation.json";
	   
	@Bean
    public JsonSchema jsonSchema() {
        return JsonSchemaFactory
                .getInstance( SpecVersion.VersionFlag.V7 )
                .getSchema( getClass().getResourceAsStream( SCHEMA_VALIDATION_FILE ) );
    }

}
