package com.forestnewark.repository;


import com.forestnewark.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by forestnewark on 4/22/17.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
    List<User> findByEmail(String email);
    List<User> findByRank(String rank);
}
