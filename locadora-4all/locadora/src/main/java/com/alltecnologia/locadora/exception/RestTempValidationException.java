/*
 * CABAL BRASIL PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 * Copyright (c) 2017, Cabal Brasil and/or its affiliates. All rights reserved.
 */
package com.alltecnologia.locadora.exception;

public class RestTempValidationException extends RuntimeException {

  private static final long serialVersionUID = 1L;

  public RestTempValidationException(String error) {
    super(error);
  }

}
