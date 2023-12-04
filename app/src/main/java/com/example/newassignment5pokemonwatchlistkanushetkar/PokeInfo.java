package com.example.newassignment5pokemonwatchlistkanushetkar;

import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PokeInfo {
    private String pokemonName; // : "AAPL",
    private int iD; //" : "Apple Inc.",
    private Float weight; //" : 120.96000000,
    private Float height; //" : 0.07000000,
    private Float baseEP; //" : 0.08000000,
    private List <String> move = new ArrayList<>();
    private List <String> ability = new ArrayList<>();
    //" : 110.89000000,



    public PokeInfo(String pokemonName, int iD, Float weight, Float height, Float baseEP, List<String> move, List<String> ability) {
        this.pokemonName = pokemonName;
        this.iD = iD;
        this.weight = weight;
        this.height = height;
        this.baseEP = baseEP;
        this.move = move;
        this.ability = ability;
    }

    public PokeInfo(String name, int id, TextView weight, TextView height, TextView baseEP, TextView move, TextView ability) {
    }

    public PokeInfo(String name, int id) {

    }

    public PokeInfo(String name, int id, String weight, String height, String baseEP, String move, String ability) {
    }


    public String pokemonName() {
        return pokemonName;
    }

    public int iD() {
        return iD;
    }

    public Float weight() {
        return weight;
    }

    public Float height() {
        return height;
    }

    public Float baseEP() {
        return baseEP;
    }

    public List<String> move() {
        return move;
    }

    public List<String> ability() {
        return ability;
    }


    public String getName() {
        return pokemonName;
    }

    public int getId() {
        return iD;
    }
}
