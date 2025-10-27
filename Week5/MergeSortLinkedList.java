package Labs.Week5;

public class MergeSortLinkedList {

    // Node class for linked list
    static class Node {
        int data;
        Node next;

        Node(int data) {
            this.data = data;
            this.next = null;
        }
    }

    // Merge sort function
    public Node mergeSort(Node head) {
        if (head == null || head.next == null) {
            return head;
        }

        // Find middle using slow/fast pointer
        Node middle = getMiddle(head);
        Node nextOfMiddle = middle.next;
        middle.next = null;

        // Recursively sort left and right halves
        Node left = mergeSort(head);
        Node right = mergeSort(nextOfMiddle);

        // Merge sorted halves
        return merge(left, right);
    }

    // Get middle node using slow/fast pointer
    private Node getMiddle(Node head) {
        if (head == null) return head;

        Node slow = head;
        Node fast = head;
        Node prev = null;

        while (fast != null && fast.next != null) {
            prev = slow;
            slow = slow.next;
            fast = fast.next.next;
        }

        return prev;
    }

    // Merge two sorted linked lists
    private Node merge(Node left, Node right) {
        if (left == null) return right;
        if (right == null) return left;

        Node merged = null;
        if (left.data < right.data) {
            merged = left;
            merged.next = merge(left.next, right);
        } else {
            merged = right;
            merged.next = merge(left, right.next);
        }
        return merged;
    }

    // Helper method to print the linked list
    private static void printList(Node head) {
        Node current = head;
        while (current != null) {
            System.out.print(current.data + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        MergeSortLinkedList list = new MergeSortLinkedList();
        Node head = new Node(38);
        head.next = new Node(27);
        head.next.next = new Node(43);
        head.next.next.next = new Node(3);
        head.next.next.next.next = new Node(9);
        head.next.next.next.next.next = new Node(82);
        head.next.next.next.next.next.next = new Node(10);

        System.out.println("Original linked list:");
        printList(head);

        head = list.mergeSort(head);

        System.out.println("Sorted linked list:");
        printList(head);
    }
}
