package com.forestnewark.repository;

import com.forestnewark.bean.Catagory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;


/**
 * Created by forestnewark on 4/24/17.
 */
public interface CatagoryRepository extends JpaRepository<Catagory,Integer>,QueryByExampleExecutor<Catagory> {

    List<Catagory> findByName(String name);

}
