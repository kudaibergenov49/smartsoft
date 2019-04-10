package com.example.ru.smartsoft.csv.reader.reader.repository;

import com.example.ru.smartsoft.csv.reader.reader.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<User,Long> {

    List<User> findAll();
}