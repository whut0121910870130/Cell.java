public class Map {
    private int ml;
    private int mw;
    private Cell[][] cell = new Cell[80][80];//分配空间
    private int generation;

    public Map(int ml,int mw)
    {
        this.ml = ml;
        this.mw = mw;
        generation = 0;
        for(int i = 0; i < ml; i++)
            for(int j = 0; j < mw; j++)
            {
                cell[i][j] = new Cell(i,j);//对空间里的每一个细胞进行初始化
                cell[i][j].setAlive(0);
            }
    }

    public void setMl(int ml) { this.ml = ml; }
    public int getMl() { return ml; }
    public void setMw(int mw) { this.mw = mw; }
    public int getMw() { return mw; }
    public int getNowGeneration()
    {
        return generation;
    }

    public int getCellXY(int x,int y)
    {
        return this.cell[x][y].getIsAlive();
    }

    //随机初始化细胞
    public void InitCell()
    {
        for(int i = 0; i < ml; i ++)
            for(int j = 0; j < mw; j ++)
                cell[i][j].setAlive(Math.random()>0.5?1:0);
    }

    //重置细胞代数
    public void setGeneration(int generation)
    {
        this.generation = generation;
    }

    //细胞清零
    public void clearCell()
    {
        for(int i = 0; i < ml; i++)
            for(int j = 0; j < mw; j++)
            {
                cell[i][j].setAlive(0);
            }
        generation = 0;
    }

    //细胞自动繁衍
    public void cellUpdate()
    {
        int [][] c=new int[ml][mw]; //用于保存每一个细胞周围存活细胞个数
        for(int x=0;x<ml;x++){
            for(int y=0;y<mw;y++){
                if(cell[x][y].getIsAlive() == 1){
                    for(int xx=x-1;xx<=x+1;xx++){
                        for(int yy=y-1;yy<=y+1;yy++){
                            if(xx>=0&&xx<ml&&yy>=0&&yy<mw)c[xx][yy]++;
                        }
                    }
                    c[x][y]--;
                }
            }
        }
        for(int x=0;x<ml;x++){
            for(int y=0;y<mw;y++){
                switch(c[x][y]){
                    case 3:cell[x][y].setAlive(1);break;
                    case 2:break;
                    default:cell[x][y].setAlive(0);
                }
            }
        }
        generation++;
    }
        /*Cell[][] newCell = new Cell[ml + 2][mw + 2];
        for(int i = 0; i < ml+2;i++)
            for(int j = 0; j < mw+2; j++)
            {
                newCell[i][j] = new Cell(i,j);
            }
        for(int i = 0; i < ml; i++)
            for(int j = 0 ;j < mw; j++)
                switch(getNumNeibor(i,j))
                {
                    case 2:
                        newCell[i][j].setAlive(cell[i][j].getIsAlive());
                        break;
                    case 3:
                        newCell[i][j].setAlive(1);
                        break;
                    default:
                        newCell[i][j].setAlive(0);
                }
        for(int i = 0; i < ml; i++)
            for(int j = 0; j < mw; j++)
                cell[i][j] = newCell[i][j];
            generation++;*/






    //获取细胞邻居数量
    /*public int getNumNeibor(int i1,int j1)
    {
       int count = 0;
        for(int i = i1 - 1; i <= i1 + 1; i++)
             {
         for(int j = j1 - 1; j <= j1 + 1; j++)
             {
                if(i>=0&&i<ml&&j>=0&&j<mw&&cell[i][j].getIsAlive() == 1)//不能简单地==而是利用某种匹配函数
                    count++;}}
        if(cell[i1][j1].getIsAlive() == 1)
                    count = count -1;
        return count;*/
        /*
        int[][] c = new int[mw][ml];

        for(int i = 0; i < mw; i++)
        {
            for(int j = 0; j < ml; j++)
            {
                if(cell[i][j].getIsAlive() == 1)
                {
                    for(int x = i - 1;x <= i + 1;x++)
                    {
                        for(int y = j - 1;y <= j + 1;y++)
                        {
                            if(x>=0&&y>=0&&x<=ml&&y<=mw)
                                c[x][y]++;
                        }
                    }
                    c[i][j]--;
                }
            }
        }

        for(int i = 0 ; i < i1;i++)
        {
            for(int j = 0; j < j1;j++)
            {
                 count =  c[i1][j1];
            }
        }
       // count = c[i1][j1];
        return count;
    }*/

    public void WPrint() {
        for(int i = 0 ; i < ml;i++)

        {
            for (int j = 0; j < mw; j++)

            {
                System.out.print(cell[i][j].getIsAlive());
            }
            System.out.println(" ");
        }
        System.out.println(" ");
    }
}

