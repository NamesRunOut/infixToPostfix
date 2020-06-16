package com.company;

class stack{
    char[] a;
    int n;

    public stack() {
        a = new char[256];
        n=0;
    }
    int currentSize(){
        return n;
    }
    char top(){
        return a[n-1];
    }
    void pop(){
        n--;
    }
    void push(char x){
        a[n]=x;
        n++;
    }
    boolean isEmpty(){
        return n == 0;
    }

}

class infixToPostfix {
    private static int priority(char s){
        int priorityValue=-1;
        if (s=='=')
            priorityValue=0;
        if (s=='<' || s=='>')
            priorityValue=1;
        if (s=='+' || s=='-')
            priorityValue=2;
        if (s=='*' || s=='/' || s=='%')
            priorityValue=3;
        if (s=='^')
            priorityValue=4;
        if (s=='~')
            priorityValue=5;
        if (s>='a' && s<='z')
            priorityValue=6;
        return priorityValue;
    }

    private static char leftOrRight(char c){
        int n = priority(c);
        if (n==0 || n==4 || n==5){
            return 'p';
        }
        if (n==1 || n==2 || n==3){
            return 'l';
        }
        return '?';
    }

    static String convert(String a){
        StringBuilder result= new StringBuilder();
            stack tmp = new stack();
            for (int i=0;i<a.length();i++){
                char c = a.charAt(i);
                if (priority(c)==6) result.append(c);
                else{
                    if (c=='(') tmp.push(c);
                    else if (c==')'){
                        while(!tmp.isEmpty() && tmp.top()!='('){
                            result.append(tmp.top());tmp.pop();
                        }
                        if (!tmp.isEmpty()) tmp.pop();
                    } else if (priority(c)<6){
                        while ((leftOrRight(c)=='l' && !tmp.isEmpty() && priority(tmp.top())>=priority(c)) ||
                                (leftOrRight(c)=='p' && !tmp.isEmpty() && priority(tmp.top())>priority(c))){
                            char o2=tmp.top();
                            tmp.pop();
                            result.append(o2);
                        }
                        tmp.push(c);
                    }
                }
            }
            while (!tmp.isEmpty()){
                result.append(tmp.top());tmp.pop();
            }
        return result.toString();
    }
}

public class Main {

    public static void main(String[] args) {
        // assuming that the statement is correct
        // converts an infix statement to it's postfix version
        String statement = "x=((a+b)*(c/d))^f";
        System.out.println(infixToPostfix.convert(statement));
        statement = "~~~~~~a+b";
        System.out.println(infixToPostfix.convert(statement));
    }
}
