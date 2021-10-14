package com.qubits.demo.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

@Data
@Entity
@IdClass(CompositeKey.class)
public class Santa {
  @Id
  String giver_email;
  @Id
  String taker_email;
  @Id
  int year;
}
