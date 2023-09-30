package com.hkjava.demo.demofinnhub.annotation;

import java.util.Objects;
import com.hkjava.demo.demofinnhub.config.AppStartRunner;
import com.hkjava.demo.demofinnhub.model.dto.web.req.SymbolReqDTO;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class SymbolValidator
    implements ConstraintValidator<SymbolCheck, SymbolReqDTO> {

  @Override
  public boolean isValid(SymbolReqDTO symbol, ConstraintValidatorContext context) {
    return Objects.nonNull(symbol.getSymbol())
        && AppStartRunner.availableStockList.contains(symbol.getSymbol());
  }

}
