package com.example.ru.smartsoft.csv.reader.repository;

import com.example.ru.smartsoft.csv.reader.model.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.web.PageableDefault;

import java.util.List;

public interface UserRepository extends PagingAndSortingRepository<User, Long> {
    @Query(value = " select u.formid from usr u group by u.formid order by count(u.formid) desc limit 5 \n ", nativeQuery = true)
    List<String> top5Forms();

    @Query(value = " select count(u.formid) from usr u where u.formid = :formid \n ", nativeQuery = true)
    Integer formCount(@Param("formid") String formid);

    @Query(value = "select * from  usr where ymdh >= DATE_SUB(NOW(),INTERVAL 15500 HOUR)", nativeQuery = true)
    Page<User> lastHourActivities(@PageableDefault(size = 10) Pageable pageable);

    @Query(value = "select * from usr u where ts = (select max(ts) from usr us where u.ssoid = us.ssoid) ", nativeQuery = true)
    Page<User> usersLastActivities(@PageableDefault(size = 10) Pageable pageable,@Param("pageNumber") int pageNumber);
}
