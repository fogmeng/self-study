package com.behaviour.visitor;
public abstract class Node {
    /**
     * ���ܲ���
     */
    public abstract void accept(Visitor visitor);
}