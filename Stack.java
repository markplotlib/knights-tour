/*
 * Mark Chesney
 * ACM: Association for Computing Machinery, Seattle University
 * This is free and unencumbered software released into the public domain.
 */
import java.util.EmptyStackException;

/*
Generates a generic stack using linked lists.
*/
public class Stack<T>
{

	/**
    This class represents an exception
    thrown by pop and peek when the stack is empty.
	*/


	/**
	   The Node class is used to implement the linked list.
	*/
	public class Node<T>
	{
	    T value;
	    Node<T> next;
	    /**
	     * Node constructor
	     * @param val
	     * @param n
	     */
        Node(T val, Node<T> n)
	    {
	        value = val;
	        next = n;
	    }
	}

	private Node<T> top;  // Top of the stack


    /**
	   Constructor.
	*/
	public Stack()
	{
        top = null;
	}


	/**
	   The push method adds a new item to the stack.
	   @param n The item to be pushed onto the stack.
	*/
	public void push(T n)
	{
	    top = new Node<T>(n, top);
	}

	/**
	   The Pop method removes the value at the top of the stack.
	   @return The value at the top of the stack.
		 @exception EmptyStackException When the stack is empty.
	*/
	public T pop()
	{
	    if (empty())
	      throw new EmptyStackException();
	    else
	    {
	        T retValue = top.value;
	        top = top.next;
	        return retValue;
	    }
	}

    /**
       The size method returns the length of the stack.
       @return length of the stack.
         @exception EmptyStackException When the stack is empty.
    */
    public int size()
    {
        if (empty())
            return 0;
        int length = 0;
        Node<T> p = top;
	    while (p != null)
	    {
            length++;
	        p = p.next;
	    }
        return length;
    }

	/**
	   The peek method returns the top value
		 on the stack.
	   @return The value at the top of the stack.
		 @exception EmptyStackException When the
		 stack is empty.
	*/
	public T peek()
	{
	   if (empty())
	      throw new EmptyStackException();
	   else
	      return top.value;
	}


    /**
	   The empty method checks for an empty stack.
	   @return true if stack is empty, false otherwise.
	*/
	public boolean empty()
	{
	    return top == null;
	}


	/**
	   toString method computes a string representation of stack's contents.
	   @return The string representation of the stack contents.
	*/
	public String toString()
	{
	    StringBuilder sBuilder = new StringBuilder();
	    Node<T> p = top;
	    while (p != null)
	    {
	        sBuilder.append(p.value);
	        p = p.next;
	        if (p != null)
				sBuilder.append(" ");
	    }
	    return sBuilder.toString();
	}


    public Stack<T> copy() {
        Stack<T> tempCopy = new Stack<T>();     // temp, reversed order
        Stack<T> finalCopy = new Stack<T>();    // final, preserving sequence

		// Walk down the list and peek at each value.
		Node<T> p = top;
		while (p != null) {
			tempCopy.push(p.value);  // build queue in copied object.
			p = p.next;
		}

        // Repeat for final list
        Node<T> q = top;
        while (q != null) {
            finalCopy.push(q.value);  // build queue in copied object.
            q = q.next;
        }

		return finalCopy;
	}


    public boolean equals(Stack<T> stack2) {

		// is exclusively one of them empty?
		if (empty() && stack2.empty())
			return true;

		Node<T> p = this.top;
		Node<T> q = stack2.top;

		// while stacks have equal length...
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
