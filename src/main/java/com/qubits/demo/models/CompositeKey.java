package com.qubits.demo.models;

import lombok.Data;

import java.io.Serializable;

@Data
public class CompositeKey implements Serializable {
  private String giver_email;
  private String taker_email;
  private int year;
}