import org.junit.Test;

public class TestMyList {
    @Test
    public void test_0() {
        DoubleConList<Integer> op = new DoubleConList<>();
        op.add(1);
        op.add(2);
        op.add(0);
        op.add(5);
        op.add(1);
        op.add(2);
        //op.print_list();
        //System.out.println(op.lastIndexOf(2));
        assert op.lastIndexOf(2) == 5;
    }
    @Test
    public void test_1() {
        DoubleConList<Integer> op = new DoubleConList<>();
        op.add(1);
        op.add(2);
        op.add(0);
        op.add(5);
        op.add(1);
        op.add(2);
        //op.print_list();
        //System.out.println(op.indexOf(5));
        assert op.indexOf(5) == 3;
    }
}