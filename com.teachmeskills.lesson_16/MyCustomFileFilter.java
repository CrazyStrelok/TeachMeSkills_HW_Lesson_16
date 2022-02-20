package com.teachmeskills.lesson_4.com.teachmeskills.lesson_16;

import java.io.File;
import java.io.FilenameFilter;

public class MyCustomFileFilter implements FilenameFilter {
    private String ext;

    public MyCustomFileFilter(String ext){
        this.ext = ext.toLowerCase();
    }

    @Override
    public boolean accept(File dir, String name) {
        return name.toLowerCase().endsWith(ext);
    }

}
