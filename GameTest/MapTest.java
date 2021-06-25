import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MapTest {
    Map map1 = new Map(10,10);
    Map map2 = new Map(20,10);
    Map map3 = new Map(-1,-1);


    @Test
    void setMl() {
        map1.setMl(15);
        map1.setMl(25);
        map3.setMl(1);
        assertArrayEquals(15,map1.getMl());
        assertArrayEquals(25,map2.getMl());
        assertArrayEquals(1,map3.getMl());
    }

    @Test
    void getMl() {
        assertArrayEquals(10,map1.getMl());
        assertArrayEquals(20,map2.getMl());
        assertArrayEquals(-1,map3.getMl());
    }

    private void assertArrayEquals(int i, int ml) {
    }

    @Test
    void setMw() {
        map1.setMw(15);
        map1.setMw(25);
        map3.setMw(1);
        assertArrayEquals(15,map1.getMw());
        assertArrayEquals(25,map2.getMw());
        assertArrayEquals(1,map3.getMw());
    }

    @Test
    void getMw() {
        assertArrayEquals(10,map1.getMw());
        assertArrayEquals(10,map2.getMw());
        assertArrayEquals(-1,map3.getMw());

    }

    @Test
    void getNowGeneration() {
        assertArrayEquals(0,map1.getNowGeneration());
        assertArrayEquals(0,map2.getNowGeneration());
        assertArrayEquals(0,map3.getNowGeneration());
    }

    @Test
    void getCellXY() {
        assertArrayEquals(0,map1.getCellXY(9,9));
        assertArrayEquals(0,map2.getCellXY(0,0));
       // assertArrayEquals(0, map3.getCellXY(-1,-1));
    }

    @Test
    void initCell() {
        map1.InitCell();
        map1.WPrint();
        map2.InitCell();
        map2.WPrint();
        map3.InitCell();
        map3.WPrint();
    }

    @Test
    void setGeneration() {
        map1.setGeneration(1);
        assertArrayEquals(1,map1.getNowGeneration());
        map2.setGeneration(2);
        assertArrayEquals(2,map2.getNowGeneration());
        map3.setGeneration(3);
        assertArrayEquals(3,map3.getNowGeneration());
    }

    @Test
    void clearCell() {
        map1.InitCell();
        map1.WPrint();
        System.out.println("------");
        map1.clearCell();
        map1.WPrint();
    }

    @Test
    void cellUpdate() {
        map1.InitCell();
        map1.WPrint();
        System.out.println("------");
        map1.cellUpdate();
        map1.WPrint();
        System.out.println("------");
        map1.cellUpdate();
        map1.WPrint();
        System.out.println("Genneration: "+map1.getNowGeneration());
    }

    /*@Test
    void getNumNeibor() {
        map1.InitCell();
        map1.WPrint();
        System.out.println("------");
        System.out.println("邻居数量："+map1.getNumNeibor(1,1));
        System.out.println("邻居数量："+map1.getNumNeibor(1,2));
    }*/
}