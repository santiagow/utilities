package com.santiagow.util.io.handler;

import java.io.File;

/**
 * @author Santiago Wang
 * @since 2016/7/10
 */
public class RemoveFileHandler implements FileHandler {
	public void handle(File file) {
		boolean isDir = file.isDirectory();
		if (isDir) {
			deleteDir(file);
		} else {
			file.delete();
		}
		System.out.println("Success to delete " + (isDir ? "directory" : "file") + ": " + file.getPath());
	}

	private void deleteDir(File file) {
		for (File f : file.listFiles()) {
			if (f.isDirectory()) {
				deleteDir(f);
			} else {
				f.delete();
			}
		}

		file.delete();
	}
}
