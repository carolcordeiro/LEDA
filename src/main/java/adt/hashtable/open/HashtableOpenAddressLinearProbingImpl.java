package adt.hashtable.open;

import adt.hashtable.hashfunction.HashFunctionClosedAddressMethod;
import adt.hashtable.hashfunction.HashFunctionLinearProbing;

public class HashtableOpenAddressLinearProbingImpl<T extends Storable> extends
		AbstractHashtableOpenAddress<T> {

	public HashtableOpenAddressLinearProbingImpl(int size,
			HashFunctionClosedAddressMethod method) {
		super(size);
		hashFunction = new HashFunctionLinearProbing<T>(size, method);
		this.initiateInternalTable(size);
	}

	private int hash (T element, int probe) {
		return ((HashFunctionLinearProbing<T>) this.hashFunction).hash(element, probe);
	}
	 
	@Override
	public void insert(T element) {
		if(!this.isFull()) {
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
				if (this.table[hash] != null) {
					if (this.table[hash].equals(element)) {
						this.table[hash] = this.deletedElement;
						this.elements -= 1;
						this.COLLISIONS -= i;
						break;
					}
					i++;
				}else {
					break;}
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
