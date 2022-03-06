package river;

import java.awt.*;
import java.util.Map;

public abstract class AbstractGameEngine implements GameEngine{

    protected Map<Item, GameObject> gamePlayers;
    protected Location boatLocation;
    protected void setGameObjects(Map<Item, GameObject> gamePlayers) {
        this.gamePlayers = gamePlayers;
    }

    public abstract int numberOfItems();

    @Override
    public Color getItemColor(Item item) {
        return gamePlayers.get(item).getColor();
    }

    @Override
    public String getItemLabel(Item id) {
        return gamePlayers.get(id).getLabel();
    }

    @Override
    public Location getItemLocation(Item item) {
        return gamePlayers.get(item).getItemLocation();
    }

    @Override
    public void setItemLocation(Item item, Location location) {
        gamePlayers.get(item).setLocation(location);
    }

    @Override
    public Location getBoatLocation() {
        return boatLocation;
    }

    @Override
    public void unloadBoat(Item id) {
        if (gamePlayers.get(id).getItemLocation() == Location.BOAT) {
            gamePlayers.get(id).setLocation(boatLocation);
        }
    }

    @Override
    public void loadBoat(Item item) {
        if (gamePlayers.get(item).getItemLocation() == boatLocation) {
            Integer itemsOnBoat = 0;
            for (Item val : Item.values()) {
                if (val != item && gamePlayers.get(val).getItemLocation() == Location.BOAT) {
                    itemsOnBoat += 1;
                }
            }
            if (itemsOnBoat < 2)
                gamePlayers.get(item).setLocation(Location.BOAT);
        }
    }

    @Override
    public void rowBoat() {
        Boolean anyoneOnBoat = false;
        for(Item item : Item.values()){
            if(gamePlayers.get(item).getItemLocation() == Location.BOAT){
                anyoneOnBoat = true;
                break;
            }
        }
        if(anyoneOnBoat)
            boatLocation = boatLocation == Location.START ? Location.FINISH : Location.START;
    }

    @Override
    public boolean gameIsWon() {
        return gamePlayers.values().stream().allMatch(x -> x.getItemLocation() == Location.FINISH);
    }

    @Override
    public boolean gameIsLost() {
        return false;
    }

    @Override
    public void resetGame() {
        gamePlayers.forEach((item, value) -> {
            value.setLocation(Location.START);
        });
        boatLocation = Location.START;
    }

//    public abstract int numberOfItems();


}
