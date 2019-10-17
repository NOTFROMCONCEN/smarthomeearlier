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
 * @�ļ�����UIsystem.java
 * @������ϵͳ����
 * @���ߣ�������
 *  ��Ȩ����������ΪCSDN������zpf_����ԭ�����£���ѭCC 4.0 by-sa��ȨЭ�飬ת���븽��ԭ�ĳ������Ӽ���������
 ԭ�����ӣ�https://blog.csdn.net/qq1271396448/article/details/80510946
 * @ʱ�䣺2019-8-13
 * @author Administrator
 */
public final class UIsystem {

	public static boolean isMIUI() {
		String xiaomi = Build.MANUFACTURER;
		// ����ַ��������Լ�����,�����жϻ�Ϊ����дhuawei,�������дmeizu
		if ("Xiaomi".equalsIgnoreCase(xiaomi)) {
			return true;
		}
		return false;
	}
}
