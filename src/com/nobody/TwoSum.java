package com.nobody;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * @Description 给定一个整数数组 nums 和一个整数目标值 target，请你在该数组中找出和为目标值的那两个整数，并返回它们的数组下标。
 *              你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。 你可以按任意顺序返回答案。
 *              原题链接：https://leetcode-cn.com/problems/two-sum/
 * @Author Mr.nobody
 * @Date 2021/1/21
 * @Version 1.0
 */
public class TwoSum {

    public static int[] twoSum(int[] nums, int target) {

        // 记录每一个遍历过的数，利用HashMap插入查找0(1)的高效率时间复杂度
        Map<Integer, Integer> indexMap = new HashMap<>(16);

        // 遍历每一个数
        for (int index = 0; index < nums.length; index++) {
            // target-nums[index]算出另一个加数，然后在map中找有没有这个加数
            if (indexMap.get(target - nums[index]) != null) {
                // 找到了，就返回2个加数的下标
                return new int[] {indexMap.get(target - nums[index]), index};
            } else {
                // 没找到，就将这个数放入map中，key为这个数，value为这个数的下标
                indexMap.put(nums[index], index);
            }
        }
        return null;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(twoSum(new int[] {1, 2, 6, 4, 10, 11, 8, 9}, 16)));
    }
}
