package com.example.shengsaiademo2017918;

import android.text.method.ReplacementTransformationMethod;

/*
 * @文件名：TextChanger.java
 * @描述：字符转换
 * @作者：邢启瑞
 * @版权：Bilibili 奶油话梅糖
 * @时间：2019-9-18
 */
public class TextChanger extends ReplacementTransformationMethod {
	private String string = null;

	@Override
	protected char[] getOriginal() {
		// TODO Auto-generated method stub
		// 循环ASCII值 字符串形式累加到String
		for (char i = 0; i < 256; i++) {
			string += String.valueOf(i);
		}
		// 转换为字符串形式
		char[] charOriginal = string.toCharArray();
		return charOriginal;
	}

	@Override
	protected char[] getReplacement() {
		// TODO Auto-generated method stub
		char[] charReplacement = new char[255];
		for (int i = 0; i < 255; i++) {
			charReplacement[i] = '*';
		}
		return charReplacement;
	}
}
