package com.teachmeskills.lesson_4.com.teachmeskills.lesson_16;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Parser implements IParser {
    public Map<String, Document> docs = new HashMap<>();

    @Override
    public void parse(String pathToFolder, int countToParse) {
        File folder = new File(pathToFolder);

        if(folder.isDirectory()){
            List<File> files = Arrays.stream(folder.listFiles(new MyCustomFileFilter("txt")))
                    .limit(countToParse)
                    .collect(Collectors.toList());

            if(files.size() == 0){
                System.out.println("Нет подходящих файлов");
                return;
            }

            for(File file: files){
                readFile(file);
            }

            System.out.println("End");
        } else {
            System.out.println("Невалидный путь");
        }
    }

    private void readFile(File file){
        Pattern docPattern = Pattern.compile("\\d{4}[-][a-zа-я]{3}[-]\\d{4}[-][a-zа-я]{3}[-]\\d[a-zа-я]\\d[a-zа-я]", Pattern.CASE_INSENSITIVE);
        Pattern phonePattern = Pattern.compile("(\\+*)[(]\\d{2}[)]\\d{7}([\\W\\n\\t]|$)");
        Pattern emailPattern = Pattern.compile("[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}", Pattern.CASE_INSENSITIVE);

        Document doc = new Document();

        try(BufferedReader reader = new BufferedReader(new FileReader(file))) {
            String docOneLine;

            while((docOneLine = reader.readLine()) != null){
                Matcher docMatcher = docPattern.matcher(docOneLine);
                Matcher phoneMatcher = phonePattern.matcher(docOneLine);
                Matcher emailMatcher = emailPattern.matcher(docOneLine);

                if(docMatcher.find()){
                    doc.setDocNumber(docOneLine.substring(docMatcher.start(), docMatcher.end()));
                }

                if(phoneMatcher.find()){
                    doc.setPhoneNumber(docOneLine.substring(phoneMatcher.start(), phoneMatcher.end()));
                }

                if(emailMatcher.find()){
                    doc.setEmail(docOneLine.substring(emailMatcher.start(), emailMatcher.end()));
                }

            }
        } catch(IOException ex){
            System.out.println(ex.getMessage());
        }

        if(doc.getDocNumber() != null || doc.getEmail() != null || doc.getPhoneNumber() != null){
            docs.put(file.getName(), doc);
        }
    }

}
