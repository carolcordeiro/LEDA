package adt.linkedList;

public class RecursiveDoubleLinkedListImpl<T> extends
		RecursiveSingleLinkedListImpl<T> implements DoubleLinkedList<T> {

	protected RecursiveDoubleLinkedListImpl<T> previous;

	public RecursiveDoubleLinkedListImpl() {}

	@Override
	public void insert(T element) {
		if(this.isEmpty()) {
			RecursiveDoubleLinkedListImpl<T> nil = new RecursiveDoubleLinkedListImpl<>();
			this.setData(element);
			this.setNext(nil);
			this.setPrevious(this);

			if (this.previous == null){
				RecursiveDoubleLinkedListImpl<T> nilHead = new RecursiveDoubleLinkedListImpl<>();

				this.previous = nilHead;
				nilHead.setNext(this);
			}
		} else {
			this.next.insert(element);
		}
	}
	
	@Override
	public void insertFirst(T element) {
		if (this.isEmpty()) {
			this.insert(element);
		} else {
			RecursiveDoubleLinkedListImpl<T> newNode = new RecursiveDoubleLinkedListImpl<>();

			newNode.setData(this.data);
			this.data = element;
			newNode.setNext(this.next);
			this.next = newNode;
			newNode.setPrevious(this);
			((RecursiveDoubleLinkedListImpl<T>) this.next).setPrevious(newNode);
		}
	}
 
	@Override
	public void removeFirst() {
		if(!this.isEmpty()) {
			this.setData(this.getNext().getData());
			this.setNext(this.getNext().getNext());
			this.previous = new RecursiveDoubleLinkedListImpl<>();
		}
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty()) {
			if(this.getNext().getNext().isEmpty()) {
				this.setNext(new RecursiveDoubleLinkedListImpl<>());
			}else {
				((RecursiveDoubleLinkedListImpl<T>)this.getNext()).removeLast();
			}
		}
	}

	public RecursiveDoubleLinkedListImpl<T> getPrevious() {
		return previous;
	}

	public void setPrevious(RecursiveDoubleLinkedListImpl<T> previous) {
		this.previous = previous;
	}

}
