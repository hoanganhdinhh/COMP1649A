package Week3;

public class LinkListStack {
    private LinkedNode top;
    public LinkListStack() {
        this.top = null;
    }
    public int Push(LinkedNode pNode){
        if (this.top != null){
            pNode.next = this.top;
        }
        this.top = pNode;
        return 0;
    }
    public LinkedNode Pop(){
        if (this.top == null){
            return null;
        }
        LinkedNode pN = this.top;
        this.top = this.top.next;
        pN.next = null;
        return pN;
    }
    public int PrintStack(){
        LinkedNode pL = this.top;
        while (pL != null){
            System.out.println(" " + pL.data);
            pL = pL.next;
        }
        return 0;
    }

    public static void main(String[] args) {
        int dArr[] = {12,18,31,22,49};
        LinkListStack myStack = new LinkListStack();
        LinkedNode pL;

        System.out.println("Push data to stack:");
        for (int i = 0; i < 5; i++){
            LinkedNode pN = new LinkedNode(dArr[i]);
            myStack.Push(pN);
        }
        myStack.PrintStack();

        System.out.println("Pop data from stack:");
        for (int i = 0; i < 5; i++){
            pL = myStack.Pop();
            if (pL != null){
                System.out.println(" " + pL.data);
            }
        }
    }
}

class LinkedNode {
    int data;
    LinkedNode next;

    public LinkedNode(int data) {
        this.data = data;
        this.next = null;
    }
}
