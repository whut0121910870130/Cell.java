public class Cell {
    private int cl;
    private int cw;
    private int isAlive;

    public Cell(int cl,int cw)
    {
        this.cl = cl;
        this.cw = cw;
        isAlive = 0;
    }
    public void setCw(int cw){this.cw = cw;}
    public void setCl(int cl){this.cl = cl;}

    public int getCl(){return cl;}
    public int getCw(){return cw;}

    public void setAlive(int isAlive){this.isAlive = isAlive;}
    public int getIsAlive(){return isAlive;}
}
