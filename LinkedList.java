
//import java.lang.classfile.Signature;

/**
 * Represents a list of Nodes. 
 */
public class LinkedList {
	
	private Node first; // pointer to the first element of this list
	private Node last;  // pointer to the last element of this list
	private int size;   // number of elements in this list
	
	/**
	 * Constructs a new list.
	 */ 
	public LinkedList () {
		first = null;
		last = first;
		size = 0;
	}
	
	/**
	 * Gets the first node of the list
	 * @return The first node of the list.
	 */		
	public Node getFirst() {
		return this.first;
	}

	/**
	 * Gets the last node of the list
	 * @return The last node of the list.
	 */		
	public Node getLast() {
		return this.last;
	}
	
	/**
	 * Gets the current size of the list
	 * @return The size of the list.
	 */		
	public int getSize() {
		return this.size;
	}
	
	/**
	 * Gets the node located at the given index in this list. 
	 * 
	 * @param index
	 *        the index of the node to retrieve, between 0 and size
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 * @return the node at the given index
	 */		
	public Node getNode(int index) {
		if (index < 0 || index > size) {
			throw new IllegalArgumentException(
					"index must be between 0 and size");
		}
		//// Replace the following statement with your code
		Node current = this.first;
		//// Replace the following statement with your code
		for(int i =0; i <index;i++)
		{
			current = current.next;
		}
		return current;
	}
	
	/**
	 * Creates a new Node object that points to the given memory block, 
	 * and inserts the node at the given index in this list.
	 * <p>
	 * If the given index is 0, the new node becomes the first node in this list.
	 * <p>
	 * If the given index equals the list's size, the new node becomes the last 
	 * node in this list.
     * <p>
	 * The method implementation is optimized, as follows: if the given 
	 * index is either 0 or the list's size, the addition time is O(1). 
	 * 
	 * @param block
	 *        the memory block to be inserted into the list
	 * @param index
	 *        the index before which the memory block should be inserted
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than the list's size
	 */
	public void add(int index, MemoryBlock block) {
		//// Write your code here
		Node n = new Node (block);
		Node current = this.first;
		if(index<0 || index> this.size)
		{
			throw new IllegalArgumentException(
				"index must be between 0 and size");
		}
		if (index == 0) {
			if (this.size == 0) { // List is empty
				this.first = n;
				this.last = n;
			}
			else
			 { // List is not empty
				n.next = this.first;
				this.first = n;
			}
		} 
		else if (index == this.size) {
			this.last.next = n;
			this.last = n;
		} 
		// Case 3: Adding in the middle of the list
		else {
			for (int i = 0; i < index - 1; i++) { // Stop at the node before the target index
				current = current.next;
			}
			n.next = current.next;
			current.next = n;
		}
	
		// Increment the size of the list
		this.size++;
	}
	

	/**
	 * Creates a new node that points to the given memory block, and adds it
	 * to the end of this list (the node will become the list's last element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addLast(MemoryBlock block) {
		//// Write your code here
		Node n = new Node (block);
		if(this.size ==0)
		{
			this.last = n;
			this.first = n;
			this.size++;
		}
		else{
		this.last.next = n;
		this.last = n;
		this.size++;
		}
	}
	
	/**
	 * Creates a new node that points to the given memory block, and adds it 
	 * to the beginning of this list (the node will become the list's first element).
	 * 
	 * @param block
	 *        the given memory block
	 */
	public void addFirst(MemoryBlock block) 
	{
		//// Write your code here
		Node n = new Node (block);
		if(this.size == 0)
		{
			n.next = this.first;
			this.first =n;
			this.last =n;
			this.size++;
		}
		else
		{
		n.next = this.first;
		this.first =n;
		this.size++;
		}
		}

