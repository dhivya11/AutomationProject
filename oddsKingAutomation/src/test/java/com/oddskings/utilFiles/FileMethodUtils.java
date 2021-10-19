package com.oddskings.utilFiles;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.Date;
import org.apache.commons.io.FileUtils;

public class FileMethodUtils {
	public String filecopy(String readPath, String WritePath) {

		File source = new File(readPath);
		File dest = new File(WritePath);

		try {
			FileUtils.copyFile(source, dest);
		} catch (IOException e) {
			e.printStackTrace();
		}

		String ts = getCurrentTimeStamp();
		String name = dest.getName();
		String fileName = name.replaceFirst("[.][^.]+$", "");
		String newfilename = fileName + ts;
		Path source1 = Paths.get(dest.getAbsolutePath());

		try {
			Files.move(source1, source1.resolveSibling(newfilename + ".xlsx"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println();
		return newfilename;

	}

	public String getCurrentTimeStamp() {
		String fileSuffix = new SimpleDateFormat("yyyyMMddHHmmss").format(new Date());
		return fileSuffix;
	}

	public void renameFile(String src, String dest) {
		File oldName = new File(src);
		File newName = new File(dest);

		if (oldName.renameTo(newName)) {
			System.out.println("Renamed successfully");
		} else {
			System.out.println("Error");
		}
	}
}
