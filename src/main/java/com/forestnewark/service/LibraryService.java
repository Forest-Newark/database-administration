package com.forestnewark.service;

import com.forestnewark.bean.Catagory;
import com.forestnewark.bean.Composition;
import com.forestnewark.bean.Ensemble;
import com.forestnewark.bean.Musician;
import com.forestnewark.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by forestnewark on 4/25/17.
 */
@Component
public class LibraryService {

    final
    ActionItemRepository actionItemRepository;

    final
    CatagoryRepository catagoryRepository;

    final
    CompositionRepository compositionRepository;

    final
    EnsembleRepository ensembleRepository;

    final
    MusicianRepository musicianRepository;

    final
    UserRepository userRepository;


    @Autowired
    public LibraryService(ActionItemRepository actionItemRepository, CatagoryRepository catagoryRepository, CompositionRepository compositionRepository, EnsembleRepository ensembleRepository, MusicianRepository musicianRepository, UserRepository userRepository) {
        this.actionItemRepository = actionItemRepository;
        this.catagoryRepository = catagoryRepository;
        this.compositionRepository = compositionRepository;
        this.ensembleRepository = ensembleRepository;
        this.musicianRepository = musicianRepository;
        this.userRepository = userRepository;
    }


    public void saveComposition(Composition composition){

        //Check and Set Catagory
        if(catagoryRepository.findByName(composition.getCatagory().getName()).size() == 1){

            Catagory catagory= catagoryRepository.findByName(composition.getCatagory().getName()).get(0);

            composition.setCatagory(catagory);
        }

        //Check and Set Ensemble
        if(ensembleRepository.findByName(composition.getCatagory().getName()).size() ==1){
            Ensemble ensemble = ensembleRepository.findByName(composition.getEnsemble().getName()).get(0);
            composition.setEnsemble(ensemble);
        }

        //Check and Set Composer
        if(musicianRepository.findByName(composition.getComposer().getName()).size() ==1){
            Musician composer = musicianRepository.findByName(composition.getComposer().getName()).get(0);
            composition.setComposer(composer);
        }

        //Check and Set Arranger
        if(musicianRepository.findByName(composition.getArranger().getName()).size() == 1){
            Musician arranger = musicianRepository.findByName(composition.getArranger().getName()).get(0);
            composition.setArranger(arranger);
        }

        //Save Composition
        compositionRepository.save(composition);

    }



    public List<Composition> searchCompositions(String keyword, String catagory, String ensemble){
//        if(!catagory.equals("")){
//            Catagory searchCatagory = catagoryRepository.findByName(catagory).get(0);
//        }
//
//       Ensemble searchEnsemble = ensembleRepository.findByName(ensemble).get(0);

        if(keyword.equals("")){
            return compositionRepository.findAllByOrderByLibraryNumberAscCatagoryAsc();
        }else {
            return compositionRepository.findByTitleIgnoreCaseContaining(keyword);
        }


    }








}
