package com.example.guosaicdemo831;

import android.text.method.ReplacementTransformationMethod;

public class TextChanger extends ReplacementTransformationMethod {
	private String textchanger = null;

	@Override
	protected char[] getOriginal() {
		// TODO Auto-generated method stub
		// 循环ASCII值，字符串形式累加到String
		for (char i = 0; i < 256; i++) {
			textchanger += String.valueOf(i);
		}
		char[] charOriginal = textchanger.toCharArray();
		return charOriginal;
	}

	@Override
	protected char[] getReplacement() {
		// TODO Auto-generated method stub
		// 输入的字符在ASCII范围内，将其转换为*
		char[] charRepacement = new char[255];
		for (int i = 0; i < 255; i++) {
			charRepacement[i] = '*';
		}
		return charRepacement;
	}
}
