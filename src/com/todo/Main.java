package com.todo;

import java.io.IOException;

public class Main {

	public static void main(String[] args) throws IOException {
		TodoMain.load();
		TodoMain.start();
		TodoMain.save();
	}
}
