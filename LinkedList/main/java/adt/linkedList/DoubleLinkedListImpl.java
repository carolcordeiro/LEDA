package adt.linkedList;

public class DoubleLinkedListImpl<T> extends SingleLinkedListImpl<T> implements
		DoubleLinkedList<T> {

	protected DoubleLinkedListNode<T> last;

	public DoubleLinkedListImpl() {
		this.last = new DoubleLinkedListNode<>();
		this.head = new DoubleLinkedListNode<>();
	}
	
	@Override
	public void insert(T element) {
		if (element != null) {
			DoubleLinkedListNode<T> newNode = new DoubleLinkedListNode<>();
			DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();

			newNode.setData(element);
			newNode.setNext(nil);
			nil.setPrevious(newNode);
			this.last.setNext(newNode);
			newNode.setPrevious(this.last);

			if (this.last.isNIL())
				this.head = newNode;

			this.last = newNode;
		}
	}
	
	@Override
	public void insertFirst(T element) {
		DoubleLinkedListNode<T> newHead = new DoubleLinkedListNode<T>();
		DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<T>();
		newHead.setData(element);
		newHead.setNext(this.head);
		newHead.setPrevious(nil);
		nil.setNext(newHead);
		 
		((DoubleLinkedListNode<T>) this.head).setPrevious(newHead);  
		if(this.head.isNIL()) {
			this.last = newHead;
		}   
		this.head = newHead;
	}

	@Override
	public void removeFirst() {
		if(!this.head.isNIL()) {
			this.setHead(this.getHead().getNext());
			if(this.head.isNIL()) {
				this.setLast((DoubleLinkedListNode<T>)(this.head));                           //!!!!!
			}
		}
	}

	@Override
	public void removeLast() {
		if(!this.isEmpty()) {
			this.last = this.last.getPrevious();
			if(this.last == null) {
				this.head = this.last;
			}else {
				DoubleLinkedListNode<T> nil = new DoubleLinkedListNode<>();
				this.last.setNext(nil);
				nil.setPrevious(this.getLast());
			}
		}
	}

	public DoubleLinkedListNode<T> getLast() {
		return last;
	}

	public void setLast(DoubleLinkedListNode<T> last) {
		this.last = last;
	}

}
