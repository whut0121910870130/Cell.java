import static org.junit.jupiter.api.Assertions.*;

class CellTest {
    private Cell test1 = new Cell(1,1);
    private Cell test2 = new Cell(-1,0);

    @org.junit.jupiter.api.Test
    void setCw() {
        test1.setCw(-1);
        test2.setCw(10);
        assertArrayEquals(-1,test1.getCl());
        assertArrayEquals(10,test2.getCl());

    }

    @org.junit.jupiter.api.Test
    void setCl() {
       test1.setCl(-1);
       test2.setCl(10);
       assertArrayEquals(-1,test1.getCl());
       assertArrayEquals(10,test2.getCl());
    }

    @org.junit.jupiter.api.Test
    void getCl() {
        assertArrayEquals(1,test1.getCl());

    }

    private void assertArrayEquals(int i, int cl) {
    }

    @org.junit.jupiter.api.Test
    void getCw() {
        assertArrayEquals(1,test1.getCw());
        assertArrayEquals(0,test2.getCw());
    }

    @org.junit.jupiter.api.Test
    void setAlive() {
        test1.setAlive(1);
        test2.setAlive(0);
        assertArrayEquals(1,test1.getIsAlive());
        assertArrayEquals(1,test2.getIsAlive());
    }

    @org.junit.jupiter.api.Test
    void getIsAlive() {
        assertArrayEquals(0,test1.getIsAlive());
        assertArrayEquals(0,test2.getIsAlive());
    }
}