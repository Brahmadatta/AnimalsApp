package com.example.animalsapp.model;

public class AnimalModel {

    public String name;
    public Taxonomy mTaxonomy;
    public String location;
    public Speed mSpeed;
    public String diet;
    public String lifeSpan;
    public String imageUrl;

    public AnimalModel(String name){
        this.name = name;
    }

}

class Taxonomy{
    String kingdom;
    String order;
    String family;
}

class Speed{
    String metric;
    String imperial;
}


