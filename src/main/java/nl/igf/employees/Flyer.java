package nl.igf.employees;

public interface Flyer {
    void fly();

    int getHourFlown();

    void setHourFlown(int hourFlown);

    boolean isIfr();

    void setIfr(boolean ifr);
}
