package river;

import java.awt.*;
import java.util.HashMap;

public class FarmerGameEngine extends AbstractGameEngine  {

    public static final Item BEANS = Item.ITEM_0;
    public static final Item GOOSE = Item.ITEM_1;
    public static final Item WOLF = Item.ITEM_2;
    public static final Item FARMER = Item.ITEM_3;

    public FarmerGameEngine() {
        setGameObjects(new HashMap<>(){{
            put(WOLF, new GameObject("W", Location.START, Color.CYAN));
            put(GOOSE, new GameObject("G", Location.START, Color.CYAN));
            put(BEANS, new GameObject("B", Location.START, Color.CYAN));
            put(FARMER, new GameObject("F", Location.START, Color.PINK));
        }});
        boatLocation = Location.START;
    }

    @Override
    public void loadBoat(Item item) {
        if (gamePlayers.get(item).getItemLocation() == boatLocation) {

            if(gamePlayers.get(item).getLabel() == "F"){
                gamePlayers.get(item).setLocation(Location.BOAT);
            }

            Boolean isBoatFull = false;
            for(Item val : Item.values()){
                if(val != item && val != FARMER && gamePlayers.get(val).getItemLocation() == Location.BOAT){
                    isBoatFull = true;
                }
            }
            if(!isBoatFull)
                gamePlayers.get(item).setLocation(Location.BOAT);
        }
    }

    @Override
    public void rowBoat() {
        assert (boatLocation != Location.BOAT);
        if (gamePlayers.get(FARMER).getItemLocation() == Location.BOAT) {
            boatLocation = boatLocation == Location.START ? Location.FINISH : Location.START;
        }
    }

    @Override
    public boolean gameIsLost() {
        Location location = gamePlayers.get(GOOSE).getItemLocation();
        if (location == Location.BOAT || location == boatLocation
                || location == gamePlayers.get(FARMER).getItemLocation()) {
            return false;
        } else if (location == gamePlayers.get(WOLF).getItemLocation() ||
                location == gamePlayers.get(BEANS).getItemLocation()) {
            return true;
        }
        return false;
    }

    @Override
    public int numberOfItems() {
        return 4;
    }

}