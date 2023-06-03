package adt.linkedList;


public class SingleLinkedListImpl<T> implements LinkedList<T> {

	protected SingleLinkedListNode<T> head;

	public SingleLinkedListImpl() {
		this.head = new SingleLinkedListNode<T>();
	}

	@Override
	public boolean isEmpty() {
		return this.head.isNIL();
	}
 
	@Override 
	public int size() {
		int size = 0;
		SingleLinkedListNode<T> auxHead = this.head;
		while (!auxHead.isNIL()) {
			size += 1;
			auxHead = auxHead.getNext();
		}
		return size;
	}

	@Override
	public T search(T element) {
		SingleLinkedListNode<T> auxHead = this.head;
		while (!auxHead.isNIL() && auxHead.getData() != element) {
			auxHead = auxHead.getNext();
		}
		return auxHead.getData();
	}

	@Override
	public void insert(T element) {
		SingleLinkedListNode<T> auxHead = this.head;
		if (this.isEmpty()) {
			SingleLinkedListNode<T> newHead = new SingleLinkedListNode<T>();
			newHead.next = this.head;
			head = newHead;
			head.setData(element);
		}else {
			while(!auxHead.isNIL()) {
				auxHead = auxHead.getNext();
			}
			auxHead.setData(element);
			auxHead.setNext(new SingleLinkedListNode<T>());
		}
	}

	@Override
	public void remove(T element) {
		if (this.head.getData() == element) {
			this.setHead(this.head.getNext());
		}else {
			SingleLinkedListNode<T> auxHead = this.head;
			while (!auxHead.isNIL() && auxHead.getData() != element) {
				auxHead = auxHead.getNext();
			}
			if (!auxHead.isNIL()) {
				auxHead.setData(auxHead.getNext().getData());
				auxHead.setNext(auxHead.getNext().getNext());
			}
		}
	}

	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		T[] itens = (T[]) new Object[this.size()];
		SingleLinkedListNode<T> auxHead = this.head;
		
		for (int i = 0; i < itens.length; i++) {
			itens[i] = auxHead.getData();
			auxHead = auxHead.getNext();
		}
		
		return itens;
	}

	public SingleLinkedListNode<T> getHead() {
		return head;
	}

	public void setHead(SingleLinkedListNode<T> head) {
		this.head = head;
	}

}
