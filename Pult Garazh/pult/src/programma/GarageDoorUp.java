package programma;

public class GarageDoorUp implements Command{
    private GarageDoor door;
    public GarageDoorUp(GarageDoor door){
        this.door = door;
    }
    @Override
    public void apply(){
        door.up();
    }
}
