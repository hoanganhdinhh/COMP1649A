package Coursework;
import java.util.Scanner;

//NODE GENERIC
class Node<T> {
    T data;
    Node<T> next;

    public Node(T data) {
        this.data = data;
        this.next = null;
    }
}

//MODEL: ORDER
class Order implements Comparable<Order> {
    int orderId;
    String customerName;
    String shippingAddress;
    String book;

    public Order(int orderId, String customerName, String shippingAddress, String book) {
        this.orderId = orderId;
        this.customerName = customerName;
        this.shippingAddress = shippingAddress;
        this.book = book;
    }

    @Override
    public int compareTo(Order other) {
        // Sort by orderId ascending
        return Integer.compare(this.orderId, other.orderId);
    }

    @Override
    public String toString() {
        return "Order{id=" + orderId +
               ", customer='" + customerName + '\'' +
               ", address='" + shippingAddress + '\'' +
               ", book='" + book + '\'' +
               '}';
    }
}

//ORDER LIST (LINKED LIST)
class OrderList {
    private Node<Order> head;

    public boolean isEmpty() {
        return head == null;
    }

    public Node<Order> getHead() {
        return head;
    }

    public void setHead(Node<Order> newHead) {
        this.head = newHead;
    }

    // Add order to the end of the list
    public void addOrder(Order order) {
        Node<Order> newNode = new Node<>(order);
        if (head == null) {
            head = newNode;
            return;
        }
        Node<Order> current = head;
        while (current.next != null) {
            current = current.next;
        }
        current.next = newNode;
    }

    // Delete order by orderId
    public boolean deleteOrderById(int id) {
        if (head == null) return false;

        // If the head node is the one to delete
        if (head.data.orderId == id) {
            head = head.next;
            return true;
        }

        Node<Order> prev = head;
        Node<Order> current = head.next;

        while (current != null) {
            if (current.data.orderId == id) {
                prev.next = current.next;
                return true;
            }
            prev = current;
            current = current.next;
        }
        return false; // Không tìm thấy
    }

    // In tất cả đơn hàng
    public void printOrders() {
        if (head == null) {
            System.out.println("Danh sách đơn hàng đang trống.");
            return;
        }
        Node<Order> current = head;
        System.out.println("===== DANH SÁCH ĐƠN HÀNG =====");
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("================================");
    }
}

//MERGE SORT FOR LINKED LIST
class LinkedListSorter<T extends Comparable<T>> {

    public Node<T> mergeSort(Node<T> head) {
        if (head == null || head.next == null) return head;

        Node<T> middle = getMiddle(head);
        Node<T> nextOfMiddle = middle.next;

        middle.next = null; // Chia đôi list

        Node<T> left = mergeSort(head);
        Node<T> right = mergeSort(nextOfMiddle);

        return merge(left, right);
    }

    private Node<T> merge(Node<T> left, Node<T> right) {
        if (left == null)  return right;
        if (right == null) return left;

        Node<T> result;

        if (left.data.compareTo(right.data) <= 0) {
            result = left;
            result.next = merge(left.next, right);
        } else {
            result = right;
            result.next = merge(left, right.next);
        }
        return result;
    }

    // Tìm middle bằng slow/fast pointer
    private Node<T> getMiddle(Node<T> head) {
        if (head == null) return head;
        Node<T> slow = head;
        Node<T> fast = head;

        while (fast.next != null && fast.next.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        return slow;
    }
}

//SEARCH IN LINKED LIST (OPTIONAL)
class LinkedListSearch<T> {

    public Node<T> linearSearch(Node<T> head, T target) {
        Node<T> current = head;
        while (current != null) {
            if (current.data.equals(target)) {
                return current;
            }
            current = current.next;
        }
        return null;
    }
}

//STACK (LIFO) DÙNG NODE
class LinkedStack<T> {
    private Node<T> top;

    public boolean isEmpty() {
        return top == null;
    }

