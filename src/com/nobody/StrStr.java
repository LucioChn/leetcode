package com.nobody;

/**
 * @Description 实现 strStr() 函数。 给定一个 haystack 字符串和一个 needle 字符串，在 haystack 字符串中找出 needle
 *              字符串出现的第一个位置(从0开始)。如果不存在，则返回  -1。
 *
 * @Author Mr.nobody
 * @Date 2021/1/23
 * @Version 1.0
 */
public class StrStr {

    public static int strStr(String haystack, String needle) {
        // 文本串长度小于模式串，明显匹配不到
        if (haystack.length() < needle.length()) {
            return -1;
        }
        // 利用KMP算法构建next数组
        int[] next = buildNext(needle);
        int i = 0, j = 0;
        while (i < haystack.length() && j < needle.length()) {
            if (haystack.charAt(i) == needle.charAt(j)) {
                // 匹配，两个下标都向后
                j++;
                i++;
            } else if (j == 0) {
                // 开头处匹配失效
                i++;
            } else {
                // 利用KMP，回溯到具体位置
                j = next[j];
            }
        }
        return j == needle.length() ? i - j : -1;
    }

    // KMP算法
    // next数组各值的含义：代表当前字符之前的字符串中，有多大长度的相同前缀后缀。
    // 例如如果next[j]=k，代表j之前的字符串中有最大长度为k的相同前缀后缀。
    // 意味着在某个字符失配时，该字符对应的next值会告诉你下一步匹配中，模式串应该跳到哪个位置（跳到next[j]的位置）。
    // 如果next[j]等于0，则跳到模式串的开头字符，若next[j]=k且k>0，代表下次匹配跳到j之前的某个字符，而不是跳到开头，即跳过了k个字符。
    private static int[] buildNext(String needle) {
        // 记录模式串needle每个字符匹配失效后应该回溯的位置，即nextArr[j]为在j处匹配失效后，下一个匹配（回溯）的位置，
        int[] next = new int[needle.length()];
        int j;
        // 因为第0个字符匹配失效，肯定还是从0个字符开始，所以i的从1开始
        for (int i = 1; i < needle.length(); i++) {
            j = i - 1;
            while (j > 0 && needle.charAt(i - 1) != needle.charAt(next[j])) {
                j = next[j];
            }
            if (j <= 0) {
                next[i] = 0;
            } else {
                next[i] = next[j] + 1;
            }
        }
        return next;
    }

    public static void main(String[] args) {
        System.out.println(strStr("hello", "ll"));
        System.out.println(strStr("aaaaa", "bba"));
        System.out.println(strStr("aaaaa", ""));
    }
}
