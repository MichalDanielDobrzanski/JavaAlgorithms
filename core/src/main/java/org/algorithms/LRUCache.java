package org.algorithms;

import java.util.HashMap;
import java.util.Map;

class LRUCache {

    private static class Node {
        int key, value;
        Node prev, next;

        Node(int key, int value) {
            this.key = key;
            this.value = value;
        }
    }


    // sorted in an increasing order. Latest are at the end.
    // O <--> 1 <--> 2
    private static class DoubleLinkedList {
        Node head;
        Node tail;

        DoubleLinkedList() {
            head = new Node(-1, -1);
            tail = new Node(-1, -1);
            head.next = tail;
            tail.prev = head;
        }

        public void remove(Node n) {
            // remove node
            if (n == null || n == head || n == tail) return;
            n.prev.next = n.next;
            n.next.prev = n.prev;
            n.prev = null;
            n.next = null;
        }

        // O(1)
        public void replace(Node node) {
            remove(node);
            insert(node);
        }

        public void insert(Node node) {
            // insert node to the end
            tail.prev.next = node;
            node.prev = tail.prev;
            node.next = tail;
            tail.prev = node;
        }

        public Node getMin() {
            return head.next;
        }

    }


    Map<Integer, Node> keyToNode;
    DoubleLinkedList nodes;
    int cap;

    public LRUCache(int capacity) {
        keyToNode = new HashMap<>();
        nodes = new DoubleLinkedList();
        cap = capacity;
    }

    public int get(int key) {
        if (!keyToNode.containsKey(key)) return -1;

        Node n = keyToNode.get(key);
        nodes.replace(n);

        return n.value;
    }

    public void put(int key, int value) {
        if (cap == 0) return;
        Node node = keyToNode.get(key);
        // existing node case
        if (node != null) {
            node.value = value;
            nodes.replace(node);
            return;
        }

        // full
        if (keyToNode.size() == cap) {
            // evict
            Node minNode = nodes.getMin();
            if (minNode != null) {
                keyToNode.remove(minNode.key);
                nodes.remove(minNode);
            }
        }

        Node newNode = new Node(key, value);
        keyToNode.put(key, newNode);
        nodes.insert(newNode);
    }
}

/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */