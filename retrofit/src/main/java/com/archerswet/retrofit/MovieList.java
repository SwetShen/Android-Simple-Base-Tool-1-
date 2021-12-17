package com.archerswet.retrofit;

import java.util.List;

public class MovieList {

    private List<Movie> list;

    public List<Movie> getList() {
        return list;
    }

    public void setList(List<Movie> list) {
        this.list = list;
    }

    @Override
    public String toString() {
        return "MovieList{" +
                "list=" + list +
                '}';
    }
}
