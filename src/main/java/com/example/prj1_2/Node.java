package com.example.prj1_2;

public class Node<T extends Comparable<T>> {
    private T data;
    private Node<T> next;
    public Node(T data){
        this.data=data;
    }
    public void setData(T data){
        this.data=data;
    }
    public T getData(){
        return data;
    }
    public Node <T> getNext(){
        return next;
    }
    public void setNext(Node<T>Next){
        this.next =Next;
    }


}
