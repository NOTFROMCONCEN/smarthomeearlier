package com.example.drawdemo1008.textchanger;

import android.text.method.ReplacementTransformationMethod;

/*
 * @�ļ�����TextChanger.java
 * @�������ı�ת��Ϊ��*��
 * @���ߣ�������
 * @��Ȩ��Bilibili ���ͻ�÷��
 * @ʱ�䣺2019-10-8
 */
public class TextChanger extends ReplacementTransformationMethod {

	String string = null;

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
