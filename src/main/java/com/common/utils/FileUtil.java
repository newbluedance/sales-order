package com.common.utils;

import java.io.*;
import java.nio.ByteBuffer;
import java.nio.MappedByteBuffer;
import java.nio.channels.FileChannel;
import java.nio.channels.FileChannel.MapMode;

public class FileUtil {
    public static Double getFilesize(String filepath) {
        File backupath = new File(filepath);
        return Double.valueOf(Double.valueOf(backupath.length()).doubleValue() / 1000.0D);
    }

    public static Boolean createDir(String destDirName) {
        File dir = new File(destDirName);
        if (!dir.getParentFile().exists()) {
            return Boolean.valueOf(dir.getParentFile().mkdirs());
        }
        return Boolean.valueOf(false);
    }

    public static void delFile(String filePathAndName) {
        try {
            String filePath = filePathAndName;
            filePath = filePath.toString();
            File myDelFile = new File(filePath);
            myDelFile.delete();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static byte[] getContent(String filePath)
            throws IOException {
        File file = new File(filePath);
        long fileSize = file.length();
        if (fileSize > 2147483647L) {
            System.out.println("file too big...");
            return null;
        }
        FileInputStream fi = new FileInputStream(file);
        byte[] buffer = new byte[(int) fileSize];
        int offset = 0;
        int numRead = 0;
        while ((offset < buffer.length) && ((numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0)) {
            offset += numRead;
        }
        if (offset != buffer.length) {
            throw new IOException("Could not completely read file " + file.getName());
        }
        fi.close();
        return buffer;
    }

    public static byte[] toByteArray(String filePath)
            throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }
        ByteArrayOutputStream bos = new ByteArrayOutputStream((int) f.length());
        BufferedInputStream in = null;
        try {
            in = new BufferedInputStream(new FileInputStream(f));
            int buf_size = 1024;
            byte[] buffer = new byte[buf_size];
            int len = 0;
            while (-1 != (len = in.read(buffer, 0, buf_size))) {
                bos.write(buffer, 0, len);
            }
            return bos.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                in.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            bos.close();
        }
    }

    public static byte[] toByteArray2(String filePath)
            throws IOException {
        File f = new File(filePath);
        if (!f.exists()) {
            throw new FileNotFoundException(filePath);
        }
        FileChannel channel = null;
        FileInputStream fs = null;
        try {
            fs = new FileInputStream(f);
            channel = fs.getChannel();
            ByteBuffer byteBuffer = ByteBuffer.allocate((int) channel.size());
            while (channel.read(byteBuffer) > 0) {
            }
            return byteBuffer.array();
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                channel.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
            try {
                fs.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static byte[] toByteArray3(String filePath)
            throws IOException {
        FileChannel fc = null;
        RandomAccessFile rf = null;
        try {
            rf = new RandomAccessFile(filePath, "r");
            fc = rf.getChannel();
            MappedByteBuffer byteBuffer = fc.map(MapMode.READ_ONLY, 0L, fc.size()).load();

            byte[] result = new byte[(int) fc.size()];
            if (byteBuffer.remaining() > 0) {
                byteBuffer.get(result, 0, byteBuffer.remaining());
            }
            return result;
        } catch (IOException e) {
            e.printStackTrace();
            throw e;
        } finally {
            try {
                rf.close();
                fc.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void copyFile(File file1, File file2)
            throws Exception {
        FileInputStream in = new FileInputStream(file1);
        FileOutputStream ou = new FileOutputStream(file2);
        byte[] buffer = new byte[2097152];
        for (; ; ) {
            int ins = in.read(buffer);
            if (ins == -1) {
                in.close();
                ou.flush();
                ou.close();
                break;
            }
            ou.write(buffer, 0, ins);
        }
    }
}
