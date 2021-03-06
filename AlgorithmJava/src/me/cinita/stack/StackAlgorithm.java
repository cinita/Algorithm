package me.cinita.stack;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

public class StackAlgorithm {

    // 有效的括号
    public static boolean isValidBrackets(String s) {
        if (s == null || s.length() < 2) {
            return false;
        }

        Map<Character, Character> mappings = new HashMap<>();
        mappings.put(')', '(');
        mappings.put(']', '[');
        mappings.put('}', '{');

        Stack<Character> stack = new Stack<>();
        for (int i = 0; i < s.length(); ++i) {
            char c = s.charAt(i);
            if (mappings.containsKey(c)) {
                if (stack.isEmpty()) {
                    return false;
                }

                if (stack.pop() != mappings.get(c)) {
                    return false;
                }
            } else {
                stack.push(c);
            }
        }

        return stack.isEmpty();
    }

    // 逆波兰表达式求值
    public int evalRPN(String src) {
        if (src == null || src.length() < 3) {
            return -1;
        }

        char[] chars = src.toCharArray();
        Stack<Integer> stack = new Stack<>();
        String operators = "+-*/";

        for (int c : chars) {
            String s = Integer.toString(c);
            if (!operators.contains(s)) {
                stack.push(c);
                continue;
            }

            int a = stack.pop();
            int b = stack.pop();
            switch (s) {
                case "+":
                    stack.push(b + a);
                    break;
                case "-":
                    stack.push(b - a);
                    break;
                case "*":
                    stack.push(b * a);
                    break;
                case "/":
                    stack.push(b / a);
                    break;
            }
        }

        return stack.pop();
    }

    // 递归压入栈底
    public static <E> void insertAtBottom(Stack<E> stack, E item) {
        if (stack == null || item == null) return;

        if (stack.isEmpty()) {
            stack.push(item);
            return;
        }

        E top = stack.pop();
        insertAtBottom(stack, item);
        stack.push(top);
    }

    // 递归翻转栈
    public static <E> void reverse(Stack<E> stack) {
        if (stack == null || stack.isEmpty()) return;

        E top = stack.pop();
        reverse(stack);
        insertAtBottom(stack, top);
    }

    // 根据入栈顺序判断正确的出栈顺序
    public static boolean isPostOrder(int[] push, int[] pop) {
        if (push == null || pop == null || push.length != pop.length || push.length == 0) {
            return false;
        }

        Stack<Integer> stack = new Stack<>();
        int nextPop = 0;
        int nextPush = 0;
        while (nextPop < pop.length) {
            while ((stack.isEmpty() || stack.peek() != pop[nextPop])
                    && nextPush < push.length) {
                stack.push(push[nextPush++]);
            }

            if (stack.peek() == pop[nextPop++]) {
                stack.pop();
            }
        }

        return stack.isEmpty();
    }
}
