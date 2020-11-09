/**
 * @ClassName: Test58
 * @Description: 在排序数组中查找元素的第一个和最后一个元素的位置
 *  给定一个按照升序排列的整数数组 nums，和一个目标值 target。找出给定目标值在数组中的开始位置和结束位置。
 *
 * 你的算法时间复杂度必须是 O(log n) 级别。
 *
 * 如果数组中不存在目标值，返回 [-1, -1]。
 *
 * 示例 1:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 8
 * 输出: [3,4]
 * 示例 2:
 *
 * 输入: nums = [5,7,7,8,8,10], target = 6
 * 输出: [-1,-1]
 *
 * @Author: Admin
 **/

public class Test58 {
    public static int[] searchRange1(int[] nums, int target) {
        int[] res = {-1, -1};
        if (nums.length == 0) {
            return res;
        }
        if (nums.length == 1) {
            return nums[0] == target ? new int[]{0, 0} : res;
        }
        divide(res, nums, target, 0, nums.length - 1, true);
        divide(res, nums, target, 0, nums.length - 1, false);
        return res;
    }

    public static void divide(int[] result, int[] nums, int target, int low, int high, boolean flag) {
        if (low <= high) {
            int mid = (low + high) / 2;
            if (nums[mid] < target) {
                divide(result, nums, target, mid + 1, high, flag);
            } else if (nums[mid] > target) {
                divide(result, nums, target, low, mid - 1, flag);
            } else {
                if (flag) {
                    result[0] = mid;
                    divide(result, nums, target, low, mid - 1, flag);
                } else {
                    result[1] = mid;
                    divide(result, nums, target, mid + 1, high, flag);
                }
            }
        }
        return;
    }

    //二分查找法
    public static int[] searchRange(int[] nums, int target) {
        int[] result = new int[2];
        result[0] = searchLeft(nums, target);
        result[1] = searchRight(nums, target);
        return result;
    }

    private static int searchLeft(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        //二分查找第一次出现的位置
        while (left < right) {
            //(left + right) / 2
            int mid = (left + right) >>> 1;
            if (nums[mid] == target) {
                right = mid;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        //target比所有数都大
        if (left == nums.length) {
            return -1;
        }
        return nums[left] == target ? left : -1;
    }

    private static int searchRight(int[] nums, int target) {
        int left = 0;
        int right = nums.length;
        //二分查找第二次出现的位置
        while (left < right) {
            int mid = (left + right) >>> 1;//(left + right) / 2
            if (nums[mid] == target) {
                left = mid + 1;
            } else if (nums[mid] > target) {
                right = mid;
            } else if (nums[mid] < target) {
                left = mid + 1;
            }
        }
        //target比所有的数都小
        if (left == 0) {
            return -1;
        }
        return nums[left - 1] == target ? (left - 1) : -1;
    }
}