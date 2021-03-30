package com.example.realmpractice;

import io.realm.RealmObject;

public class PlayersDetails extends RealmObject {

    String name;
    String club;
    int goals;

    public PlayersDetails() {
    }

    public PlayersDetails(String name, String club, int goals) {
        this.name = name;
        this.club = club;
        this.goals = goals;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getClub() {
        return club;
    }

    public void setClub(String club) {
        this.club = club;
    }

    public int getGoals() {
        return goals;
    }

    public void setGoals(int goals) {
        this.goals = goals;
    }
}

