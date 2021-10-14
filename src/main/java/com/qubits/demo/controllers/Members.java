package com.qubits.demo.controllers;

import com.qubits.demo.models.Member;
import com.qubits.demo.models.Santa;
import com.qubits.demo.services.LogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("members")
public class Members {
  @Autowired
  LogicService logicService;

  @PostMapping(
      path = "/create",
      consumes = MediaType.APPLICATION_JSON_VALUE,
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<Member> create(@RequestBody Member member) {
    return new ResponseEntity<Member>(logicService.save(member), HttpStatus.CREATED);
  }

  @GetMapping(
      path = "/collection",
      produces = MediaType.APPLICATION_JSON_VALUE)
  public ResponseEntity<List<Santa>> get(@PathVariable int year) {
    return new ResponseEntity<List<Santa>>(logicService.collect(year), HttpStatus.CREATED);
  }
}
