package org.song.java8;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Stack;

/**
 * Purpose of this class is to
 */
public class Calculator {
    private enum Operator{
        ADD('+', 1){
            @Override
            public int operate(int o1, int o2){
                return o1+o2;
            }
        },
        MIN('-', 1){
            @Override
            public int operate(int o1, int o2){
                return o1-o2;
            }

        },
        MULTI('*', 2){
            @Override
            public int operate(int o1, int o2){
                return o1*o2;
            }

        },
        DIV('/', 2){
            @Override
            public int operate(int o1, int o2){
                return o1/o2;
            }
        },
        OPEN('(', 0){
            @Override
            public int operate(int o1, int o2){
                return 0;
            }

        },
        CLOSE(')', 0){
            @Override
            public int operate(int o1, int o2){
                return 0;
            }
        };

        private final char id;
        private final int rank;
        private Operator(char id, int rank){
            this.id=id;
            this.rank=rank;
        }

        private static final Map<Character, Operator> map = new HashMap<>();

        static {
            for (Operator op : Operator.values()) {
                map.put(Character.valueOf(op.id), op);
            }
        }

        public static Operator getOp(char id) {
            return map.get(Character.valueOf(id));
        }

        public int rank() {
            return rank;
        }

        public abstract int operate(int o1, int o2);

    }

    private List<Object> infixToPostFix(String s) {
        Stack<Operator> stack = new Stack<>();
        List<Object> res = new LinkedList<>();
        boolean buffering = false;
        int num = 0;
        for (char c : s.toCharArray()) {
            if (c >= '0' && c <= '9') {
                num = num * 10 + (c - '0');
                buffering = true;
            }
            else {
                Operator op = Operator.getOp(c);
                if (op == null){
                    continue;
                }

                if (buffering) {
                    res.add(Integer.valueOf(num));
                    num = 0;
                    buffering = false;
                }
                if (op == Operator.OPEN) {
                    stack.push(op);
                }
                else if (op == Operator.CLOSE) {
                    while (!stack.isEmpty() && stack.peek() != Operator.OPEN) {
                        res.add(stack.pop());
                    }
                    // poping open "(";
                    stack.pop();
                }
                else{
                    while (!stack.isEmpty() && op.rank() <= stack.peek().rank() ){
                        res.add(stack.pop());
                    }
                    stack.push(op);
                }

            }

        }

        if (buffering){
            res.add(Integer.valueOf(num));
        }

        while (!stack.isEmpty()){
            res.add(stack.pop());
        }

        return res;
    }

    private int evaluate(List<Object> postFix){
        Stack<Integer> operands = new Stack<>();
        for (Object o : postFix){
            if (o instanceof Integer){
                operands.push((Integer)o);
            }
            else{
                int o2 = operands.pop();
                int o1 = operands.pop();
                Operator op = (Operator)o;
                operands.push(op.operate(o1, o2));
            }
        }
        return operands.pop();
    }
    public int calculate(String s) {
        if (s==null || s.length()==0){
            return 0;
        }
        List<Object> l = infixToPostFix(s);
        System.out.println("postFix is: " + l);
        int res = evaluate(l);

        System.out.println("res is: "+res);
        return res;
    }

    public int calculateSimple(String s) {
        if (s==null || s.trim().length()==0){
            return 0;
        }
        char [] cs = s.trim().toCharArray();
        long num =0;
        char sign='+';
        Stack<Long> stack = new Stack<>();
        for (int i=0; i<cs.length; i++){
            if(cs[i]>='0' && cs[i] <='9'){
                num = num * 10 + (cs[i]-'0');
            }
            else if (cs[i] !=' ' || i==cs.length-1){
                switch(sign){
                    case '+':
                        stack.push(num);
                        break;
                    case '-':
                        stack.push(-num);
                        break;
                    case '*':
                        stack.push(stack.pop().longValue()*num);
                        break;
                    case '/':
                        stack.push(stack.pop().longValue()/num);
                        break;
                }
                num = 0;
                sign = cs[i];
            }
        }
        if (num!=0){

            switch(sign){
                case '+':
                    stack.push(num);
                    break;
                case '-':
                    stack.push(-num);
                    break;
                case '*':
                    stack.push(stack.pop().longValue()*num);
                    break;
                case '/':
                    stack.push(stack.pop().longValue()/num);
                    break;
            }

        }
        num=0;
        while (!stack.isEmpty()){
            num+=stack.pop().longValue();
        }
        return (int) num;
//        return num==Integer.MAX_VALUE?Integer.MIN_VALUE+1:(int)num;
    }


    public static void main(String[] args){
        Calculator cal = new Calculator();
        String s1 = " 2-1 + 2 ";
        cal.calculate(s1);

        String s2 = "0-2147483647";
        System.out.println(s2 + ": " + cal.calculateSimple(s2));

        String s3 = "2147483647";
        System.out.println(s3 + ": " + cal.calculateSimple(s3));

    }
}
