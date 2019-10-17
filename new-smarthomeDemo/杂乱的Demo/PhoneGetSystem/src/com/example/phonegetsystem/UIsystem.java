package com.example.phonegetsystem;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Collection;
import java.util.Enumeration;
import java.util.Map;
import java.util.Properties;
import java.util.Set;

import android.os.Build;
import android.os.Environment;

/*
 * @文件名：UIsystem.java
 * @描述：系统辅助
 * @作者：邢启瑞
 *  版权声明：本文为CSDN博主「zpf_」的原创文章，遵循CC 4.0 by-sa版权协议，转载请附上原文出处链接及本声明。
 原文链接：https://blog.csdn.net/qq1271396448/article/details/80510946
 * @时间：2019-8-13
 * @author Administrator
 */
public final class UIsystem {

	public static boolean isMIUI() {
		String xiaomi = Build.MANUFACTURER;
		// 这个字符串可以自己定义,例如判断华为就填写huawei,魅族就填写meizu
		if ("Xiaomi".equalsIgnoreCase(xiaomi)) {
			return true;
		}
		return false;
	}
}
