package com.example.prj1_2;

public class SLinkedList <T extends Comparable<T>>{
    private Node<T> head;


    public Node<T> getHead() {
        return head;
    }

    public void setHead(Node<T> head) {
        this.head = head;
    }

    public SLinkedList(T data){
      Node<T>dummy= new Node<>(null);
      head = dummy;
        Insertsorted(data);

    }
    public SLinkedList(){
        Node<T>dummy= new Node<>(null);
        head = dummy;
    }
    public void print(){
        String x="";
        Node<T>curr = head;
        System.out.print("head");
        while (curr.getNext() !=null){
            curr=curr.getNext();
            System.out.print(("-->"+curr.getData()));

        }
        System.out.println(("-->"+"NULL"));

    }
    public void Insertsorted(T data) {
        Node<T> cur = head;
        Node<T> prv = head;
        Node<T> node = new Node(data);
        if (cur.getNext() == null) {
            cur.setNext(node);
            return;
        }
        if (cur.getNext().getData().compareTo(node.getData()) >= 0) {
            node.setNext(cur.getNext());
            head.setNext(node);
        } else {
            while (cur.getNext() != null) {
                prv = cur;
                cur = cur.getNext();
                if (cur.getData().compareTo(node.getData()) < 0 && cur.getNext() == null) {
                    cur.setNext(node);
                    return;
                }
                if (cur.getData().compareTo(node.getData()) >= 0) {
                    node.setNext(cur);
                    prv.setNext(node);
                    return;
                }

            }
        }
    }
    public T Delete(T data){
        Node<T> curr= head;
        Node<T> prev = head;
        if (curr.equals(data)){
            T temp = data;
            curr.setNext(curr.getNext().getNext());
            return data;
        }else {
            while (curr.getNext()!=null){
                prev = curr;
                curr=curr.getNext();
                if (curr.getData().equals(data)) {
                    T temp = data;
                    if (curr.getNext()==null){
                        prev.setNext(null);

                    }else {
                        prev.setNext(curr.getNext());
                    }
                    return temp;
                }
            }
        }

        return null;
    }
    public T Update (T data,T ndata){
        Node <T>curr= head;
        while (curr.getNext()!=null){
            curr=curr.getNext();
            if (curr.getData().equals(data)){
                T temp = curr.getData();

                curr.setData(ndata);
                return temp;
            }
        }
        return null;
    }

    public boolean find (T data){
        Node <T>curr= head;
        while (curr.getNext()!=null){
            curr=curr.getNext();
            if (curr.getData().equals(data)){
                return true;
            }
        }
        return false;
    }
    public Node<T> findNode (T data){
        Node<T> curr= head.getNext();
        while (curr !=null){

            if (curr.getData().compareTo(data) == 0){
                return curr;
            }
            curr=curr.getNext();
        }
        return null;
    }
    public String printS(){
        Node curr= head;
        String s = "head -->";
        while (curr.getNext()!=null){
            curr = curr.getNext();
            s+=curr.getData()+"-->";
        }
        s+="Null";
        return s;
    }
    public T get(int n){
        Node<T> curr= head.getNext();
        for (int i =0;i<n;i++){
            curr = curr.getNext();
        }
        T t = curr.getData();
        return t;
    }
    public int size(){
        Node<T>curr = head;
        int n =0;
        while (curr.getNext()!=null){
            curr = curr.getNext();
            n++;
        }
        return n;
    }
}
