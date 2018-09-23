import java.util.*;
import java.util.function.Consumer;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;
import java.util.stream.Stream;

public class DoubleConList<T> implements java.util.List<T>{
    private class MyNode<T>{
        private T value;
        private MyNode<T> next = null;
        private MyNode<T> prev = null;
        MyNode(T tmp){
            value = tmp;
        }
    }

    private MyNode<T> head = null;
    private int list_size = 0;

    DoubleConList(T tmp){
        head = new MyNode<>(tmp);
        ++list_size;
    }

    DoubleConList(){
    }

    @Override
    public int size() {
        return list_size;
    }

    @Override
    public boolean isEmpty() {
        return list_size == 0;
    }

    @Override
    public boolean contains(Object o) {
        MyNode<T> cur_node = head;
        int flag = 0;
        while(cur_node != null){
            if (o == null? cur_node.value == null : o.equals(cur_node.value)) {
                ++flag;
                break;
            }
            cur_node = cur_node.next;
        }
        return flag == 1;
    }

    @Override
    public Iterator<T> iterator() {
        return null;
    }

    @Override
    public void forEach(Consumer<? super T> action) {

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
        if (head == null){
            head = new MyNode<>(t);
            ++list_size;
        } else {
            MyNode<T> cur_node = head;
            while (cur_node.next != null)
                cur_node = cur_node.next;
            MyNode<T> last_node = new MyNode<>(t);
            cur_node.next = last_node;
            last_node.prev = cur_node;
            ++list_size;
        }
        return true;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o))
            return false;
        MyNode<T> cur_node = head, prev_node = null, next_node;
        int i = 0;
        while(cur_node != null){
            if (o == null? cur_node.value == null : o.equals(cur_node.value))
                break;
            cur_node = cur_node.next;
            ++i;
        }
        if (i == 0) {
            remove_first();
            return true;
        }
        prev_node = cur_node.prev;
        next_node = cur_node.next;
        prev_node.next = next_node;
        if (next_node != null){
            next_node.prev = prev_node;
        }
        cur_node.prev = null;
        cur_node.next = null;
        --list_size;
        return true;
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
    public boolean addAll(int index, Collection<? extends T> c) {
        return false;
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return false;
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        return false;
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        return false;
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {

    }

    @Override
    public void sort(Comparator<? super T> c) {

    }

    @Override
    public void clear() {

    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= list_size)
            throw new IndexOutOfBoundsException("Index is out of range");
        MyNode<T> cur_node = head;
        int i = 0;
        while (i != index){
            cur_node = cur_node.next;
            ++i;
        }
        return cur_node.value;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= list_size)
            throw new IndexOutOfBoundsException("Index is out of range");
        MyNode<T> cur_node = head;
        int i = 0;
        while (i != index){
            cur_node = cur_node.next;
            ++i;
        }
        cur_node.value = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index > list_size || index < 0)
            throw new IndexOutOfBoundsException("Index is out of range");
        if (index == list_size) {
            add(element);
            return;
        }
        if (index == 0){
            MyNode<T> node = new MyNode<>(element);
            node.next = head;
            node.prev = null;
            head.prev = node;
            head = node;
            ++list_size;
            return;
        }
        MyNode<T> cur_node = head.next, prev_node = head, node = new MyNode<>(element);
        int i = 1;
        while (i != index){
            prev_node = cur_node;
            cur_node = cur_node.next;
            ++i;
        }
        prev_node.next = node;
        node.prev = prev_node;
        node.next = cur_node;
        cur_node.prev = node;
        ++list_size;
    }

    private T remove_first(){
        MyNode<T> node = head;
        head = head.next;
        T val = node.value;
        node.prev = null;
        node.next = null;
        --list_size;
        return val;
    }

    public void print_list(){
        MyNode<T> node = head;
        while(node != null){
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
    }
    @Override
    public T remove(int index) {
        if (index >= list_size || index < 0)
            throw new IndexOutOfBoundsException("Index is out of range");

        if (index == 0) {
            return remove_first();
        }

        MyNode<T> cur_node = head;
        for(int i = 0; i < index; ++i)
            cur_node = cur_node.next;
        System.out.println("This is cur_node " + cur_node.value);
        MyNode<T> prev_node = cur_node.prev;
        MyNode<T> next_node = cur_node.next;
        prev_node.next = next_node;
        if (next_node != null)
            next_node.prev = prev_node;

        --list_size;
        T removed_val = cur_node.value;
        cur_node.next = null;
        cur_node.prev = null;
        cur_node.value = null;

        return removed_val;
    }

    @Override
    public int indexOf(Object o) {
        if (!contains(o)){
            return -1;
        }
        MyNode<T> cur_node = head;
        int i = 0;
        while(o == null? cur_node.value != null : !o.equals(cur_node.value)){
            cur_node = cur_node.next;
            ++i;
        }
        return i;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (!contains(o)){
            return -1;
        }
        MyNode<T> cur_node = head;
        int i = 0, last = 0;
        while(cur_node != null) {
            if (o == null ? cur_node.value == null : o.equals(cur_node.value))
                last = i;
            ++i;
            cur_node = cur_node.next;
        }
        return last;
    }

    @Override
    public ListIterator<T> listIterator() {
        return null;
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        return null;
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        return null;
    }

    @Override
    public Spliterator<T> spliterator() {
        return null;
    }

    @Override
    public Stream<T> stream() {
        return null;
    }

    @Override
    public Stream<T> parallelStream() {
        return null;
    }

    public static void main(String[] args) {
        DoubleConList<Integer> op = new DoubleConList<>();
        op.add(1);
        op.add(2);
        op.add(3);
        op.add(4);
        op.add(4, 5);
        op.add(0, 0);
        op = new DoubleConList<>();
        op.add(0, 7);

        op.print_list();
        /*System.out.println("Before");
        op.print_list();
        System.out.println(op.remove(3));
        System.out.println("After");
        op.print_list();*/
        System.out.println(op.size());

        /*LinkedList<Integer> util_list = new LinkedList<>();
        System.out.println(util_list.add(null));
        System.out.println(util_list.add(5));
        System.out.println(util_list.getFirst());
        System.out.println(util_list.remove(1));*/

        //util_list.listIterator()
    }
}
