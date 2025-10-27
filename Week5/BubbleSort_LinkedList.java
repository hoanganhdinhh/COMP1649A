package Labs.Week5;

class ListNode {
    int val;
    ListNode next;
    
    ListNode(int val) {
        this.val = val;
        this.next = null;
    }
}

public class BubbleSort_LinkedList {
    public static ListNode bubbleSort(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        
        boolean swapped;
        do {
            swapped = false;
            ListNode current = head;
            
            while (current.next != null) {
                if (current.val > current.next.val) {
                    // Swap values
                    int temp = current.val;
                    current.val = current.next.val;
                    current.next.val = temp;
                    swapped = true;
                }
                current = current.next;
            }
        } while (swapped);
        
        return head;
    }
    
    public static void printList(ListNode head) {
        ListNode current = head;
        while (current != null) {
            System.out.print(current.val + " ");
            current = current.next;
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        // Create linked list: 5 -> 2 -> 8 -> 1 -> 3 -> 7
        ListNode head = new ListNode(5);
        head.next = new ListNode(2);
        head.next.next = new ListNode(8);
        head.next.next.next = new ListNode(1);
        head.next.next.next.next = new ListNode(3);
        head.next.next.next.next.next = new ListNode(7);
        
        head = bubbleSort(head);

        System.out.println("Sorted linked list:");
        printList(head);
    }
}
