package adt.queue;

import adt.linkedList.DoubleLinkedList;
import adt.linkedList.DoubleLinkedListImpl;
import adt.linkedList.SingleLinkedListImpl;

public class QueueDoubleLinkedListImpl<T> implements Queue<T> {

	protected DoubleLinkedList<T> list;
	protected int size;

	public QueueDoubleLinkedListImpl(int size) {
		this.size = size;
		this.list = new DoubleLinkedListImpl<T>();
	}

	@Override 
	public void enqueue(T element) throws QueueOverflowException {
		if(this.isFull()) {
			throw new QueueOverflowException();
		}
		this.list.insert(element);
	}

	@Override
	public T dequeue() throws QueueUnderflowException {
		if(this.isEmpty()) {
			throw new QueueUnderflowException();
		}
		T last = ((DoubleLinkedListImpl<T>) this.list).getHead().getData();
		this.list.removeFirst();
		return last;
	}

	@Override
	public T head() {
		if(this.isEmpty()) {
			return null;
		}else {
			return ((SingleLinkedListImpl<T>) this.list).getHead().getData();
		}
	}

	@Override
	public boolean isEmpty() {
		return ((SingleLinkedListImpl<T>) this.list).getHead().getData() == null;
	}

	@Override
	public boolean isFull() {
		return this.list.size() == this.size;
	}

}