    public void push(T value) {
        Node<T> newNode = new Node<>(value);
        newNode.next = top;
        top = newNode;
    }

    public T pop() {
        if (isEmpty()) return null;
        T value = top.data;
        top = top.next;
        return value;
    }

    public T peek() {
        return isEmpty() ? null : top.data;
    }

    public void printStack() {
        if (isEmpty()) {
            System.out.println("Stack đang trống.");
            return;
        }
        System.out.println("===== STACK (Lịch sử đơn đã xử lý, TOP ở trên) =====");
        Node<T> current = top;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("=====================================================");
    }
}

//QUEUE (FIFO) DÙNG NODE
class LinkedQueue<T> {
    private Node<T> front, rear;

    public boolean isEmpty() {
        return front == null;
    }

    public void enqueue(T value) {
        Node<T> newNode = new Node<>(value);
        if (rear == null) {
            front = rear = newNode;
            return;
        }
        rear.next = newNode;
        rear = newNode;
    }

    public T dequeue() {
        if (isEmpty()) return null;
        T value = front.data;
        front = front.next;
        if (front == null) {
            rear = null;
        }
        return value;
    }

    public T peek() {
        return isEmpty() ? null : front.data;
    }

    public void printQueue() {
        if (isEmpty()) {
            System.out.println("Queue đang trống.");
            return;
        }
        System.out.println("===== QUEUE (Hàng chờ xử lý, FRONT -> REAR) =====");
        Node<T> current = front;
        while (current != null) {
            System.out.println(current.data);
            current = current.next;
        }
        System.out.println("==================================================");
    }
}

//MAIN APPLICATION + TERMINAL INTERFACE
public class BookstoreApp {

    private static final Scanner scanner = new Scanner(System.in);
    private static final OrderList orderList = new OrderList();
    private static final LinkedListSorter<Order> sorter = new LinkedListSorter<>();

    // Thêm vào luồng xử lý đơn:
    private static final LinkedQueue<Order> processingQueue = new LinkedQueue<>(); // FIFO
    private static final LinkedStack<Order> processedStack   = new LinkedStack<>(); // LIFO

    public static void main(String[] args) {
        // Demo khởi tạo vài đơn có sẵn
        initDemoData();

        int choice;
        do {
            printMenu();
            choice = inputInt("Choose an option: ");

            switch (choice) {
                case 1:
                    addOrderFromInput();
                    break;
                case 2:
                    deleteOrderFromInput();
                    break;
                case 3:
                    orderList.printOrders();
                    break;
                case 4:
                    sortAndShow();
                    break;
                case 5:
                    searchOrderFromInput();
                    break;
                case 6:
                    viewProcessingQueue();
                    break;
                case 7:
                    processNextOrder();
                    break;
                case 8:
                    viewProcessedStack();
                    break;
                case 0:
                    System.out.println("Exit the program..");
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }

        } while (choice != 0);
    }

    // SUPPORTING INTERFACE FUNCTIONS

    private static void printMenu() {
        System.out.println();
        System.out.println("=========== ORDER MANAGEMENT MENU (LINKED LIST + STACK + QUEUE) ===========");
        System.out.println("1. Add new order (automatically added to processing queue - Queue)");
        System.out.println("2. Delete order by ID (only from main list)");
        System.out.println("3. Display current order list");
        System.out.println("4. Sort order list by Order ID and display");
        System.out.println("5. Search order by ID in main list");
        System.out.println("6. View processing queue (Queue - FIFO)");
        System.out.println("7. Process next order (dequeue Queue, push into Stack)");
        System.out.println("8. View processed order history (Stack - LIFO)");
        System.out.println("0. Exit");
        System.out.println("============================================================================");
    }

