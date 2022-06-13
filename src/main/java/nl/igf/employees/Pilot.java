package nl.igf.employees;

public class Pilot implements Flyer {
    private int hourFlown = 0;
    private  boolean ifr = false;

    public Pilot(int hourFlown, boolean ifr) {
        this.hourFlown = hourFlown;
        this.ifr = ifr;
    }

    @Override
    public void fly() {
        System.out.println("Fly like an eagle!!!");
    }

    @Override
    public int getHourFlown() {
        return hourFlown;
    }

    @Override
    public void setHourFlown(int hourFlown) {
        this.hourFlown = hourFlown;
    }

    @Override
    public boolean isIfr() {
        return ifr;
    }

    @Override
    public void setIfr(boolean ifr) {
        this.ifr = ifr;
    }
}
