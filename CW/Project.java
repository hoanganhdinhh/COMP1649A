package Labs.CW;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Deque;
import java.util.List;
import java.util.Objects;

public class Project {

    static final class BookItem {
        final String title;
        final String author;
        final int quantity;

        BookItem(String title, String author, int quantity) {
            this.title = Objects.requireNonNull(title);
            this.author = Objects.requireNonNull(author);
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return String.format("%s by %s x%d", title, author, quantity);
        }
    }

    static final class Order {
        final int id;
        final String customerName;
        final String shippingAddress;
        final List<BookItem> items = new ArrayList<>();
        final Deque<String> statusHistory = new ArrayDeque<>();

        Order(int id, String customerName, String shippingAddress) {
            this.id = id;
            this.customerName = Objects.requireNonNull(customerName);
            this.shippingAddress = Objects.requireNonNull(shippingAddress);
            pushStatus("Created");
        }

        void addItem(BookItem book) {
            items.add(book);
        }

        void pushStatus(String status) {
            statusHistory.push(status);
        }

        String popStatus() {
            if (statusHistory.isEmpty()) {
                throw new IllegalStateException("No status to revert");
            }
            return statusHistory.pop();
        }

        String peekStatus() {
            return statusHistory.peek();
        }

        @Override
        public String toString() {
            return String.format("Order #%d (%s)", id, customerName);
        }
    }

    /* ---------- Stack ADT ---------- */

    interface StackADT<T> {
        void push(T value);
        T pop();
        T peek();
        boolean isEmpty();
        int size();
    }

    static final class ArrayStack<T> implements StackADT<T> {
        private final List<T> data = new ArrayList<>();

        @Override
        public void push(T value) {
            data.add(value);
        }

        @Override
        public T pop() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack underflow");
            }
            return data.remove(data.size() - 1);
        }

        @Override
        public T peek() {
            if (isEmpty()) {
                throw new IllegalStateException("Stack is empty");
            }
            return data.get(data.size() - 1);
        }

        @Override
        public boolean isEmpty() {
            return data.isEmpty();
        }

        @Override
        public int size() {
            return data.size();
        }
    }

    /* ---------- FIFO Queue ---------- */

    static final class OrderQueue {
        private final Deque<Order> queue = new ArrayDeque<>();

        void enqueue(Order order) {
            queue.addLast(order);
        }

        Order dequeue() {
            if (queue.isEmpty()) {
                throw new IllegalStateException("No orders in queue");
            }
            return queue.removeFirst();
        }

        Order peek() {
            return queue.peekFirst();
        }

        boolean isEmpty() {
            return queue.isEmpty();
        }

        int size() {
            return queue.size();
        }
    }

    /* ---------- Sorting Algorithms ---------- */

    static void insertionSortBooks(List<BookItem> books, Comparator<BookItem> comparator) {
        for (int i = 1; i < books.size(); i++) {
            BookItem key = books.get(i);
            int j = i - 1;
            while (j >= 0 && comparator.compare(books.get(j), key) > 0) {
                books.set(j + 1, books.get(j));
                j--;
            }
            books.set(j + 1, key);
        }
    }

    static void selectionSortOrders(List<Order> orders, Comparator<Order> comparator) {
        for (int i = 0; i < orders.size() - 1; i++) {
            int minIndex = i;
            for (int j = i + 1; j < orders.size(); j++) {
                if (comparator.compare(orders.get(j), orders.get(minIndex)) < 0) {
                    minIndex = j;
                }
            }
            if (minIndex != i) {
                Order tmp = orders.get(i);
                orders.set(i, orders.get(minIndex));
                orders.set(minIndex, tmp);
            }
        }
    }

    /* ---------- Searching Algorithms ---------- */

    static Order linearSearchById(List<Order> orders, int targetId) {
        for (Order order : orders) {
            if (order.id == targetId) {
                return order;
            }
        }
        return null;
    }

    static Order binarySearchById(List<Order> sortedOrders, int targetId) {
        int left = 0;
        int right = sortedOrders.size() - 1;
        while (left <= right) {
            int mid = (left + right) >>> 1;
            Order midOrder = sortedOrders.get(mid);
            if (midOrder.id == targetId) {
                return midOrder;
            } else if (midOrder.id < targetId) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return null;
    }

    /* ---------- Demo Flow ---------- */

    public static void main(String[] args) {
        OrderQueue orderQueue = new OrderQueue();

        Order order1 = buildSampleOrder(
                1001,
                "Sofia Turner",
                "21 Library Way, London",
                new BookItem("Clean Code", "Robert C. Martin", 1),
                new BookItem("The Pragmatic Programmer", "Andrew Hunt", 2),
                new BookItem("Algorithms", "Sedgewick", 1)
        );

        Order order2 = buildSampleOrder(
                1002,
                "Jake Finch",
                "55 Oak Lane, Newcastle",
                new BookItem("Design Patterns", "GoF", 1),
                new BookItem("Refactoring", "Martin Fowler", 1)
        );

        Order order3 = buildSampleOrder(
                1003,
                "Priya Singh",
                "88 Sunset Blvd, Birmingham",
                new BookItem("Effective Java", "Joshua Bloch", 1),
                new BookItem("Domain-Driven Design", "Eric Evans", 1),
                new BookItem("Clean Architecture", "Robert C. Martin", 1)
        );

        orderQueue.enqueue(order1);
        orderQueue.enqueue(order2);
        orderQueue.enqueue(order3);

        System.out.println("Processing queue:");
        while (!orderQueue.isEmpty()) {
            Order current = orderQueue.dequeue();
            processOrder(current);
        }

        List<Order> archivedOrders = Arrays.asList(order1, order2, order3);
        demonstrateSearching(archivedOrders);
    }

    private static Order buildSampleOrder(int id, String customer, String address, BookItem... books) {
        Order order = new Order(id, customer, address);
        for (BookItem book : books) {
            order.addItem(book);
        }
        return order;
    }

    private static void processOrder(Order order) {
        order.pushStatus("Inventory check");
        order.pushStatus("Payment confirmed");
        order.pushStatus("Ready for dispatch");

        System.out.println(order + " current status: " + order.peekStatus());
        insertionSortBooks(order.items, Comparator.comparing(book -> book.title));
        System.out.println(" Sorted item list:");
        order.items.forEach(item -> System.out.println("  - " + item));

        String revertedStatus = order.popStatus();
        System.out.println(" Reverted status: " + revertedStatus);
        System.out.println(" Stack size now: " + order.statusHistory.size());
        System.out.println();
    }

    private static void demonstrateSearching(List<Order> orders) {
        List<Order> mutableOrders = new ArrayList<>(orders);
        selectionSortOrders(mutableOrders, Comparator.comparingInt(order -> order.id));

        int targetId = 1002;
        Order linearResult = linearSearchById(mutableOrders, targetId);
        System.out.println("Linear search found: " + linearResult);

        Order binaryResult = binarySearchById(mutableOrders, targetId);
        System.out.println("Binary search found: " + binaryResult);
    }
}
