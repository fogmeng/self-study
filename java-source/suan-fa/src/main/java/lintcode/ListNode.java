package lintcode;

public class ListNode {
        public int val;
        public ListNode next;

        @Override
        public String toString() {
            return "ListNode{" +
                    "val=" + val +
                    ", next=" + next +
                    '}';
        }

        public ListNode(int x) {
            val = x;
        }
    }