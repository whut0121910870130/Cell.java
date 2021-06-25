import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Game extends JFrame implements ActionListener {
    private static Game frame;
    private Map map;
    private Time time;
    private int maxlength;
    private int maxwidth;
    private JButton[][] ncell;
    private boolean[][] isSelected;
    private JButton ok,t,nextGeneration,randomInit,clearCell,nowGeneration,clearGeneration;
    private JButton start,pause,exit;
    private JComboBox timeList;
    private boolean isRunning;
    private Thread thread;
    private boolean isAlive;
    //private setTime = new JTextField(3);

    public Game(String name,Map map)
    {
        super(name);
        this.maxlength = map.getMl();
        this.maxwidth = map.getMw();
        this.map = map;
        initGame();
        time.setTime(500);


    }

    public void initGame()
    {
        if(maxlength == 0)
            maxlength = 20;
        if(maxwidth == 0)
            maxwidth = 35;

        map = new Map(maxlength,maxwidth);


        JPanel backPanel,centerPanel,bottomPanel;
        JLabel jTime,jNowGeneration;
        backPanel = new JPanel(new BorderLayout());
        centerPanel = new JPanel(new GridLayout(maxlength,maxwidth));
        bottomPanel = new JPanel();

        this.setContentPane(backPanel);
        backPanel.add(centerPanel,"Center");
        backPanel.add(bottomPanel,"South");


        ncell = new JButton[maxlength][maxwidth];
        isSelected = new boolean[maxlength][maxwidth];
        for(int i = 0; i < maxlength; i++)
            for(int j = 0; j < maxwidth; j++)
            {
                ncell[i][j] = new JButton(" ");//按钮置空以表示细胞
                ncell[i][j].setBackground(Color.WHITE);
                centerPanel.add(ncell[i][j]);
            }

        jTime = new JLabel("周期");
        timeList = new JComboBox();
        for(int i = 500; i <= 10000;i+=500)
            timeList.addItem(String.valueOf(i));

        ok = new JButton("确定");
        t = new JButton("周期");
        nowGeneration = new JButton("当前代数");
        clearGeneration = new JButton("代数清零");
        randomInit = new JButton("随即布局");
        clearCell = new JButton("细胞清零");
        nextGeneration = new JButton("下一代");
        start = new JButton("开始游戏");
        pause = new JButton("暂停游戏");
        exit = new JButton("退出游戏");

        bottomPanel.add(ok);
        bottomPanel.add(t);
        bottomPanel.add(nowGeneration);
        bottomPanel.add(clearGeneration);
        bottomPanel.add(randomInit);
        bottomPanel.add(clearCell);
        bottomPanel.add(nextGeneration);
        bottomPanel.add(start);
        bottomPanel.add(pause);
        bottomPanel.add(exit);


        //设置窗口
        this.setSize(1200,700);
        this.setResizable(true);
       //this.setLocation(null);
        this.setVisible(true);

        //注册监听器
        this.addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosed(WindowEvent e) {
                super.windowClosed(e);
                System.exit(0);
            }
        });

        ok.addActionListener(this);
        t.addActionListener(this);
        nowGeneration.addActionListener(this);
        clearGeneration.addActionListener(this);
        randomInit.addActionListener(this);
        clearCell.addActionListener(this);
        nextGeneration.addActionListener(this);
        start.addActionListener(this);
        pause.addActionListener(this);
        exit.addActionListener(this);

        for(int i = 0; i < maxlength;i++)
            for(int j = 0; j < maxwidth;j++)
            {
                ncell[i][j].addActionListener(this);
            }
    }

    public  void showCell()
    {
       // Cell[][] cell = new Cell[80][80];
        for(int i = 0; i < maxlength; i++)
            for(int j = 0; j < maxwidth; j ++)
            {
                if(map.getCellXY(i,j)== 1)
                    ncell[i][j].setBackground(Color.PINK);
                else
                    ncell[i][j].setBackground(Color.white);
            }
    }

    public  void makeNextGeneration()
    {
        map.cellUpdate();
        showCell();
        nextGeneration.setText(" "+map.getNowGeneration());

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == ok)//确定
        {
            time.setTime(timeList.getSelectedIndex());//设置周期
            initGame();
            map = new Map(20,30);
        }else if(e.getSource() == clearGeneration)//代数清零
        {
            map.setGeneration(0);
            nextGeneration.setText(" "+map.getNowGeneration());
            isRunning = false;
            thread = null;
        }else if(e.getSource() == randomInit)//细胞初始化
        {
            map.InitCell();
            showCell();
            isRunning = false;
            thread = null;
        }else if(e.getSource() == clearCell )//细胞清零
        {
            map.clearCell();
            showCell();
            isRunning = false;
            thread = null;
        }else if(e.getSource() == start)//游戏开始
        {
            isRunning = true;
            thread = new Thread(new Runnable() {
                @Override
                public void run() {
                    while (isRunning) {
                        makeNextGeneration();
                        try {
                            //Thread.sleep(time.getTime());
                            thread.sleep(500);
                        } catch (InterruptedException e1) {
                            e1.printStackTrace();
                        }
                        isAlive = false;
                        for (int row = 0; row < map.getMl(); row++) {
                            for (int col = 0; col < map.getMw(); col++) {
                                if (map.getCellXY(row, col) == 1) {
                                    isAlive = true;
                                    break;
                                }
                            }
                        }
                        if (!isAlive) {
                            JOptionPane.showMessageDialog(null, "所有细胞已死亡");
                            isRunning = false;
                            thread = null;
                        }
                    }
                }
            });
            thread.start();
        }else if(e.getSource() == nextGeneration)//下一代
        {
            makeNextGeneration();
            isRunning = false;
            thread = null;
        }else if(e.getSource() == pause)//暂停
        {
            isRunning = false;
            thread = null;
        }else if(e.getSource() == exit)//退出
        {
            frame.dispose();
            System.exit(0);
        }else
        {
            Cell[][] cell = new Cell[0][0];
            for(int i = 0 ; i < maxlength; i++)
                for(int j = 0 ; j < maxwidth; j++)
                {
                    if(e.getSource() == ncell[i][j])
                    {
                        isSelected[i][j] = !isSelected[i][j];
                        if(isSelected[i][j])
                        {
                            ncell[i][j].setBackground(Color.PINK);
                            cell[i+1][j+1].setAlive(1);
                        }else
                        {
                            ncell[i][j].setBackground(Color.white);
                            cell[i+1][j+1].setAlive(0);
                        }
                        break;
                    }
                }
        }
        //map.cell.
    }
}

