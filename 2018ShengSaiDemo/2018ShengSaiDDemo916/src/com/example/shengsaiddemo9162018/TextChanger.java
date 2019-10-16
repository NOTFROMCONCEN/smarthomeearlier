package com.example.shengsaiddemo9162018;

import android.text.method.ReplacementTransformationMethod;

public class TextChanger extends ReplacementTransformationMethod {
	private String strWord = null;

	@Override
	protected char[] getOriginal() {
		// TODO Auto-generated method stub
		// ѭ��ASCIIֵ �ַ�����ʽ�ۼӵ�String
		for (char i = 0; i < 256; i++) {
			strWord += String.valueOf(i);
		}
		// strWordת��Ϊ�ַ���ʽ������
		char[] charOriginal = strWord.toCharArray();
		return charOriginal;
	}

	@Override
	protected char[] getReplacement() {
		// TODO Auto-generated method stub
		char[] charReplacement = new char[255];
		// ������ַ���ASCII��Χ�ڣ�����ת��Ϊ*
		for (int i = 0; i < 255; i++) {
			charReplacement[i] = '*';
		}

		return charReplacement;
	}

}
