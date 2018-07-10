package com.minlia.module.pretend;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ser.impl.SimpleFilterProvider;
import com.github.bohnman.squiggly.context.provider.SimpleSquigglyContextProvider;
import com.github.bohnman.squiggly.filter.SquigglyPropertyFilter;
import com.github.bohnman.squiggly.filter.SquigglyPropertyFilterMixin;
import com.github.bohnman.squiggly.parser.SquigglyParser;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

/**
 * Minlia Pretend Auto Configuration
 */

@Configuration
@ComponentScan
public class PretendAutoConfiguration {

  @Bean
  public ObjectMapper objectMapper() {
    ObjectMapper objectMapper = new ObjectMapper();
    objectMapper.addMixIn(Object.class, SquigglyPropertyFilterMixin.class);
    SquigglyPropertyFilter propertyFilter = new SquigglyPropertyFilter(
        new SimpleSquigglyContextProvider(new SquigglyParser(), "**"));
    objectMapper.setFilterProvider(
        new SimpleFilterProvider().addFilter(SquigglyPropertyFilter.FILTER_ID, propertyFilter));
    return objectMapper;
  }

}