package programma;

public class GarageLightOn implements Command{
    private GarageLight light;
    public GarageLightOn(GarageLight light){
        this.light = light;
    }

    @Override
    public void apply() {
        light.on();
    }
}
