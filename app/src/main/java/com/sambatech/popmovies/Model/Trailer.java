package com.sambatech.popmovies.Model;

import com.squareup.moshi.Json;

import java.util.List;

public class Trailer {
    public String id;
    public String name;
    public String type;
    public String key;
    public String site;
    public Integer size;
    @Json(name = "iso_639_1")
    public String iso_639_1;

    @Json(name = "iso_3166_1")
    public String iso_3166_1;

    @Override
    public String toString() {
        return "Trailer{" +
                "id=" + id + '\'' +
                ", name='" + name + '\'' +
                ", type='" + type + '\'' +
                ", key=" + key + '\'' +
                ", site=" + site + '\'' +
                ", size='" + size + '\'' +
                ", iso_639_1='" + iso_639_1 + '\'' +
                ", iso_3166_1='" + iso_3166_1 + '\'' +
                '}';
    }
}
