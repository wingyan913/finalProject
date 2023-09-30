package com.hkjava.demo.demofinnhub;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;
import java.time.LocalDate;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.hkjava.demo.demofinnhub.model.dto.finnhub.resp.CompanyProfile2DTO;

public class DeserializationForRestTemplateTest {

  private static ObjectMapper objectMapper;

  @BeforeAll
  static void init() {
    objectMapper = new ObjectMapper();
    objectMapper.registerModule(new JavaTimeModule());
    objectMapper.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);
  }

  @Test
  void testDeserializationForRestTemplate() throws JsonProcessingException {
    // JSON -> Object
    CompanyProfile2DTO companyProfile = CompanyProfile2DTO.builder() //
        .companyName("APPL Company") //
        .country("US") //
        .currency("USD") //
        .estimateCurrency("USD") //
        .exchange("XYZ") //
        .finnhubIndustry("IJK") //
        .ipoDate(LocalDate.of(1988, 12, 31)) //
        .marketCap(3000.12) //
        .logo("/abc.png") //
        .phone("123456789") //
        .shareOutstanding(23.90) //
        .ticker("APPL") //
        .build();

    // test Serialization
    String mockedResponseInJson =
        objectMapper.writeValueAsString(companyProfile);
    System.out.println("json=" + mockedResponseInJson);
    // json={"country":"US","currency":"USD","estimateCurrency":"USD",
    // "exchange":"XYZ","finnhubIndustry":"IJK","logo":"/abc.png",
    // "phone":"123456789","shareOutstanding":23.9,"ticker":"APPL",
    // "weburl":null,"ipo":"1988-12-31","marketCapitalization":3000.12,
    // "name":"APPL Company"}
    // JsonNode jsonNode = objectMapper.readTree(mockedResponseInJson);
    // assertThat(jsonNode.path("country").asText(), is("US"));
    // assertThat(jsonNode.path("ipo").asText(), is("1988-12-31"));
    // assertThat(jsonNode.path("marketCapitalization").asDouble(), is(3000.12));

    // test Deserilaizationn (main code -> automation)
    CompanyProfile2DTO afterCompanyProfile =
        objectMapper.readValue(mockedResponseInJson, CompanyProfile2DTO.class);
        
    assertEquals(true,
        afterCompanyProfile.getIpoDate().equals(companyProfile.getIpoDate()));
    assertEquals(true,
        afterCompanyProfile.getMarketCap() == companyProfile.getMarketCap());
    assertEquals(true,
        afterCompanyProfile.getCountry().equals(companyProfile.getCountry()));
  }

  @Test
  void testSerializationForRestTemplate() throws JsonProcessingException {
    // readTree to prove the conversion is correct
    // JSON -> Object
    CompanyProfile2DTO companyProfile = CompanyProfile2DTO.builder() //
        .companyName("APPL Company") //
        .country("US") //
        .currency("USD") //
        .estimateCurrency("USD") //
        .exchange("XYZ") //
        .finnhubIndustry("IJK") //
        .ipoDate(LocalDate.of(1988, 12, 31)) //
        .marketCap(3000.12) //
        .logo("/abc.png") //
        .phone("123456789") //
        .shareOutstanding(23.90) //
        .ticker("APPL") //
        .build();

    // test Serialization
    String mockedResponseInJson =
        objectMapper.writeValueAsString(companyProfile);
    System.out.println("json=" + mockedResponseInJson);
    JsonNode jsonNode = objectMapper.readTree(mockedResponseInJson);
    assertThat(jsonNode.path("country").asText(), is("US"));
    assertThat(jsonNode.path("ipo").asText(), is("1988-12-31"));
    assertThat(jsonNode.path("marketCapitalization").asDouble(), is(3000.12));
  }

}
