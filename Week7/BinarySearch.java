package Labs.Week7;

public class BinarySearch {
    private Node head;

    private static class Node {
        int value;
        Node next;

        Node(int value) {
            this.value = value;
        }
    }

    public void insertSorted(int value) {
        Node newNode = new Node(value);
        if (head == null || value <= head.value) {
            newNode.next = head;
            head = newNode;
            return;
        }

        Node current = head;
        while (current.next != null && current.next.value < value) {
            current = current.next;
        }
        newNode.next = current.next;
        current.next = newNode;
    }

    public Node binarySearch(int target) {
        Node start = head;
        Node end = null;

        while (start != end) {
            Node mid = middleNode(start, end);
            if (mid == null) {
                return null;
            }

            if (mid.value == target) {
                return mid;
            } else if (mid.value < target) {
                start = mid.next;
            } else {
                end = mid;
            }
        }
        return null;
    }

    private Node middleNode(Node start, Node end) {
        if (start == null) {
            return null;
        }

        Node slow = start;
        Node fast = start;
        while (fast != end) {
            fast = fast.next;
            if (fast != end) {
                fast = fast.next;
                slow = slow.next;
            }
        }
        return slow;
    }

    public void printList() {
        Node current = head;
        while (current != null) {
            System.out.print(current.value + " ");
            current = current.next;
        }
        System.out.println();
    }

    public static void main(String[] args) {
        BinarySearch list = new BinarySearch();
        int[] data = {10, 4, 7, 3, 12, 15, 2, 5};
        for (int value : data) {
            list.insertSorted(value);
        }

        System.out.print("Sorted List: ");
        list.printList();

        int target = 12;
        Node result = list.binarySearch(target);
        if (result != null) {
            System.out.println("Value " + target + " found in the list.");
        } else {
            System.out.println("Value " + target + " not found in the list.");
        }
    }
}
