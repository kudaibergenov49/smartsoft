package com.example.ru.smartsoft.csv.reader.repository;

import com.example.ru.smartsoft.csv.reader.model.FormCount;
import com.example.ru.smartsoft.csv.reader.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    @Query(value = " select u.formid from usr u group by u.formid order by count(u.formid) desc limit 5 \n ",nativeQuery = true)
    List<String> findTop5Forms();

    @Query(value = " select count(u.formid) from usr u where u.formid = :formid \n ",nativeQuery = true)
    Integer findFormCount(@Param("formid") String formid);

    @Query(value = "select *\n" +
            "from  usr\n" +
            "where ymdh >= DATE_SUB(NOW(),INTERVAL 15500 HOUR)",nativeQuery = true)
    Page<User> lastHourUsers(Pageable pageable);
}