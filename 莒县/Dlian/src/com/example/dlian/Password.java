package com.example.dlian;

import android.text.method.PasswordTransformationMethod;
import android.view.View;

public class Password extends PasswordTransformationMethod{
	@Override
	public CharSequence getTransformation(CharSequence source, View view) {
		// TODO Auto-generated method stub
		return new pwd(source);
	}
	private class pwd implements CharSequence{
		private CharSequence mquance;
		public pwd(CharSequence sequence){
			mquance=sequence;
		}
		@Override
		public char charAt(int index) {
			// TODO Auto-generated method stub
			return '*';
		}
		@Override
		public int length() {
			// TODO Auto-generated method stub
			return mquance.length();
		}
		@Override
		public CharSequence subSequence(int start, int end) {
			// TODO Auto-generated method stub
			return mquance.subSequence(start, end);
		}
	}

}
