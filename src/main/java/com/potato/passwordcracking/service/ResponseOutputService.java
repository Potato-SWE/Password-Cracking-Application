package com.potato.passwordcracking.service;

import com.potato.passwordcracking.model.PasswordCrackingRequest;
import com.potato.passwordcracking.model.PasswordCrackingResponse;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.text.SimpleDateFormat;

public class ResponseOutputService {

    private String outputDir = System.getProperty("user.dir").replace('\\', '/')+"/output/";

    public void write(PasswordCrackingRequest request) {
        
    }

    public void write(PasswordCrackingResponse response) {

    }

    private String getFileName() {

        String fileName = outputDir;
        String appender = ".txt";

        //create output folder if it does not exist
        if(!Files.isDirectory(Path.of(fileName))) {
            File file = new File(fileName);
            file.mkdirs();
        }

        fileName = fileName + "output_"+getDate();
        if(!Files.exists(Path.of(fileName+appender))) {
            return fileName+appender;
        }
        int count = 1;
        while(Files.exists(Path.of(fileName+'('+ count +')'+appender))) {
            count++;
        }

        return fileName+'('+count+')'+appender;
    }

    private String getDate() {
        return new SimpleDateFormat("yyyy_MM_dd").format(new java.util.Date());
    }
}
