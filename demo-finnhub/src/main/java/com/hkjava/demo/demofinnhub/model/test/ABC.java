package com.hkjava.demo.demofinnhub.model.test;

// package-private
class ABC { // can only viewed by classes under "test" package
  
  public static class XYZ {

  }

  public static void main(String[] args) {
    ABC abc = new ABC();
    XYZ xyz = new ABC.XYZ(); // access to ABC
  }
}
