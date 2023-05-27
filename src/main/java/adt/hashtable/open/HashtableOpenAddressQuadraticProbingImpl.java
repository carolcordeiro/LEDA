package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionQuadraticProbing;

public class HashtableOpenAddressQuadraticProbingImpl<T extends Storable>
		extends AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressQuadraticProbingImpl(int size,
			HashFunctionClosedAddressMethod method, int c1, int c2) {
		super(size);
		hashFunction = new HashFunctionQuadraticProbing<T>(size, method, c1, c2);
		this.initiateInternalTable(size);
	}

	private int hash(T element, int probe) {
		return ((HashFunctionQuadraticProbing<T>) this.hashFunction).hash(element, probe);
	}
	
	@Override 
	public void insert(T element) {
		if (!this.isFull()) {
			if (element != null) {
				int i = 0;
				while (i != this.capacity()){
					int hash = this.hash(element, i);
					
					if (this.table[hash] == null || this.table[hash].equals(deletedElement)) {
						this.table[hash] = element;
						this.elements += 1;
						break;}
					else if (this.table[hash].equals(element)) {
						break;}
					else {
						this.COLLISIONS += 1;
						i++;}
				}
			}
		}else {
			throw new HashtableOverflowException();
		}
	}

	@Override
	public void remove(T element) {
		if (element != null && !this.isEmpty()) {
			int i = 0;
			while (i != this.capacity()){
				int hash = this.hash(element, i);
				
				if (this.table[hash] != null && this.table[hash].equals(element)) {
					this.table[hash] = new DELETED();
					this.elements -= 1;}
				else {i++;}
			}	
		}
	}

	@Override
	public T search(T element) {
		if(element != null && !this.isEmpty()) {
			int i = 0;
			int hash = this.hash(element, i);
			while (i != this.capacity() && this.table[hash] != null){
				
				if (this.table[hash].equals(element)) {
					return (T) this.table[hash];}
				else {
					hash = this.hash(element, ++i);}
			}
			return null;	
		}else{
			return null;
		}
	}

	@Override
	public int indexOf(T element) {
		if (element != null && !this.isEmpty()) {
			int i = 0;
			int hash = this.hash(element, i);
			
			while (i != this.capacity() && this.table[hash] != null){
				
				if (this.table[hash].equals(element)) {
					return hash;}
				else {
					hash = this.hash(element, ++i);;}
			}
		}
		return -1;
	}
	
	
}
