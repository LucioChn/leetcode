package com.nobody;

import java.util.LinkedList;
import java.util.List;

/**
 * @Description 对于非负整数 X 而言，X 的数组形式是每位数字按从左到右的顺序形成的数组。例如，如果 X = 1231，那么其数组形式为 [1,2,3,1]。 给定非负整数 X
 *              的数组形式 A，返回整数 X+K 的数组形式。
 * @Author Mr.nobody
 * @Date 2021/1/22
 * @Version 1.0
 */
public class AddToArrayForm {
    // 因为数组A的长度可能为10000，转为数值类型是很大的一个值，而且越界现有Java的基本数据类型，所以不能将A转为数字再与K相加。
    // 可以按我们正常算加法的思维，从右到左，依次先相加低位，再算高位。
    // 即我们从数组A的尾部向头部遍历每一个数值，与K相加，保留低位，进位与下一高位继续相加。
    public static List<Integer> addToArrayForm(int[] A, int K) { // 向吐槽Leetcode的变量名大写，建议使用驼峰法
        // 链表，存相加后的结果
        LinkedList<Integer> result = new LinkedList<>();
        // 从数组A的最末位开始遍历
        int index = A.length - 1;
        while (index >= 0 || K > 0) {
            // 数组A的每一位都与K相加
            if (index >= 0) {
                K += A[index];
            }
            // 将相加后的低位保存到链表中
            result.addFirst(K % 10);
            // 去除低位值
            K /= 10;
            // 游标向前
            index--;
        }
        return result;
    }

    public static void main(String[] args) {
        int[] A = new int[] {1, 2, 0, 0};
        int K = 34;
        System.out.println(addToArrayForm(A, K));
    }
}
