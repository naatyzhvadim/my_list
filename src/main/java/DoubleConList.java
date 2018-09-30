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
        public T getValue(){
            return value;
        }

        public MyNode<T> getNext() {
            return next;
        }

        public MyNode<T> getPrev() {
            return prev;
        }

        public void setValue(T val){
            value = val;
        }
        public void setNext(MyNode<T> nxt){
            next = nxt;
        }
        public void setPrev(MyNode<T> prv){
            prev = prv;
        }
    }

    private MyNode<T> head = null, tail = null, mid = null;
    private int listSize = 0, mid_i = 0;

    DoubleConList(T tmp){
        mid = tail = head = new MyNode<>(tmp);
        ++listSize;
    }

    DoubleConList(){
    }

    @Override
    public int size() {
        return listSize;
    }

    @Override
    public boolean isEmpty() {
        return listSize == 0;
    }

    @Override
    public boolean contains(Object o) {
        MyNode<T> curNode = head;
        while(curNode != null && (o == null? curNode.value != null : !o.equals(curNode.value))){
            curNode = curNode.next;
        }
        return curNode != null;
    }

    @Override
    public Iterator<T> iterator() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public void forEach(Consumer<? super T> action) {
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
        if (head == null){
            mid = tail = head = new MyNode<>(t);
            ++listSize;
        } else {
            MyNode<T> lastNode = new MyNode<>(t);
            tail.next = lastNode;
            lastNode.prev = tail;
            tail = tail.next;
            ++listSize;
            updateMid();
        }
        return true;
    }

    public int getMid_i() {
        return mid_i;
    }

    public T getMid() {
        return mid.value;
    }

    private void updateMid(){
        if (listSize == 0 || listSize == 1){
            mid = head;
            mid_i = 0;
        }
        int sub = (listSize - 1)/2  - mid_i;
        if (sub == 0) {
            return;
        }
        for (int i = 0; i < Math.abs(sub); ++i){
            if (sub > 0){
                mid = mid.next;
            }else{
                mid = mid.prev;
            }
        }
        mid_i = (listSize - 1)/2;
    }

    @Override
    public boolean remove(Object o) {
        if (!contains(o)) {
            return false;
        }
        MyNode<T> curNode = head, prevNode, nextNode;
        int i = 0;
        while(o == null? curNode.value != null : !o.equals(curNode.value)){
            curNode = curNode.next;
            ++i;
        }
        if (i == 0) {
            --mid_i;
            removeFirst();
            return true;
        }
        if (i == listSize - 1){
            removeLast();
            return true;
        }
        if (i == mid_i){
            removeMiddle();
            return true;
        }
        prevNode = curNode.prev;
        nextNode = curNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        curNode.prev = null;
        curNode.next = null;
        --listSize;
        if (i < mid_i) {
            --mid_i;
        }
        updateMid();
        return true;
    }

    private T removeLast(){
        MyNode<T> node = tail;
        T val = tail.value;
        tail = tail.prev;
        tail.next = null;
        node.prev = null;
        --listSize;
        updateMid();
        return val;
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
    public boolean addAll(int index, Collection<? extends T> c) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean removeIf(Predicate<? super T> filter) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public void replaceAll(UnaryOperator<T> operator) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public void sort(Comparator<? super T> c) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public void clear() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= listSize) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        MyNode<T> curNode = findNode(index);
        return curNode.value;
    }

    private MyNode<T> findNode(int index){
        MyNode<T> curNode;
        int i;
        boolean dest;
        if (index < mid_i){
            if (index < mid_i - index){
                curNode = head;
                dest = true;
                i = 0;
            }else{
                curNode = mid;
                dest = false;
                i = mid_i;
            }
        }else{
            if (index - mid_i < listSize - mid_i){
                curNode = mid;
                dest = true;
                i = mid_i;
            }else{
                curNode = tail;
                dest = false;
                i = listSize - 1;
            }
        }
        while (i != index){
            if (dest){
                curNode = curNode.next;
                ++i;
            }else{
                curNode = curNode.prev;
                --i;
            }
        }
        return curNode;
    }

    @Override
    public T set(int index, T element) {
        if (index < 0 || index >= listSize) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        MyNode<T> curNode = findNode(index);
        curNode.value = element;
        return element;
    }

    @Override
    public void add(int index, T element) {
        if (index > listSize || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        if (index == listSize) {
            add(element);
            return;
        }
        if (index == 0){
            MyNode<T> node = new MyNode<>(element);
            node.next = head;
            node.prev = null;
            head.prev = node;
            head = node;
            ++listSize;
            ++mid_i;
            updateMid();
            return;
        }
        MyNode<T> curNode = findNode(index), prevNode, node = new MyNode<>(element);
        prevNode = curNode.prev;
        prevNode.next = node;
        node.prev = prevNode;
        node.next = curNode;
        curNode.prev = node;
        ++listSize;
        if (index <= mid_i){
            ++mid_i;
        }
        updateMid();
    }

    private T removeFirst(){
        MyNode<T> node = head;
        head = head.next;
        if (listSize == 1){
            tail = mid = head;
        }
        T val = node.value;
        node.prev = null;
        node.next = null;
        --listSize;
        updateMid();
        return val;
    }


    private T removeMiddle(){
        MyNode<T> prevNode = mid.prev, nextNode = mid.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        --listSize;
        T val = mid.value;
        mid.next = null;
        mid.prev = null;
        mid = nextNode;
        updateMid();
        return val;
    }

    public void print_list(){
        MyNode<T> node = head;
        while(node != null){
            System.out.print(node.value + " ");
            node = node.next;
        }
        System.out.println();
        if (head != null) {
            System.out.println("Head " + head.value);
            System.out.println("MId " + mid.value + "   " + mid_i);
            System.out.println("Tail " + tail.value);
        }
    }
    @Override
    public T remove(int index) {
        if (index >= listSize || index < 0) {
            throw new IndexOutOfBoundsException("Index is out of range");
        }
        if (index == 0) {
            --mid_i;
            return removeFirst();
        }
        if (index == listSize - 1){
            return removeLast();
        }
        if (index == mid_i){
            return removeMiddle();
        }

        MyNode<T> curNode = findNode(index);
        MyNode<T> prevNode = curNode.prev, nextNode = curNode.next;
        prevNode.next = nextNode;
        nextNode.prev = prevNode;
        --listSize;
        T removedVal = curNode.value;
        curNode.next = null;
        curNode.prev = null;
        curNode.value = null;
        if (index < mid_i){
            --mid_i;
        }
        updateMid();
        return removedVal;
    }

    @Override
    public int indexOf(Object o) {
        if (!contains(o)){
            return -1;
        }
        MyNode<T> curNode = head;
        int i = 0;
        while(o == null? curNode.value != null : !o.equals(curNode.value)){
            curNode = curNode.next;
            ++i;
        }
        return i;
    }

    @Override
    public int lastIndexOf(Object o) {
        if (!contains(o)){
            return -1;
        }
        MyNode<T> curNode = tail;
        int i;
        i = listSize - 1;
        while(o == null ? curNode.value != null : !o.equals(curNode.value)) {
            --i;
            curNode = curNode.prev;
        }
        return i;
    }

    @Override
    public ListIterator<T> listIterator() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public ListIterator<T> listIterator(int index) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public List<T> subList(int fromIndex, int toIndex) {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Spliterator<T> spliterator() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Stream<T> stream() {
        throw new UnsupportedOperationException("This operation is unsupported");
    }

    @Override
    public Stream<T> parallelStream() {
        throw new UnsupportedOperationException("This operation is unsupported");
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
        System.out.println(op.size());

    }
}
