package Week3;

public class Queue {
    private LinkedNode front;
    private LinkedNode rear;

    public Queue() {
        this.front = null;
        this.rear = null;
    }

    public int Enqueue(LinkedNode pNode){
        if (this.rear != null){
            this.rear.next = pNode;
        }
        this.rear = pNode;
        if (this.front == null){
            this.front = pNode;
        }
        return 0;
    }

    public LinkedNode Dequeue(){
        if (this.front == null){
            return null;
        }
        LinkedNode pN = this.front;
        this.front = this.front.next;
        if (this.front == null){
            this.rear = null;
        }
        pN.next = null;
        return pN;
    }

    public int PrintQueue(){
        LinkedNode pL = this.front;
        while (pL != null){
            System.out.println(" " + pL.data);
            pL = pL.next;
        }
        return 0;
    }

    public static void main(String[] args) {
        int dArr[] = {12,18,31,22,49};
        Queue myQueue = new Queue();
        LinkedNode pL;

        System.out.println("Enqueue data to queue:");
        for (int i = 0; i < 5; i++){
            LinkedNode pN = new LinkedNode(dArr[i]);
            myQueue.Enqueue(pN);
        }
        myQueue.PrintQueue();

        System.out.println("Dequeue data from queue:");
        for (int i = 0; i < 5; i++){
            pL = myQueue.Dequeue();
            if (pL != null){
                System.out.println(" " + pL.data);
            }
        }
    }
}
