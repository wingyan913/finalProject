package com.hkjava.demo.demofinnhub.exception;

import com.hkjava.demo.demofinnhub.infra.BusinessException;
import com.hkjava.demo.demofinnhub.infra.Code;

public class FinnhubException extends BusinessException {

  public FinnhubException(Code code) {
    super(code);
  }

}
