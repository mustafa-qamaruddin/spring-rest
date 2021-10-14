package com.qubits.demo.services;

import com.qubits.demo.models.Member;
import com.qubits.demo.models.Santa;
import com.qubits.demo.repositories.MembersRepository;
import com.qubits.demo.repositories.SantasRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Objects;

@Component
public class LogicService {
  @Autowired
  MembersRepository membersRepository;
  @Autowired
  SantasRepository santasRepository;

  public Member save(Member member) {
    Member newMember = membersRepository.save(member);
    var nextYear = Calendar.getInstance().get(Calendar.YEAR) + 1;
    assignGiver(nextYear, newMember);
    assignTaker(nextYear, newMember);
    return newMember;
  }

  private Santa assignGiver(int nextYear, Member newMember) {
    var giver = membersRepository.findNextGiver(nextYear - 3, newMember.getEmail());
    if (Objects.isNull(giver))
      return null;
    var santa = new Santa();
    santa.setGiver_email(giver.getEmail());
    santa.setTaker_email(newMember.getEmail());
    santa.setYear(nextYear);
    return santasRepository.save(santa);
  }

  private Santa assignTaker(int nextYear, Member newMember) {
    var taker = membersRepository.findNextTaker(nextYear - 3, newMember.getEmail());
    if (Objects.isNull(taker))
      return null;
    var santa = new Santa();
    santa.setTaker_email(taker.getEmail());
    santa.setGiver_email(newMember.getEmail());
    santa.setYear(nextYear);
    return santasRepository.save(santa);
  }

  public List<Santa> collect(int year) {
    List<Santa> santas = new ArrayList<>();
    membersRepository.findAll().forEach(member -> {
      santas.add(assignTaker(year, member));
      santas.add(assignGiver(year, member));
    });
    return santas;
  }
}
