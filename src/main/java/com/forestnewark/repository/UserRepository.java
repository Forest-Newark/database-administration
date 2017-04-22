package com.forestnewark.repository;


import com.forestnewark.bean.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by forestnewark on 4/22/17.
 */
public interface UserRepository extends JpaRepository<User,Integer> {
}
