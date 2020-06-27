// Copyright 2019 Choice Hotels International
package com.members.api.web.config;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.client.RestTemplate;

@Configuration
@EnableRetry
@EnableTransactionManagement
@EnableCaching

@PropertySource(value = "classpath:/${ENVIRONMENT:qa}.properties")
//@Import(value = {JSchConfig.class})
@EnableAsync
@EnableScheduling
public class AppConfig {

  public static final int MAX_FAILURES = 3;

  /**
   * Initialize the rest template.
   *
   * @return the rest template
   */
  @Bean
  public RestTemplate restTemplate() {
    return new RestTemplate();
  }

  @Bean(name = "hrhDateTimeFormatter")
  public DateTimeFormatter hrhDateTimeFormatter() {
    return DateTimeFormatter.ISO_OFFSET_DATE_TIME;
  }

  /**
   * Creates an object mapper for use throughout the application.
   *
   * @return the object mapper
   */
  @Bean
  public ObjectMapper objectMapper() {
    return new ObjectMapper().setSerializationInclusion(JsonInclude.Include.NON_NULL)
        .disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS)
        // Disable fail on unknown properties so that messages from get can be used with changes to do posts.
        .disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES)
        //.registerModule(new JavaTimeModule());
        .findAndRegisterModules();
  }

  @Bean
  public Calendar calendar() {
    final Calendar calendar = Calendar.getInstance();
    return calendar;
  }
}
