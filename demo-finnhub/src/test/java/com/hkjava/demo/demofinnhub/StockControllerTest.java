package com.hkjava.demo.demofinnhub;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import com.hkjava.demo.demofinnhub.controller.impl.StockController;
import com.hkjava.demo.demofinnhub.model.dto.web.resp.CompanyProfileDTO;
import com.hkjava.demo.demofinnhub.model.dto.web.resp.StockDTO;
import com.hkjava.demo.demofinnhub.service.WebStockService;

@WebMvcTest(StockController.class)
@ActiveProfiles("test")
public class StockControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private WebStockService webStockService;

  @Test
  void testGetUsers() throws Exception {

    CompanyProfileDTO profileDTO =
        new CompanyProfileDTO("HK", "Orange Inc", LocalDate.of(1988, 12, 31),
            "https://orange.com/abc.png", 15050.99, "USD");
    StockDTO mockDTO = StockDTO.builder() //
        .companyProfile(profileDTO) //
        .currentPrice(199.99).dayHigh(179.22).dayLow(167.99).dayOpen(175.44)
        .prevDayClose(169.99).build();

    Mockito.when(webStockService.stockInfo("AAPL")).thenReturn(mockDTO);

    mockMvc.perform(get("/api/v1/stock").param("symbol", "AAPL")) //
        .andExpect(status().isOk()) // HTTP 200
        .andExpect(content().contentType(MediaType.APPLICATION_JSON))
        .andExpect(jsonPath("$.code").value(20000)) //
        .andExpect(jsonPath("$.message").value("OK")) //
        .andExpect(jsonPath("$.data.companyProfile.country").value("HK")) //
        .andExpect(
            jsonPath("$.data.companyProfile.companyName").value("Orange Inc")) //
        .andExpect(jsonPath("$.data.currentPrice").value(199.99)) //
        .andExpect(jsonPath("$.data.dayOpen").value(175.44)) //
        .andDo(print());
  }

}