    private static void initDemoData() {
        Order o1 = new Order(3, "Minh", "Hà Nội", "Java Programming");
        Order o2 = new Order(1, "Khang", "TP.HCM", "Data Structures");
        Order o3 = new Order(5, "Tuấn", "Đà Nẵng", "Algorithms");
        Order o4 = new Order(2, "Bảo", "Cần Thơ", "Database Systems");

        orderList.addOrder(o1);
        orderList.addOrder(o2);
        orderList.addOrder(o3);
        orderList.addOrder(o4);

        // Add all to processing queue (Queue)
        processingQueue.enqueue(o1);
        processingQueue.enqueue(o2);
        processingQueue.enqueue(o3);
        processingQueue.enqueue(o4);
    }

    private static void addOrderFromInput() {
        System.out.println("--- Add new order ---");
        int id = inputInt("Enter Order ID (integer): ");
        System.out.print("Enter customer name: ");
        String name = inputLine();
        System.out.print("Enter delivery address: ");
        String address = inputLine();
        System.out.print("Enter book name: ");
        String book = inputLine();

        Order order = new Order(id, name, address, book);
        orderList.addOrder(order);
        processingQueue.enqueue(order); // Add to processing queue

        System.out.println(">> Added: " + order);
        System.out.println(">> This order has been added to the processing queue (Queue).");
    }

    private static void deleteOrderFromInput() {
        System.out.println("--- Delete order ---");
        int id = inputInt("Enter Order ID to delete (from main list): ");
        boolean removed = orderList.deleteOrderById(id);
        if (removed) {
            System.out.println(">> Deleted order with ID = " + id + " from main list.");
            System.out.println(">> Note: not deleting from Queue/Stack in this demo.");
        } else {
            System.out.println(">> Order with ID = " + id + " not found.");
        }
    }

    private static void sortAndShow() {
        System.out.println("--- Sort order list by Order ID ---");
        if (orderList.isEmpty()) {
            System.out.println("The list is empty, nothing to sort.");
            return;
        }
        Node<Order> head = orderList.getHead();
        head = sorter.mergeSort(head);
        orderList.setHead(head);
        System.out.println(">> Sorted successfully.");
        orderList.printOrders();
    }

    private static void searchOrderFromInput() {
        System.out.println("--- Search order in main list ---");
        if (orderList.isEmpty()) {
            System.out.println("The list is empty.");
            return;
        }
        int id = inputInt("Enter Order ID to search: ");

        Node<Order> current = orderList.getHead();
        Order found = null;
        while (current != null) {
            if (current.data.orderId == id) {
                found = current.data;
                break;
            }
            current = current.next;
        }

        if (found != null) {
            System.out.println(">> Found: " + found);
        } else {
            System.out.println(">> Order with ID = " + id + " not found.");
        }
    }

    //STACK / QUEUE DEMO

    private static void viewProcessingQueue() {
        System.out.println("--- Processing Queue ---");
        processingQueue.printQueue();
    }

    private static void processNextOrder() {
        System.out.println("--- Process next order in Queue ---");
        if (processingQueue.isEmpty()) {
            System.out.println(">> The queue is empty, no orders to process.");
            return;
        }
        Order next = processingQueue.dequeue();
        System.out.println(">> Processing order: " + next);
        // After processing, push to history Stack
        processedStack.push(next);
        System.out.println(">> Order has been added to history (Stack).");
    }

    private static void viewProcessedStack() {
        System.out.println("--- Processed Order History (Stack) ---");
        processedStack.printStack();
    }

    //INPUT UTIL

    // Đọc int an toàn, tránh lỗi xuống dòng
    private static int inputInt(String message) {
        while (true) {
            try {
                System.out.print(message);
                String line = scanner.nextLine();
                return Integer.parseInt(line.trim());
            } catch (NumberFormatException ex) {
                System.out.println("Giá trị không hợp lệ, vui lòng nhập số nguyên.");
            }
        }
    }

    // Đọc cả dòng (kể cả có dấu cách)
    private static String inputLine() {
        String line = scanner.nextLine();
        return line.trim();
    }
}
