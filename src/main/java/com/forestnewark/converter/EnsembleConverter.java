package com.forestnewark.converter;


import com.forestnewark.bean.Ensemble;
import com.forestnewark.repository.EnsembleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

@Component
public class EnsembleConverter implements Converter<String,Ensemble> {

    final
    EnsembleRepository ensembleRepository;

    @Autowired
    public EnsembleConverter(EnsembleRepository ensembleRepository) {
        this.ensembleRepository = ensembleRepository;
    }

    @Override
    public Ensemble convert(String s) {
        Example<Ensemble> example = Example.of(new Ensemble(s));
        return ensembleRepository.findAll(example).get(0);
    }
}
