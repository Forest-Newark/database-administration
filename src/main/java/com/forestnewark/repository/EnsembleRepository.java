package com.forestnewark.repository;


import com.forestnewark.bean.Ensemble;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.QueryByExampleExecutor;

import java.util.List;

/**
 * Created by forestnewark on 4/24/17.
 */
public interface EnsembleRepository extends JpaRepository<Ensemble,Integer>,QueryByExampleExecutor<Ensemble> {
    List<Ensemble> findByName(String name);

}
