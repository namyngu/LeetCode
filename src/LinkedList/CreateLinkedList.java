package LinkedList;


// Class to generate a linked list for troubleshooting
public class CreateLinkedList {
    public ListNode head;

    public CreateLinkedList(){}

    public CreateLinkedList(int[] array) {
        if (array.length == 0) {
            head = null;
            return;
        }

        head = new ListNode(array[0]);

        ListNode tracker = new ListNode();
        for (int i = 1; i < array.length; i++) {
            if (i == 1) {
                tracker.val = array[1];
                head.next = tracker;
            }
            else {
                tracker.next = new ListNode(array[i]);
                tracker = tracker.next;
            }

        }
    }

    public void printList() {
        ListNode node = head;
        String str = "";
        while (node != null) {
            str = str + node.val + ", ";
            node = node.next;
        }

        System.out.println(str);
        System.out.println("Head: " + head.val);
    }
}
