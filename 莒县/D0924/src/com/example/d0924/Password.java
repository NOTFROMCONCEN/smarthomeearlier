package com.example.d0924;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

public class Password extends PasswordTransformationMethod{
	@Override
	public CharSequence getTransformation(CharSequence source, View view) {
		// TODO Auto-generated method stub
		return new pwd(source);
	}
	private class pwd implements CharSequence{
		private CharSequence msSequence;

		public pwd(CharSequence source) {
			// TODO Auto-generated constructor stub
			msSequence=source;
		}

		@Override
		public char charAt(int index) {
			// TODO Auto-generated method stub
			return '*';
		}

		@Override
		public int length() {
			// TODO Auto-generated method stub
			return msSequence.length();
		}

		@Override
		public CharSequence subSequence(int start, int end) {
			// TODO Auto-generated method stub
			return msSequence.subSequence(start, end);
		}
	} 

}
