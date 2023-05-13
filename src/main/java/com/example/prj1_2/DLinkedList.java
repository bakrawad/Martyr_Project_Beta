package com.example.prj1_2;

public class DLinkedList <T extends Comparable<T>>{
    private NodeD<T> head;
    private String location;


    public NodeD<T> getHead() {
        return head;
    }

    public void setHead(NodeD<T> head) {
        this.head = head;
    }

    public DLinkedList(){
        NodeD<T>dummy = new NodeD<>(null);
        head = dummy;
    }
    public void insertAtHead(T data){
        NodeD<T> node = new NodeD<>(data);
        if (head.getNext()==null){
            head.setNext(node);
            node.setPrev(head);
        }else {
            node.setNext(head.getNext());
            node.setPrev(head);
            head.getNext().setPrev(node);
            head.setNext(node);
        }
    }
    public void insertSorted(T data){
        NodeD<T> node = new NodeD<>(data);
        NodeD<T>curr = head;
        while(curr.getNext()!=null && curr.getNext().getData().compareTo(data)<0)
            curr = curr.getNext();
            if(curr.getNext()!=null){   //not last item
                curr.getNext().setPrev(node);
                node.setNext(curr.getNext());
            }
            node.setPrev(curr);
            curr.setNext(node);

    }
    public T Delete(T data){
        NodeD<T> curr = head;
        while (curr.getNext()!=null){
            curr = curr.getNext();
            if (curr.getData().equals(data)){
                T temp = curr.getData();
                if (curr.getNext()==null){
                    curr.getPrev().setNext(null);
                    return temp;
                }else {
                    curr.getPrev().setNext(curr.getNext());
                    curr.getNext().setPrev(curr.getPrev());
                    return temp;
                }
            }
        }

        return null;
    }
    public T Deleteloc(T data){
        NodeD<T> curr = head;
        while (curr.getNext()!=null){
            curr = curr.getNext();
            if (curr.getData().compareTo(data)==0){
                T temp = curr.getData();
                if (curr.getNext()==null){
                    curr.getPrev().setNext(null);
                    return temp;
                }else {
                    curr.getPrev().setNext(curr.getNext());
                    curr.getNext().setPrev(curr.getPrev());
                    return temp;
                }
            }
        }

        return null;
    }

    public T Update(T data,T ndate){
        NodeD<T> curr = head;
        while (curr.getNext()!=null){
            curr = curr.getNext();
            if (curr.getData().equals(data)){
                T temp = curr.getData();
                curr.setData(ndate);
                return temp;
            }
        }
        return null;

    }
    public boolean find (T data){
        NodeD <T>curr= head;
        while (curr.getNext()!=null){
            curr=curr.getNext();
            if (curr.getData().equals(data)){
                return true;
            }
        }
        return false;
    }
    public boolean findLoc (T data){
        NodeD <T>curr= head;
        while (curr.getNext()!=null){
            curr=curr.getNext();
            if (curr.getData().compareTo(data)==0){
                return true;
            }
        }
        return false;
    }
    public boolean findSt (T data){
        NodeD <T>curr= head;
        while (curr.getNext()!=null){
            curr=curr.getNext();
            if (((String)curr.getData()).equalsIgnoreCase(((String)data))){
                return true;
            }
        }
        return false;
    }

    public NodeD<T> findNode (T data){
        NodeD<T> curr= head.getNext();
        while (curr !=null){

            if (curr.getData().compareTo(data) == 0){
                return curr;
            }
            curr=curr.getNext();
        }
        return null;
    }

    public String print(){
        NodeD curr= head;
        String s = "head -->";
        while (curr.getNext()!=null){
            curr = curr.getNext();
            s+=curr.getData()+"-->";
        }
        s+="Null";
        return s;
    }
    public int size(){
        NodeD<T>curr = head;
        int n =0;
        while (curr.getNext()!=null){
            curr = curr.getNext();
            n++;
        }
        return n;
    }
    public T get(int n){
        NodeD<T> curr= head.getNext();
        for (int i =0;i<n;i++){
            curr = curr.getNext();
        }
        T t = curr.getData();
        return t;
    }
    public T UpdateLoc (T data,T ndata){
        NodeD <T>curr= head;
        while (curr.getNext()!=null){
            curr=curr.getNext();
            if (curr.getData().compareTo(data)==0){
                T temp = curr.getData();

                curr.setData(ndata);
                return temp;
            }
        }
        return null;
    }





    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }





}
