package com.forestnewark.converter;


import com.forestnewark.bean.Catagory;
import com.forestnewark.repository.CatagoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * Created by forestnewark on 4/24/17.
 */
@Component
public class CatagoryConverter implements Converter<String,Catagory> {

    final
    CatagoryRepository catagoryRepository;

    @Autowired
    public CatagoryConverter(CatagoryRepository catagoryRepository) {
        this.catagoryRepository = catagoryRepository;
    }

    @Override
    public Catagory convert(String s) {
        Example<Catagory> example = Example.of(new Catagory(s));


        return catagoryRepository.findAll(example).get(0);
    }
}
