package com.example.shengsaiademo2017918;

import android.text.method.ReplacementTransformationMethod;

/*
 * @�ļ�����TextChanger.java
 * @�������ַ�ת��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-9-18
 */
public class TextChanger extends ReplacementTransformationMethod {
	private String string = null;

	@Override
	protected char[] getOriginal() {
		// TODO Auto-generated method stub
		// ѭ��ASCIIֵ �ַ�����ʽ�ۼӵ�String
		for (char i = 0; i < 256; i++) {
			string += String.valueOf(i);
		}
		// ת��Ϊ�ַ�����ʽ
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