	/**
	 * Gets the memory block located at the given index in this list.
	 * 
	 * @param index
	 *        the index of the retrieved memory block
	 * @return the memory block at the given index
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public MemoryBlock getBlock(int index) {
		//// Replace the following statement with your code
		if (index < 0 || index >= size) {
			throw new IllegalArgumentException(
				"index must be between 0 and size");
		}
		MemoryBlock temp = getNode(index).block;
		return temp;
	}	

	/**
	 * Gets the index of the node pointing to the given memory block.
	 * 
	 * @param block
	 *        the given memory block
	 * @return the index of the block, or -1 if the block is not in this list
	 */
	public int indexOf(MemoryBlock block) {
		//// Replace the following statement with your code
		int counter = 0;
		Node current = this.first;
		while(current!=null)
		{
			if(current.block==block)
			{
				return counter;
			}
			current = current.next;
			counter++;
		}
		return -1;
	}

	/**
	 * Removes the given node from this list.	
	 * 
	 * @param node
	 *        the node that will be removed from this list
	 */
	public void remove(Node node) {
		Node prev =null;
		ListIterator itr = this.iterator();
		Node current = itr.current;
		if (node == null || this.first == null) {
			throw new IllegalArgumentException(" NullPointerException!");
			// Nothing to remove - edge case
		}
		if(this.first.block.equals(node.block))
		{
			this.first =this.first.next;
			if(this.first==null)
			{
				this.last = this.first; //edge case 1 element in the list
			}
		}
		else
		{
			while(!itr.current.next.block.equals(node.block))
			{
				itr.next();
			}
			itr.current.next = itr.current.next.next;
			if(itr.current.next==null)
			{
				this.last = itr.current;
			}
		}
		this.size--;
	}

	/**
	 * Removes from this list the node which is located at the given index.
	 * 
	 * @param index the location of the node that has to be removed.
	 * @throws IllegalArgumentException
	 *         if index is negative or greater than or equal to size
	 */
	public void remove(int index) {
		//// Write your code here
		ListIterator itr = this.iterator();
		Node current = itr.current;
		if(index < 0 || index>this.size)
		{
			throw new IllegalArgumentException(
				"index must be between 0 and size");
		}
		if(this.first.block.equals(this.getBlock(index))){
			this.first=this.first.next;
			if(this.first==null){
				this.last=this.first;
			}
		}else{		
			
		while(!itr.current.next.block.equals(this.getBlock(index))){		
			itr.next();
		}
		itr.current.next = itr.current.next.next;
		if(itr.current.next==null){
			this.last=itr.current;
		}
	}
		this.size--;
	}
	/**
	 * Removes from this list the node pointing to the given memory block.
	 * 
	 * @param block the memory block that should be removed from the list
	 * @throws IllegalArgumentException
	 *         if the given memory block is not in this list
	 */
	public void remove(MemoryBlock block) {
		//// Write your code here
		Node prev =null;
		ListIterator itr = this.iterator();
		Node current = itr.current;
		if (block == null || this.first == null) {
			throw new IllegalArgumentException("index must be between 0 and size");
			// Nothing to remove - edge case
		}
		if(this.indexOf(block)==-1)
		{
			throw new IllegalArgumentException("index must be between 0 and size");
		}
		if(this.first.block.equals(block))
		{
			this.first =this.first.next;
			if(this.first==null)
			{
				this.last = this.first; //edge case 1 element in the list
			}
		}
		else
		{
			while(!itr.current.next.block.equals(block))
			{
				itr.next();
			}
			itr.current.next = itr.current.next.next;
			if(itr.current.next==null)
			{
				this.last = itr.current;
			}
		}
		this.size--;		
	}	

	/**
	 * Returns an iterator over this list, starting with the first element.
	 */
	public ListIterator iterator(){
		return new ListIterator(first);
	}
	
	/**
	 * A textual representation of this list, for debugging.
	 */
	public String toString() {
		//// Replace the following statement with your code
// and builds the string incrementally
	ListIterator itr = this.iterator();
	String str = "";
	while (itr.hasNext()) {
	str += "(" + itr.current.block.baseAddress + " , " + itr.current.block.length + ") ";
	itr.next();
	}
	return str;
	}

}