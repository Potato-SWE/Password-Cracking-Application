package com.potato.passwordcracking.service;

import com.potato.passwordcracking.model.PasswordCrackingResponse;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

public class ResponseOutputService {

    private static String outputDir = System.getProperty("user.dir").replace('\\', '/') + "/output/";

    public static String write(PasswordCrackingResponse response) throws IOException {
        if (response == null) {
            return null;
        }

        String responseStr = responseToString(response);
        String fileName = buildFileName();

        PrintWriter printWriter = new PrintWriter(new BufferedWriter(new FileWriter(fileName)));
        printWriter.println(responseStr);
        printWriter.close();

        return fileName;
    }

    private static String responseToString(PasswordCrackingResponse response) {
        StringBuilder responseStringBuilder = new StringBuilder();
        responseStringBuilder.append("---------- Password Cracking Response ----------\n");
        if (response.getMessage() != null) {
            responseStringBuilder.append("Message: " + response.getMessage() + "\n");
        }
        if (response.getCrackedPasswordMap() != null) {
            responseStringBuilder.append("" + response.getCrackedPasswordMap() + "\n");
        }
        return responseStringBuilder.toString();
    }

    private static String buildFileName() {

        String fileName = outputDir;
        String appender = ".txt";

        //create output folder if it does not exist
        if (!Files.isDirectory(Path.of(fileName))) {
            File file = new File(fileName);
            file.mkdirs();
        }

        fileName = fileName + "output_" + getDate();
        if (!Files.exists(Path.of(fileName + appender))) {
            return fileName + appender;
        }
        int count = 1;
        while (Files.exists(Path.of(fileName + '(' + count + ')' + appender))) {
            count++;
        }

        return fileName + '(' + count + ')' + appender;
    }

    private static String getDate() {
        return new SimpleDateFormat("yyyy_MM_dd").format(new java.util.Date());
    }
}
