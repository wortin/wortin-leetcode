package com.wortin.leetcode;

public class Main {
    public static void main(String[] args) {
        String ar = "[[1,1,1,2,0,0],[0,0,0,0,1,2]]";
        ar = ar.replace('[', '{');
        ar = ar.replace(']', '}');
        System.out.println(ar);
    }
}
