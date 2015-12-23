package com.util;

public class BugData {
	public static String bugLeve1="Ⅰ级(建议性,不影响使用，但是可以优化,无需立即修复)";
	public static String bugLeve2="Ⅱ级(影响使用,需要尽快修复)";
	public static String bugLeve3="Ⅲ级(严重影响使用,不立即修复无法使用)";

	public static String SubbmitAndIsRepairing="已提交，正在修复中...";//1
	public static String RepairedAndIsTesting="已修复正在测试中...";//2
	public static String Closed="通过测试，BUG已修复关闭！";//3
	public static String NotPassTestAndReturnRepairing="测试未通过，返回正在修复...";//4
	public static String ReopenAndRepairing="BUG重新开启，正在修复...";//5
}
