package com.example.ru.smartsoft.csv.reader.repository;

import com.example.ru.smartsoft.csv.reader.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User, Long> {
    @Query(value = " select u.formid from usr u group by u.formid order by count(u.formid) desc limit 5 \n ", nativeQuery = true)
    List<String> top5Forms();

    @Query(value = " select count(u.formid) from usr u where u.formid = :formid \n ", nativeQuery = true)
    Integer formCount(@Param("formid") String formid);

    @Query(value = "select * from  usr where ymdh >= DATE_SUB(NOW(),INTERVAL 15500 HOUR)", nativeQuery = true)
    List<User> lastHourActivities();

    @Query(value = "select distinct * from usr u where id = (select min(id) from usr us where u.ssoid = us.ssoid) ", nativeQuery = true)
    List<User> usersLastActivities();
}