package com.example.shengsaic9192017;

import android.text.method.ReplacementTransformationMethod;

/*
 * @�ļ�����TextChanger.java
 * @�������ַ�ת��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-19
 */
public class TextChanger extends ReplacementTransformationMethod {

	private String string = null;

	@Override
	protected char[] getOriginal() {
		// TODO Auto-generated method stub
		for (char i = 0; i < 256; i++) {
			string += String.valueOf(i);
		}
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
