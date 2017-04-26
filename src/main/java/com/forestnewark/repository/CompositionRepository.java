package com.forestnewark.repository;

import com.forestnewark.bean.Catagory;
import com.forestnewark.bean.Composition;
import com.forestnewark.bean.Ensemble;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * Created by forestnewark on 4/22/17.
 */
public interface CompositionRepository extends JpaRepository<Composition,Integer> {

    List<Composition> findByTitleIgnoreCaseContaining(String title);


    List<Composition> findAllByOrderByLibraryNumberAscCatagoryAsc();

    Page<Composition> findAllOrderByCatagoryName(Pageable pageable);
}
