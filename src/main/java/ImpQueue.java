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
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Object[] toArray() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public <T1> T1[] toArray(T1[] a) {
        throw new UnsupportedOperationException("This operation is unsupported");
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
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean addAll(Collection<? extends T> c) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean offer(T t) {
        throw new UnsupportedOperationException("This operation is unsupported");
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
