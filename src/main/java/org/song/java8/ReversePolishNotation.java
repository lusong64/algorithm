package org.song.java8;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * Purpose of this class is to
 */
public class ReversePolishNotation {
    private static enum Operator{
        Plus("+"){
            @Override
            public int operate(int i1, int i2) {
                return i1+i2;
            }
        },
        Minus("-"){
            @Override
            public int operate(int i1, int i2) {
                return i1-i2;
            }

        },
        Dvision("/"){
            @Override
            public int operate(int i1, int i2) {
                return i1/i2;
            }

        },
        Multiply("*"){
            @Override
            public int operate(int i1, int i2) {
                return i1*i2;
            }

        };

        private final String operator;
        private Operator(String operator){
            this.operator = operator;
        }

        private static final Map<String, Operator> map =
                new HashMap<>();
        static{
            for (Operator op : Operator.values()){
                map.put(op.operator, op);
            }
        }

        public static Operator getOperator(String s){
            return map.get(s);
        }

        public abstract int operate(int i1, int i2);


    }
    public int evalRPN(String[] tokens) {

        if (tokens == null || tokens.length == 0){

            return 0;
        }
        Stack<Object> stack = new Stack<>();
        for (String t : tokens){
            Operator op = Operator.getOperator(t);
            if (op == null){
                stack.push(Integer.parseInt(t));
            }
            else {
                int i2 = ((Integer)stack.pop()).intValue();
                int i1 = ((Integer)stack.pop()).intValue();
                stack.push(Integer.valueOf(op.operate(i1, i2)));
            }
        }

        return ((Integer)stack.pop()).intValue();
    }
}
