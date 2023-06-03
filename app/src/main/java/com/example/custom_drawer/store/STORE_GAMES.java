package com.example.custom_drawer.store;

public class STORE_GAMES {
    String id;
    String name;
    String price;
    String rating;
    String rating_count;
    String genres;
    String modes;
    String release_dates;
    String summary;
    String cover_image_id;
    String video_id;
    String screenshots_image_id;
//    public STORE_GAMES(String name){
//        this.name=name;
//    }


    public STORE_GAMES(String id, String name, String price, String rating, String rating_count, String genres,String modes, String release_dates, String summary, String cover_image_id, String video_id, String screenshots_image_id) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.rating = rating;
        this.rating_count = rating_count;
        this.genres = genres;
        this.modes = modes;
        this.release_dates = release_dates;
        this.summary = summary;
        this.cover_image_id = cover_image_id;
        this.video_id = video_id;
        this.screenshots_image_id = screenshots_image_id;
    }
}
