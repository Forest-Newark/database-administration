package com.forestnewark.service;

import com.forestnewark.bean.*;
import com.forestnewark.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class LibraryService {

    private final
    ActionItemRepository actionItemRepository;

    private final
    CatagoryRepository catagoryRepository;

    private final
    CompositionRepository compositionRepository;

    private final
    EnsembleRepository ensembleRepository;

    private final
    MusicianRepository musicianRepository;

    private final
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

    public Page<Composition> findAllCompositionsPageable(Integer page, int itemsPerPage, Sort sort) {

       return compositionRepository.findAll(new PageRequest(page-1, itemsPerPage, sort));
    }

    public List<User> findAllUsers() {

        return userRepository.findAll();
    }

    public List<ActionItem> findAllActionItems() {
        return actionItemRepository.findAll();
    }

    public List<User> findUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getOneUser(Integer id) {

       return userRepository.getOne(id);
    }

    public void deleteUser(User user) {

        userRepository.delete(user);
    }

    public void saveActionItem(ActionItem actionItem) {
        actionItemRepository.save(actionItem);
    }

    public ActionItem getOneActionItem(Integer id) {
      return  actionItemRepository.getOne(id);

    }


    public void deleteActionItem(ActionItem actionItem) {

        actionItemRepository.delete(actionItem);
    }

    public Composition getOneComposition(Integer id) {
        return compositionRepository.getOne(id);
    }

    public void deleteComposition(Composition composition) {
        compositionRepository.delete(composition);
    }

    public Integer compositionCount(){

        return compositionRepository.findAll().size();
    }
}
