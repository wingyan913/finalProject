package com.hkjava.demo.demofinnhub.model.test2;

import com.hkjava.demo.demofinnhub.model.test.QWE;

public class IJK {
  public static void main(String[] args) {
    // cannot access ABC, ABC is package-private
    QWE qwe = new QWE(); // QWE is public class
  }
}
