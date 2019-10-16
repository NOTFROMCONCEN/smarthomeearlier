package com.example.guosaicdemo831;

import android.text.method.ReplacementTransformationMethod;

public class TextChanger extends ReplacementTransformationMethod {
	private String textchanger = null;

	@Override
	protected char[] getOriginal() {
		// TODO Auto-generated method stub
		// ѭ��ASCIIֵ���ַ�����ʽ�ۼӵ�String
		for (char i = 0; i < 256; i++) {
			textchanger += String.valueOf(i);
		}
		char[] charOriginal = textchanger.toCharArray();
		return charOriginal;
	}

	@Override
	protected char[] getReplacement() {
		// TODO Auto-generated method stub
		// ������ַ���ASCII��Χ�ڣ�����ת��Ϊ*
		char[] charRepacement = new char[255];
		for (int i = 0; i < 255; i++) {
			charRepacement[i] = '*';
		}
		return charRepacement;
	}
}
