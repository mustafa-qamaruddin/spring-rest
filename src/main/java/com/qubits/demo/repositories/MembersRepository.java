package com.qubits.demo.repositories;

import com.qubits.demo.models.Member;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MembersRepository extends CrudRepository<Member, String> {
  @Query(
      value = "select m.* \n" +
          "from member m\n" +
          "left join santa s\n" +
          "on m.email = s.giver_email\n" +
          "where s.giver_email is null\n" +
          "and (s.year is null or s.year > ?1)\n" +
          "and m.email <> ?2\n" +
          "order by random()\n" +
          "limit 1;",
      countQuery = "select count(m.email)\n" +
          "from member m\n" +
          "left join santa s\n" +
          "on m.email = s.giver_email\n" +
          "where s.giver_email is null\n" +
          "and (s.year is null or s.year > ?1)\n" +
          "and m.email <> ?2\n;",
      nativeQuery = true)
  Member findNextGiver(int year, String forbidden);

  @Query(
      value = "select m.* \n" +
          "from member m\n" +
          "left join santa s\n" +
          "on m.email = s.taker_email\n" +
          "where s.taker_email is null\n" +
          "and (s.year is null or s.year > ?1)\n" +
          "and m.email <> ?2\n" +
          "order by random()\n" +
          "limit 1;",
      countQuery = "select count(m.email)\n" +
          "from member m\n" +
          "left join santa s\n" +
          "on m.email = s.taker_email\n" +
          "where s.taker_email is null\n" +
          "and (s.year is null or s.year > ?1)\n" +
          "and m.email <> ?2\n;",
      nativeQuery = true)
  Member findNextTaker(int year, String forbidden);

}
