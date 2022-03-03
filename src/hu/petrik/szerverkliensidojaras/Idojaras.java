package hu.petrik.szerverkliensidojaras;

public class Idojaras {
    private String megye;
    private Elorejelzses mai;
    private Elorejelzses holnapi;

    public Idojaras(String sor) {
        String[] adatok = sor.split("\\t+", -1);

        this.megye = adatok[0].trim();

        String elorejelzes = adatok[1].trim();
        String minMax = adatok[2].trim();
        this.mai = new Elorejelzses(elorejelzes, minMax);

        elorejelzes = adatok[3].trim();
        minMax = adatok[4].trim();
        this.holnapi = new Elorejelzses(elorejelzes, minMax);
    }

    public String getMegye() {
        return megye;
    }

    public void setMegye(String megye) {
        this.megye = megye;
    }

    public Elorejelzses getMai() {
        return mai;
    }

    public void setMai(Elorejelzses mai) {
        this.mai = mai;
    }

    public Elorejelzses getHolnapi() {
        return holnapi;
    }

    public void setHolnapi(Elorejelzses holnapi) {
        this.holnapi = holnapi;
    }

    @Override
    public String toString() {
        return megye + ":\n\tMai: " + mai +
                "\n\tHolnapi: " + holnapi;
    }
}
