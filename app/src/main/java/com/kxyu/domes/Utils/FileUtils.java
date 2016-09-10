package com.kxyu.domes.Utils;

import android.content.Context;

import java.io.ByteArrayOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;

/**
 * Created by kxyu on 16-8-30.
 */
public class FileUtils {

    public static void writeFile(final Context context, final String fileName, final String content, boolean isUseThread) {
        if (isUseThread) {
            new Thread() {
                @Override
                public void run() {
                    writeFile(context, fileName, content);
                    super.run();
                }
            }.start();
        } else {
            writeFile(context, fileName, content);
        }
    }

    public static void writeFile(final Context context, final String fileName, final String content) {
        FileOutputStream outStream = null;
        try {
            outStream = context.openFileOutput(fileName,
                    Context.MODE_PRIVATE);
            outStream.write(content.getBytes());
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                outStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public static String readFile(Context context, String fileName) {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        FileInputStream fis = null;
        try {
            fis = context.openFileInput(fileName);
            byte[] buffer = new byte[1024];
            int len;
            while ((len = fis.read(buffer)) != -1) {
                baos.write(buffer, 0, len);
            }
            String content = baos.toString();
            return content;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fis.close();
                baos.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
