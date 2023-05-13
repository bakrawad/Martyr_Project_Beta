package com.example.prj1_2;

public class NodeD<T extends Comparable<T>> {
    private T data;
    private SLinkedList<T> list = new SLinkedList();
    private NodeD<T> next;
    private NodeD<T>prev;
    public NodeD(T data){
        this.data=data;
    }
    public void setData(T data){
        this.data=data;
    }
    public T getData(){
        return data;
    }

    public NodeD<T> getNext() {
        return next;
    }

    public void setNext(NodeD<T> next) {
        this.next = next;
    }

    public NodeD<T> getPrev() {
        return prev;
    }

    public void setPrev(NodeD<T> prev) {
        this.prev = prev;
    }

    public SLinkedList<T> getList() {
        return list;
    }

    public void setList(SLinkedList<T> list) {
        this.list = list;
    }

    public String printS(){
        return data +" : "+ list.printS();
    }

}
