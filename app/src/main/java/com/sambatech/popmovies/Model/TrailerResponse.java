package com.sambatech.popmovies.Model;

import java.util.List;

public class TrailerResponse {

    public List<Trailer> results;


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof TrailerResponse)) return false;

        TrailerResponse that = (TrailerResponse) o;

        return results != null ? results.equals(that.results) : that.results == null;
    }

    @Override
    public int hashCode() {
        return results != null ? results.hashCode() : 0;
    }

    @Override
    public String toString() {
        return "MoviesResponse{" +
                "results=" + results +
                '}';
    }
}
