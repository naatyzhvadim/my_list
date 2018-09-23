import java.util.Collection;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class ImpQueue<T> implements java.util.Queue<T> {
    private DoubleConList<T> list;

    ImpQueue(T element){
        list = new DoubleConList<>(element);
    }

    @Override
    public int size() {
        return list.size();
    }

    @Override
    public boolean isEmpty() {
        return list.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        return list.contains(o);
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public Object[] toArray() {
        return new Object[0];
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        return null;
    }

    @Override
    public boolean add(T t) {
        return list.add(t);
    }

    @Override
    public boolean remove(Object o) {
        return list.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void clear() {

    }

    @Override
    public boolean offer(T t) {
        return false;
    }

    @Override
    public T remove() {
        try{
            return list.remove(0);
        }
        catch (IndexOutOfBoundsException ex){
            throw new NoSuchElementException("This queue is empty");
        }
    }

    @Override
    public T poll() {
        try{
            return list.remove(0);
        }
        catch (IndexOutOfBoundsException ex){
            return null;
        }
    }

    @Override
    public T element() {
        if (list.isEmpty())
            throw  new NoSuchElementException("This queue is empty");
        return list.get(0);
    }

    @Override
    public T peek() {
        if (list.isEmpty())
            return null;
        return list.get(0);
    }

    public static void main(String[] args) {
        ImpQueue<Double> queue = new ImpQueue<>(3.5);
        queue.add(5.5);
    }
}
