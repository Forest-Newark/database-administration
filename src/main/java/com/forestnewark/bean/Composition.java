package com.forestnewark.bean;

import javax.persistence.*;

/**
 * Created by forestnewark on 4/22/17.
 */
@Entity
@Table(name = "composition")
public class Composition {


    @Id
    @GeneratedValue
    private Integer id;

    @ManyToOne
    private Catagory catagory;



    private Integer libraryNumber;
    private String title;

    private String composer;
    private String arranger;

    @ManyToOne
    private Ensemble ensemble;

    private Integer copyright;
    private String notes;


    public Composition(){}

    public Composition(Catagory catagory, Integer libraryNumber, String title, String composer, String arranger, Ensemble ensemble, Integer copyright, String notes) {
        this.catagory = catagory;
        this.libraryNumber = libraryNumber;
        this.title = title;
        this.composer = composer;
        this.arranger = arranger;
        this.ensemble = ensemble;
        this.copyright = copyright;
        this.notes = notes;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Catagory getCatagory() {
        return catagory;
    }

    public void setCatagory(Catagory catagory) {
        this.catagory = catagory;
    }

    public Integer getLibraryNumber() {
        return libraryNumber;
    }

    public void setLibraryNumber(Integer libraryNumber) {
        this.libraryNumber = libraryNumber;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getArranger() {
        return arranger;
    }

    public void setArranger(String arranger) {
        this.arranger = arranger;
    }

    public Ensemble getEnsemble() {
        return ensemble;
    }

    public void setEnsemble(Ensemble ensemble) {
        this.ensemble = ensemble;
    }

    public Integer getCopyright() {
        return copyright;
    }

    public void setCopyright(Integer copyright) {
        this.copyright = copyright;
    }

    public String getNotes() {
        return notes;
    }

    public void setNotes(String notes) {
        this.notes = notes;
    }
}
