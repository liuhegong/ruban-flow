package cn.com.yusys.yusp.workflow.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;


public class FileUtil {

    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        return dir.delete();
    }    

    public static void writeNewContentToFile(String filePathAndName, String newContent) throws IOException {

			File file = new File(filePathAndName);
			if (!file.exists()) {
				file.createNewFile();
			}

			OutputStreamWriter write = null;
			BufferedWriter writer = null;
			try {
				write = new OutputStreamWriter(new FileOutputStream(file,false), "utf-8");
				writer = new BufferedWriter(write);
				writer.write(newContent);
				writer.newLine();
				System.out.println("【成功写入内容到文件中】【" + filePathAndName + "】");
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}finally{
				writer.close();
				write.close();
			}	
	}
}
