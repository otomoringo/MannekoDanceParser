package com.otomoringo.manekkodanceparser;

public class Util {
	private char _bracket;
	private int _loopCounter;
	private int _backCounter;

	// public Util(char b, int lc, int bc) {
	// _bracket = b;
	// _loopCounter = lc;
	// _backCounter = bc;
	// }

	public char getBracket() {
		return _bracket;
	}

	public void setBracket(char bracket) {
		_bracket = bracket;
	}

	public int getLoopCounter() {
		return _loopCounter;
	}

	public void setLoopCounter(int loopCounter) {
		_loopCounter = loopCounter;
	}

	public int getBackCounter() {
		return _backCounter;
	}

	public void setBackCounter(int backCounter) {
		_backCounter = backCounter;
	}

}
