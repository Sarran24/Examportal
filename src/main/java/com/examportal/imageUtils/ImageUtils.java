package com.examportal.imageUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class ImageUtils {
    //	public static byte[] compressImage(byte[] data) {
//        Deflater deflater = new Deflater();
//        deflater.setLevel(Deflater.BEST_COMPRESSION);
//        deflater.setInput(data);
//        deflater.finish();
//
//        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
//        byte[] tmp = new byte[10*1024];
//        while (!deflater.finished()) {
//            int size = deflater.deflate(tmp);
//            outputStream.write(tmp, 0, size);
//        }
//        try {
//            outputStream.close();
//        } catch (Exception ignored) {
//        }
//        return outputStream.toByteArray();
//    }
    public static byte[] compressImage(byte[] data) throws IOException {
        Deflater deflater = new Deflater();
        deflater.setLevel(Deflater.BEST_COMPRESSION);
        deflater.setInput(data);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[10 * 1024];
        while (!deflater.finished()) {
            int size = deflater.deflate(tmp);
            outputStream.write(tmp, 0, size);
        }

        byte[] compressedData = outputStream.toByteArray();
        if (compressedData.length > 5 * 1024 * 1024) {
            throw new IOException("Compressed image size exceeds 5MB");
        }

        try {
            outputStream.close();
        } catch (Exception ignored) {
        }
        return compressedData;
    }


    public static byte[] decompressImage(byte[] data) {
        Inflater inflater = new Inflater();
        inflater.setInput(data);
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(data.length);
        byte[] tmp = new byte[4 * 1024];
        try {
            while (!inflater.finished()) {
                int count = inflater.inflate(tmp);
                outputStream.write(tmp, 0, count);
            }
            outputStream.close();
        } catch (Exception ignored) {
        }
        return outputStream.toByteArray();
    }
}
