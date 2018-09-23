import org.junit.Test;

public class TestMyQueue {
    @Test
    public void test_0() {
        ImpQueue<Integer> op = new ImpQueue<>(0);
        op.add(3);
        op.add(2);
        op.add(0);
        //op.print_list();
        //System.out.println(op.lastIndexOf(2));
        op.remove();
        assert op.peek() == 3;
    }
}
