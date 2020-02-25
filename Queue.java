/*
 * Mark Chesney
 * ACM: Association for Computing Machinery, Seattle University
 * This is free and unencumbered software released into the public domain.
 */

/**
    Generates a generic queue using doubly-linked lists.
*/

public class Queue<T> {

    /**
     * Node of the doubly-linked list.
     */
    private class Node<T> {
        T value;            // value stored in Node.
        Node<T> next;		// Reference to succeeding node of list.
        Node<T> prev;		// Reference to preceding node of list.

        /**
         * Constructor of new Node in list.
         * @param val number stored in node.
         * @param n  reference to succeeding node of list.
         * @param p  reference to preceding node of list.
         */
        Node(T val, Node<T> n, Node<T> p) {
            value = val;
            next = n;
            prev = p;
        }
    }

    // private member variables
    // default Node objects used in doubly-linked list queue.
    private Node<T> head;
    private Node<T> tail;	// tail is needed for queue structures.


    /**
	   Constructor.
	*/
	public Queue()
	{
        head = null;
        tail = null;
	}


    /**
       The method enqueue adds a value to the queue.
       @param val number stored in node.
    */
	public void enqueue(T val)
    {
        if (tail != null)
        {
			// add value to a queue that's NOT empty
			tail.next = new Node<T>(val, null, tail);
			tail = tail.next;
        }
        else
        {
			// add val to an empty queue
            head = new Node<T>(val, null, null);
            tail = head;
        }
    }


    /**
       The dequeue method removes and returns item at the head of the queue.
       @return item at head of queue.
		 @exception EmptyQueueException When the queue is empty.
    */
    public T dequeue()
    {
       if (empty()) {
           throw new IllegalArgumentException("cannot dequeue from "
                   + "empty queue");
       }
       else {
			T value = head.value;
			head = head.next;  // move next item down the queue
			if (head == null) {
                // if item being removed is last one in list
				tail = null;  //  then the queue has been emptied.
							  // prevent tail from pointing to removed item
            } else {
                head.prev = null;
            }
			return value;
       }
    }


    /**
       The method peek returns value at the head of the queue.
       @return  item at head of queue.
		 @exception EmptyQueueException When the queue is empty.
    */
    public T peek()
    {
        if (empty())
            throw new IllegalArgumentException("Empty Queue!");
        else
            return head.value;
    }


    /**
       The empty method checks to see if the queue is empty.
       @return  true if and only if queue is empty.
    */
    public boolean empty()
    {
        return head == null;
    }


    /**
     * Method produces string representation of Queue object.
     * @return  a queue of values, in string form.
	 */
	public String toString()
	{
	    StringBuilder sBuilder = new StringBuilder();
	    Node<T> p = head;
	    while (p != null)
	    {
	        sBuilder.append(p.value);
	        p = p.next;
	        if (p != null)
				   sBuilder.append(" | ");
	    }
	    return sBuilder.toString();
	}


    /**
     * Enqueue all the elements from another queue onto this queue.
     * @param other  the queue with the elements to enqueue
     */
	public void append(Queue<T> other) {
        for (Node<T> p = other.head; p != null; p = p.next)
            enqueue(p.value);
	}


    /**
     * Makes a deep copy of the queue, without altering original queue.
     * @return  the copied queue.
     */
	public Queue<T> copy() {
        Queue<T> theCopy = new Queue<T>();

		// Walk down the list and peek at each value.
		Node<T> p = head;
		// while iterating through current queue:
		while (p != null) {
			theCopy.enqueue(p.value);  // build queue in copied object.
			p = p.next;
		}
		return theCopy;
	}


    public boolean equals(Queue<T> queue2) {

		// is exclusively one of them empty?
		if (empty() && queue2.empty())
			return true;

		Node<T> p = this.head;
		Node<T> q = queue2.head;

		// while queues have equal length...
		while (p != null && q != null) {

			// are two nodes unequal in value?
			if (p.value != q.value) {
				return false;
			}
			q = q.next;
			p = p.next;
		}

		return true;
	}

}
