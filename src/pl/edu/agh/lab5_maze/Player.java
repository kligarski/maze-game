package pl.edu.agh.lab5_maze;

import pl.edu.agh.lab5_maze.map_sites.Room;

public class Player {
    private Room currentRoom;
    private boolean alive = true;
    private int numberOfSpecialItems = 0;

    public Room getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Room currentRoom) {
        this.currentRoom = currentRoom;
    }

    public boolean isAlive() {
        return alive;
    }

    public void kill() {
        alive = false;
    }

    public void addSpecialItem() {
        ++numberOfSpecialItems;
    }

    public void useSpecialItem() {
        --numberOfSpecialItems;
    }

    public int getNumberOfSpecialItems() {
        return numberOfSpecialItems;
    }
}
