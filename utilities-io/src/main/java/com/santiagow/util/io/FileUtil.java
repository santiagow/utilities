package com.santiagow.util.io;

import com.santiagow.util.io.handler.RemoveFileHandler;
import com.santiagow.util.io.handler.FileHandler;
import com.santiagow.util.io.handler.ListFileHandler;

import java.io.File;

/**
 * @author Santiago Wang
 * @since 2016/7/9
 */
public class FileUtil {
	public static void findFile(String path, String pattern, FileHandler handler) {
		findFile(path, pattern, false, handler);
	}

	public static void findFile(String path, String pattern, boolean isDir, FileHandler handler) {
		File file = new File(path);

		if (file.isDirectory()) {
			if (isDir && file.getName().equals(pattern)) {
				handler.handle(file);
			} else {
				for (File f : file.listFiles()) {
					findFile(f.getPath(), pattern, isDir, handler);
				}
			}
		} else {
			if (!isDir && file.getName().equals(pattern)) {
				System.out.println(file.getPath());
			}
		}
	}

	public enum FileHandlerEnum {
		LIST("-l", new ListFileHandler()),
		REMOVE("-rm", new RemoveFileHandler());

		private final String param;
		private final FileHandler handler;

		FileHandlerEnum(String param, FileHandler handler) {
			this.param = param;
			this.handler = handler;
		}

		public String getParam() {
			return param;
		}

		public FileHandler getHandler() {
			return handler;
		}

		public static FileHandlerEnum findHandlerByParam(String param){
			if (param == null || param.isEmpty()) {
				return LIST;
			}

			for (FileHandlerEnum handlerEnum : FileHandlerEnum.class.getEnumConstants()) {
				if (param.equals(handlerEnum.getParam())) {
					return handlerEnum;
				}
			}

			return LIST;
		}
	}

	public static void main(String[] args) {
		if (args.length != 4) {
			System.out.println("Usage: [path] [-f]|[-d] [file_name] [-l]|[-rm]");
			return;
		}

		String path = args[0];
		boolean isDir = args[1].equals("-d");
		String pattern = args[2];
		String handler = args[3];
		FileHandlerEnum handlerEnum = FileHandlerEnum.findHandlerByParam(handler);

		findFile(path, pattern, isDir, handlerEnum.getHandler());
	}
}
