package com.example.nano1.moviedb;

import java.util.Map;

/**
 * Created by nano1 on 5/23/2017.
 */

public enum GenreEnum {

    ACTION("Action", 28),
    ADVENTURE("Adventure", 12),
    ANIMATION("Animation", 16),
    COMEDY("Comedy", 35),
    CRIME("Crime", 80),
    DOCUMENTARY("Documentary", 99),
    DRAMA("Drama", 18),
    FAMILY("Family", 10751),
    FANTASY("Fantasy", 14),
    FOREIGN("Foreign", 10769),
    HISTORY("History", 36),
    HORROR("Horror", 27),
    MUSIC("Music", 10402),
    MYSTERY("Mystery", 9648),
    ROMANCE("Romance", 10749),
    SCIFI("Sci-Fi", 878),
    TVMOVIE("TV Movie", 10770),
    THRILLER("Thriller", 53),
    WAR("War", 10752),
    WESTERN("Western", 37);

    private String name;
    private int code;



    GenreEnum(String name, int code){
        this.name = name;
        this.code = code;
    }

    public static String find(int code){
        String name = null;
        for (GenreEnum genreEnum : GenreEnum.values()) {
            if (genreEnum.getCode() == code) {
                 name = genreEnum.getName();
            }
        }
        return name;
    }

    public String getName(){
        return name;
    }

    public int getCode(){
        return code;
    }

    public GenreEnum[] getValues(){
        return values();
    }
}
