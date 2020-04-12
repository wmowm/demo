package com.example.demo.service;

import com.example.demo.dto.UserDto;
import com.example.demo.entity.User;
import com.example.demo.repository.BaseRepository;
import org.springframework.data.domain.Page;

public interface UserService extends BaseRepository<User> {

    Page<User> findAll(UserDto dto);

}