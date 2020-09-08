package com.example.algorithm;

/**
 * @author wang.zhiqiang
 * @version 1.0
 * @date 2020/9/3 17:40
 * @desc
 */
public class Sort {

    /**
     * 冒泡排序：比较相邻的两个元素，把较大的放到右边，让右边再比较
     * @param nums
     * @return
     */
    public static int[] sortByBubble(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = 0; j < nums.length - i -1; j++) {
                if (nums[j] > nums[j+1]) {
                    int tmp = nums[j + 1];
                    nums[j + 1] = nums[j];
                    nums[j] = tmp;
                }
            }
        }
        return nums;
    }

    /**
     * 选择排序(Selection-sort)是一种简单直观的排序算法。它的工作原理：首先在未排序序列中找到最小（大）元素，
     * 存放到排序序列的起始位置，然后，再从剩余未排序元素中继续寻找最小（大）元素，然后放到已排序序列的末尾。以此类推，直到所有元素均排序完毕
     *
     * 算法复杂度稳定：O(n2)
     *
     * @param nums
     * @return
     */
    public static int[] sortBySelection(int[] nums) {
        for (int i = 0; i < nums.length; i++) {
            for (int j = i + 1; j < nums.length; j++) {
                if (nums[i] > nums[j]) {
                    int tmp = nums[j];
                    nums[j] = nums[i];
                    nums[i] = tmp;
                }
            }
        }
        return nums;
    }

    /**
     * 插入排序（Insertion-Sort）的算法描述是一种简单直观的排序算法。它的工作原理是通过构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入。
     *
     * @param nums
     * @return
     */
    /*public static int[] sortByInsertion(int[] nums) {
        int tmp = nums[0];
        for (int i = 1; i < nums.length; i++) {
            if () {

            }
        }
    }*/
}
