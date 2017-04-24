package com.forestnewark.repository;


import com.forestnewark.bean.Musician;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * Created by forestnewark on 4/24/17.
 */
public interface MusicianRepository extends JpaRepository<Musician,Integer>,QueryByExampleExecutor<Musician> {
    List<Musician> findByName(String name);

}
