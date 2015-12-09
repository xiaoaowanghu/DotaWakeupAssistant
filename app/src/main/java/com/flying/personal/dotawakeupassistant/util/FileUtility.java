package com.flying.personal.dotawakeupassistant.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.zip.ZipEntry;
import java.util.zip.ZipFile;

/**
 * Created by wangxian on 9/15/2015.
 */
public class FileUtility {
    public static void copyFolder(String oldPath, String newPath) throws IOException {
        File newPathDir = new File(newPath);
        newPathDir.mkdirs();
        File a = new File(oldPath);
        String[] file = a.list();
        File temp = null;
        for (int i = 0; i < file.length; i++) {
            if (oldPath.endsWith(File.separator)) {
                temp = new File(oldPath + file[i]);
            } else {
                temp = new File(oldPath + File.separator + file[i]);
            }

            if (temp.isFile()) {
                FileInputStream input = null;
                FileOutputStream output = null;
                try {
                    input = new FileInputStream(temp);
                    output = new FileOutputStream(newPath + File.separator + (temp.getName()).toString());
                    byte[] b = new byte[1024 * 5];
                    int len;
                    while ((len = input.read(b)) != -1) {
                        output.write(b, 0, len);

                    }
                    output.flush();
                } finally {
                    if (output != null)
                        output.close();
                    if (input != null)
                        input.close();
                }

            }
            if (temp.isDirectory()) {
                copyFolder(temp.getAbsolutePath(), newPath + File.separator + file[i]);
            }
        }
    }

    public static void unZip(String zipFileName, String outputDirectory) throws IOException {
        ZipFile zipFile = null;
        try {
            zipFile = new ZipFile(zipFileName);
            Enumeration e = zipFile.entries();
            ZipEntry zipEntry = null;
            File dest = new File(outputDirectory);
            dest.mkdirs();
            while (e.hasMoreElements()) {
                zipEntry = (ZipEntry) e.nextElement();
                String entryName = zipEntry.getName();
                InputStream in = null;
                FileOutputStream out = null;
                try {
                    if (zipEntry.isDirectory()) {
                        String name = zipEntry.getName();
                        name = name.substring(0, name.length() - 1);
                        File f = new File(outputDirectory + File.separator
                                + name);
                        f.mkdirs();
                    } else {
                        int index = entryName.lastIndexOf("\\");
                        if (index != -1) {
                            File df = new File(outputDirectory + File.separator
                                    + entryName.substring(0, index));
                            df.mkdirs();
                        }
                        index = entryName.lastIndexOf("/");
                        if (index != -1) {
                            File df = new File(outputDirectory + File.separator
                                    + entryName.substring(0, index));
                            df.mkdirs();
                        }
                        File f = new File(outputDirectory + File.separator
                                + zipEntry.getName());
                        in = zipFile.getInputStream(zipEntry);
                        out = new FileOutputStream(f);
                        int c;
                        byte[] by = new byte[2048];
                        while ((c = in.read(by)) != -1) {
                            out.write(by, 0, c);
                        }
                        out.flush();
                    }
                } finally {
                    if (in != null) {
                        in.close();
                    }
                    if (out != null) {
                        out.close();
                    }
                }
            }
        } finally {
            if (zipFile != null) {
                zipFile.close();
            }
        }
    }

    public static void deleteAllFiles(File root) {
        deleteAllFiles(root, true);
    }

    public static void deleteAllFiles(File root, boolean isDeleteRoot) {
        if (!root.exists())
            return;

        if (root.isDirectory()) {
            File files[] = root.listFiles();
            for (int i = 0; i < files.length; i++) {
                deleteAllFiles(files[i], true);
            }

            if (isDeleteRoot)
                root.delete();
        } else {
            root.delete();
        }
    }


}
