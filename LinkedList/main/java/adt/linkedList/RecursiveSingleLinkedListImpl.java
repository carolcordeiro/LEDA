package adt.linkedList;

import java.util.ArrayList;
import java.util.List;

public class RecursiveSingleLinkedListImpl<T> implements LinkedList<T> {

	protected T data;
	protected RecursiveSingleLinkedListImpl<T> next;

	public RecursiveSingleLinkedListImpl() {}

 
	@Override
	public boolean isEmpty() {
		return this.data == null;
	}

	@Override
	public int size() {
		if (this.isEmpty()) {
			return 0;
		}else {
			return 1 + this.getNext().size();
		}
	}

	@Override
	public T search(T element) {
		if(!this.isEmpty()) {
			if(this.data == element) {
				return this.data;
			}else {
				return this.next.search(element);
			}
		}
		return null;
	}

	@Override
	public void insert(T element) {
		if (this.isEmpty()) {
			this.setData(element);
			this.setNext(new RecursiveSingleLinkedListImpl<T>());
		}else {
			this.next.insert(element);
		}
	}

	@Override
	public void remove(T element) {
		if(!this.isEmpty()) {
			if(this.data == element) {
				this.setData(this.next.data);
				this.setNext(this.next.next);
			}else {
				this.next.remove(element);
			}
		}
	}

	@Override
	public T[] toArray() {
		List<T> array = new ArrayList<>();
		return this.toArray(array);
	}

	@SuppressWarnings("unchecked")
	private T[] toArray(List<T> array) {
		if (!this.isEmpty()){
			array.add(this.data);
			this.next.toArray(array);
		}
		return (T[]) array.toArray();
	}


	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public RecursiveSingleLinkedListImpl<T> getNext() {
		return next;
	}

	public void setNext(RecursiveSingleLinkedListImpl<T> next) {
		this.next = next;
	}

}
