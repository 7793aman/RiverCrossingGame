package river;

import java.awt.*;
import java.util.HashMap;


public class FarmerGameEngine extends AbstractGameEngine {

    public static final Item BEANS = Item.ITEM_0;
    public static final Item GOOSE = Item.ITEM_1;
    public static final Item WOLF = Item.ITEM_2;
    public static final Item FARMER = Item.ITEM_3;

    public FarmerGameEngine() {
        setGameObjects(new HashMap<Item, GameObject>(){{
            put(WOLF, new GameObject("W", Location.START, Color.CYAN));
            put(GOOSE, new GameObject("G", Location.START, Color.CYAN));
            put(BEANS, new GameObject("B", Location.START, Color.CYAN));
            put(FARMER, new GameObject("F", Location.START, Color.PINK));
        }});
        boatLocation = Location.START;
    }

    @Override
    public void rowBoat() {
        assert (boatLocation != Location.BOAT);
        if (gamePlayers.get(FARMER).getLocation() == Location.BOAT) {
            boatLocation = boatLocation == Location.START ? Location.FINISH : Location.START;
        }
    }

    @Override
    public boolean gameIsLost() {
        Location location = gamePlayers.get(GOOSE).getLocation();
        if (location == Location.BOAT || location == boatLocation
                || location == gamePlayers.get(FARMER).getLocation()) {
            return false;
        } else if (location == gamePlayers.get(WOLF).getLocation() ||
                location == gamePlayers.get(BEANS).getLocation()) {
            return true;
        }
        return false;
    }

    @Override
    public int numberOfItems() {
        return 4;
    }

}