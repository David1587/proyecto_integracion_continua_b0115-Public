package com.models;

import java.util.List;

public class DeveloperInfo {
    private String name;
    private List<GameInfo> topGames;
    
    public DeveloperInfo(String name, List<GameInfo> topGames) {
        this.name = name;
        this.topGames = topGames;
    }
    
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public List<GameInfo> getTopGames() {
        return topGames;
    }
    public void setTopGames(List<GameInfo> topGames) {
        this.topGames = topGames;
    }
}
