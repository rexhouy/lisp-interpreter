package com.rexhouy.env;

import java.io.File;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.rexhouy.type.Type;

public class RootStack extends ComExecStack {

	final String BASE_PATH = "com/rexhouy/lib";
	static final Logger log = LogManager.getLogger(RootStack.class);

	/**
	 * Load system predefined functions and macros. 
	 */
	public RootStack() {
		String bathPath = getBasePath();
		List<String> classes = findClassesInDir(bathPath, getProgramRoot());
		classes.stream().forEach((clazz) -> {
			try {
				Object preDefined = Class.forName(clazz).newInstance();
				Method getName = preDefined.getClass().getMethod("name", new Class[] {});
				this.bind((String)getName.invoke(preDefined), (Type)preDefined);
				log.info("Load system function/macro ["+clazz+"]");
			} catch(Exception e) {
				log.warn("Error loading system function/macro ["+clazz+"]");
			}
		});
	}

	String getProgramRoot() {
		return ClassLoader.getSystemResource("").getPath();
	}

	String getBasePath() {
		return getProgramRoot() + BASE_PATH;
	}

	List<String> findClassesInDir(String dir, String basePath) {
		File f = new File(dir);
		if (!f.exists()) {
			return Collections.EMPTY_LIST;
		}
		if (f.isFile()) {
			String path = f.getAbsolutePath().substring(basePath.length());
			if (filter(path)) {
				path = path.substring(0, path.length() - 6); // remove .class
				return Arrays.asList(path.replaceAll("/", "."));
			}
		}
		if (f.isDirectory()) {
			List<String> pathes = new ArrayList<String>();
			for (String path : f.list()) {
				pathes.addAll(findClassesInDir(dir + File.separator + path,
						basePath));
			}
			return pathes;
		}
		return Collections.EMPTY_LIST;
	}

	boolean filter(String path) {
		return !path.endsWith("Test.class");
	}

	public static void main(String[] args) {
		new RootStack();
	}

}