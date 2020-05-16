package synthesizer;

// to-do: Make sure to make this class a part of the synthesizer package
// package <package name>;
import java.util.Iterator;

//to-do: Make sure to make this class and all of its methods public
//to-do: Make sure to make this class extend AbstractBoundedQueue<t>
public class ArrayRingBuffer<T> extends AbstractBoundedQueue<T> {
    /* Index for the next dequeue or peek. */
    private int first;            // index for the next dequeue or peek
    /* Index for the next enqueue. */
    private int last;
    /* Array for storing the buffer data. */
    private T[] rb;

    /**
     * Create a new ArrayRingBuffer with the given capacity.
     */
    public ArrayRingBuffer(int capacity) {
        // to-do: Create new array with capacity elements.
        //       first, last, and fillCount should all be set to 0.
        //       this.capacity should be set appropriately. Note that the local variable
        //       here shadows the field we inherit from AbstractBoundedQueue, so
        //       you'll need to use this.capacity to set the capacity.
        first = 0;
        last = 0;
        fillCount = 0;
        this.capacity = capacity;
        rb = (T[]) new Object[this.capacity];
    }

    /**
     * Adds x to the end of the ring buffer. If there is no room, then
     * throw new RuntimeException("Ring buffer overflow"). Exceptions
     * covered Monday.
     */
    private int addOne(int x) {
        if (x + 1 == this.capacity) {
            return 0;
        }
        return x + 1;
    }

    @Override
    public void enqueue(T x) {
        // to-do: Enqueue the item. Don't forget to increase fillCount and update last.
        if (isFull()) {
            throw new RuntimeException("Ring Buffer Overflow");
        } else {
            rb[last] = x;
            last = addOne(last);
            fillCount += 1;
        }
    }

    /**
     * Dequeue oldest item in the ring buffer. If the buffer is empty, then
     * throw new RuntimeException("Ring buffer underflow"). Exceptions
     * covered Monday.
     */
    @Override
    public T dequeue() {
        // to-do: Dequeue the first item. Don't forget to decrease fillCount and update
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        } else {
            T item = rb[first];
            rb[first] = null;
            first = addOne(first);
            fillCount -= 1;
            return item;
        }
    }

    /**
     * Return oldest item, but don't remove it.
     */
    @Override
    public T peek() {
        // to-do: Return the first item. None of your instance variables should change.
        if (isEmpty()) {
            throw new RuntimeException("Ring Buffer Underflow");
        } else {
            return rb[first];
        }
    }

    // to-do: When you get to part 5, implement the needed code to support iteration.
    public class IndexIterator implements Iterator<T> {
        private int ptr;

        public IndexIterator() {
            ptr = first;
        }

        @Override
        public boolean hasNext() {
            return (ptr != last);
        }

        @Override
        public T next() {
            T returnItem = rb[ptr];
            ptr = addOne(ptr);
            return returnItem;
        }
    }

    @Override
    public Iterator<T> iterator() {
        return new IndexIterator();
    }
}
