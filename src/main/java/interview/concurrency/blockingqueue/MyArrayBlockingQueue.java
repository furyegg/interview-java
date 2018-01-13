package interview.concurrency.blockingqueue;

import java.util.Collection;
import java.util.Iterator;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.TimeUnit;

public class MyArrayBlockingQueue<E> implements BlockingQueue<E> {
	
	private Object[] items;
	
	private int capacity;
	private int putIndex;
	private int takeIndex;
	private int size;
	
	public MyArrayBlockingQueue(int capacity) {
		this.capacity = capacity;
		items = new Object[capacity];
		size = 0;
		putIndex = 0;
		takeIndex = -1;
	}
	
	private int nextIndex(int currentIndex) {
		return currentIndex == capacity - 1 ? 0 : ++currentIndex;
	}
	
	@Override
	public void put(E e) throws InterruptedException {
		synchronized (this) {
			if (size == capacity) {
				wait();
			}
			items[putIndex] = e;
			putIndex = nextIndex(putIndex);
			if (takeIndex == -1) {
				takeIndex = 0;
			}
			++size;
			notifyAll();
		}
	}
	
	@Override
	public E take() throws InterruptedException {
		synchronized (this) {
			if (size == 0) {
				wait();
			}
			E item = (E) items[takeIndex];
			items[takeIndex] = null;
			takeIndex = nextIndex(takeIndex);
			--size;
			notifyAll();
			return item;
		}
	}
	
	@Override
	public boolean add(E e) {
		return false;
	}
	
	@Override
	public boolean offer(E e) {
		return false;
	}
	
	@Override
	public E remove() {
		return null;
	}
	
	@Override
	public E poll() {
		return null;
	}
	
	@Override
	public E element() {
		return null;
	}
	
	@Override
	public E peek() {
		return null;
	}
	
	@Override
	public boolean offer(E e, long timeout, TimeUnit unit) throws InterruptedException {
		return false;
	}
	
	@Override
	public E poll(long timeout, TimeUnit unit) throws InterruptedException {
		return null;
	}
	
	@Override
	public int remainingCapacity() {
		return 0;
	}
	
	@Override
	public boolean remove(Object o) {
		return false;
	}
	
	@Override
	public boolean containsAll(Collection<?> c) {
		return false;
	}
	
	@Override
	public boolean addAll(Collection<? extends E> c) {
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
	public int size() {
		return 0;
	}
	
	@Override
	public boolean isEmpty() {
		return false;
	}
	
	@Override
	public boolean contains(Object o) {
		return false;
	}
	
	@Override
	public Iterator<E> iterator() {
		return null;
	}
	
	@Override
	public Object[] toArray() {
		return new Object[0];
	}
	
	@Override
	public <T> T[] toArray(T[] a) {
		return null;
	}
	
	@Override
	public int drainTo(Collection<? super E> c) {
		return 0;
	}
	
	@Override
	public int drainTo(Collection<? super E> c, int maxElements) {
		return 0;
	}
}
