package lab9;

import java.util.Iterator;
import java.util.Set;
import java.util.TreeSet;

/**
 * Implementation of interface Map61B with BST as core data structure.
 *
 * @author Your name here
 */
public class BSTMap<K extends Comparable<K>, V> implements Map61B<K, V> {

    private class Node {
        /* (K, V) pair stored in this Node. */
        private K key;
        private V value;

        /* Children of this Node. */
        private Node left;
        private Node right;

        private Node(K k, V v) {
            key = k;
            value = v;
        }
    }

    private Node root;  /* Root node of the tree. */
    private int size; /* The number of key-value pairs in the tree */

    /* Creates an empty BSTMap. */
    public BSTMap() {
        this.clear();
    }

    /* Removes all of the mappings from this map. */
    @Override
    public void clear() {
        root = null;
        size = 0;
    }

    /** Returns the value mapped to by KEY in the subtree rooted in P.
     *  or null if this map contains no mapping for the key.
     */
    private V getHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.key.compareTo(key) < 0) {
            return getHelper(key, p.right);
        } else if (p.key.compareTo(key) == 0) {
            return p.value;
        } else {
            return getHelper(key, p.left);
        }

    }

    /** Returns the value to which the specified key is mapped, or null if this
     *  map contains no mapping for the key.
     */
    @Override
    public V get(K key) {
        return getHelper(key, root);
    }

    /** Returns a BSTMap rooted in p with (KEY, VALUE) added as a key-value mapping.
      * Or if p is null, it returns a one node BSTMap containing (KEY, VALUE).
     */
    private Node putHelper(K key, V value, Node p) {
        if (p == null) {
            Node res = new Node(key, value);
            if (size == 0) {
                root = res;
            }
            size += 1;
            return res;
        } else if (p.key.compareTo(key) < 0) {
            if (p.right == null) {
                p.right = new Node(key, value);
                size += 1;
                return p.right;
            } else {
                return putHelper(key, value, p.right);
            }
        } else if (p.key.compareTo(key) == 0) {
            p.value = value;
            return p;
        } else {
            if (p.left == null) {
                p.left = new Node(key, value);
                size += 1;
                return p.left;
            } else {
                return putHelper(key, value, p.left);
            }
        }
    }

    /** Inserts the key KEY
     *  If it is already present, updates value to be VALUE.
     */
    @Override
    public void put(K key, V value) {
        putHelper(key, value, root);
    }

    /* Returns the number of key-value mappings in this map. */
    @Override
    public int size() {
        return size;
    }

    //////////////// EVERYTHING BELOW THIS LINE IS OPTIONAL ////////////////

    /* Returns a Set view of the keys contained in this map. */
    private void KeySetHelper(Set<K> setK, Node p) {
        if (p == null) {
            return;
        } else {
            setK.add(p.key);
            KeySetHelper(setK, p.left);
            KeySetHelper(setK, p.right);
        }
    }

    @Override
    public Set<K> keySet() {
        Set<K> res = new TreeSet<>();
        KeySetHelper(res, root);
        return res;
    }

    /** Removes KEY from the tree if present
     *  returns VALUE removed,
     *  null on failed removal.
     */
    private Node getSmallestBig(Node p) {
        if (p == null || p.right == null) {
            return null;
        } else {
            Node res = p.right;
            while (res.left != null) {
                res = res.left;
            }
            return res;
        }
    }

    private V removeHelper(K key, Node p) {
        if (p == null) {
            return null;
        } else if (p.key.compareTo(key) < 0) {
            return removeHelper(key, p.right);
        } else if (p.key.compareTo(key) == 0) {
            System.out.println("find and remove");
            size -= 1;
            V res = p.value;
            Node nextNode = getSmallestBig(p);
            if (nextNode == null) {
                p = p.right;
            } else {
                p.value = nextNode.value;
                nextNode = null;
            }
            return res;
        } else {
            return removeHelper(key, p.left);
        }
    }

    @Override
    public V remove(K key) {
        return removeHelper(key, root);
    }

    /** Removes the key-value entry for the specified key only if it is
     *  currently mapped to the specified value.  Returns the VALUE removed,
     *  null on failed removal.
     **/
    @Override
    public V remove(K key, V value) {
        throw new UnsupportedOperationException();
    }

    @Override
    public Iterator<K> iterator() {
        throw new UnsupportedOperationException();
    }

    public static void main(String[] args) {
        BSTMap<Integer, Double> bst = new BSTMap<>();
        bst.put(1, 1.1);
        bst.put(2, 0.5);
        bst.put(2, 0.7);
        bst.remove(2);
        System.out.println(bst.keySet());
        System.out.println(bst.get(2));
        System.out.println(bst.size());
    }
}
