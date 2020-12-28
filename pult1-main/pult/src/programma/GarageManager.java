package programma;

public class GarageManager {
    private Command command;
    public void setCommand(Command command){
        this.command = command;
    }

    public void buttonApply(){
        command.apply();
    }
}
