package com.santiagow.util.io.handler;

import java.io.File;

/**
 * @author Santiago Wang
 * @since 2016/7/10
 */
public class ListFileHandler implements FileHandler {

	public void handle(File file) {
		System.out.println(file.getPath());
	}
}
