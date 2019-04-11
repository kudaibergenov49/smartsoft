package com.example.ru.smartsoft.csv.reader.repository;

import com.example.ru.smartsoft.csv.reader.model.FormCount;
import com.example.ru.smartsoft.csv.reader.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {
    /*@Query(value = " \n " +
            " select new com.example.ru.smatrsoft.csv.reader.FormCount(case when u.formid is null or u.formid = \"\" then 'hello' else  u.formid end, count(u.formid)) from usr u group by u.formid order by count(u.formid) desc limit 5 \n ",nativeQuery = true)
    List<FormCount> findForms();*/

    @Query(value = "select *\n" +
            "from  usr\n" +
            "where ymdh >= DATE_SUB(NOW(),INTERVAL 15500 HOUR)",nativeQuery = true)
    List<User> lastHourUsers();
}