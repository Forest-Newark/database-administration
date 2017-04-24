package com.forestnewark.converter;


import com.forestnewark.bean.Musician;
import com.forestnewark.repository.MusicianRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.data.domain.Example;
import org.springframework.stereotype.Component;

/**
 * Created by forestnewark on 4/24/17.
 */
@Component
public class MusicianConverter implements Converter<String,Musician> {

    final
    MusicianRepository musicianRepository;

    @Autowired
    public MusicianConverter(MusicianRepository musicianRepositoryl) {
        this.musicianRepository = musicianRepositoryl;
    }

    @Override
    public Musician convert(String s) {
       if(musicianRepository.findByName(s).size() == 0){
           musicianRepository.save(new Musician(s));
       }
        Example<Musician> example = Example.of(new Musician(s));
        return musicianRepository.findAll(example).get(0);

    }
}
