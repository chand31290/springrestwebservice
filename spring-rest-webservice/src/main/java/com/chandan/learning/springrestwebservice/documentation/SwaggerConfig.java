package com.chandan.learning.springrestwebservice.documentation;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.service.VendorExtension;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class SwaggerConfig
{

	public static final Contact DEFAULT_CONTACT = new Contact("Chandan Singh",
			"https://www.linkedin.com/in/chandan-singh-431a2340/", "chand312902@gmail.com");

	@SuppressWarnings("rawtypes")
	public static final ApiInfo API_INFO = new ApiInfo("Spring Rest WebService Documentation",
			"Spring Rest WebService Documentation Descriptio", "1.0", "urn:tos", DEFAULT_CONTACT, "Apache 2.0",
			"http://www.apache.org/licenses/LICENSE-2.0", new ArrayList<VendorExtension>());

	private static final Set<String> PRODUCES_AND_CONSUMES = new HashSet<>(Arrays.asList("application/json"));

	@Bean
	public Docket api()
	{
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(API_INFO).produces(PRODUCES_AND_CONSUMES)
				.consumes(PRODUCES_AND_CONSUMES);
	}
}
