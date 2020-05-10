package com.boob.medical.dao;

import com.boob.medical.entity.Term;
import org.junit.jupiter.api.Test;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class FileTest {

    public void generatorData放射学() {
        String termType;
        List<Term> terms = new ArrayList<>();
        BufferedReader bf = getBufferedReader("data5.txt");
        try {
            String line = bf.readLine();
            termType = line;
            System.out.println(termType);
            while ((line = bf.readLine()) != null) {
                if (line.length() == 0) {
                    continue;
                }
                String[] strings = line.replace("（", "(")
                        .replace("）", ")")
                        .split(" ");
                //这是一个术语名(中文名和英文名)
                String name = strings[0].replace(":", "").replace(".", "");
                System.out.println(name);
                StringBuilder englishName = new StringBuilder();
                for (int i = 1; i < strings.length - 1; ++i) {
                    englishName.append(strings[i]).append(" ");
                }
                englishName.append(strings[strings.length - 1].replace(":", "").replace(".", ""));
                System.out.println(englishName);
                System.out.println("==================content===================");
                //再读一行读取内容
                String content = bf.readLine()
                        .replace("：", "")
                        .replace(":", "");
                System.out.println(content);
                System.out.println("====================content=================");

                Term term = new Term();
                term.setName(name)
                        .setEnglishName(englishName.toString())
                        .setDescription(content)
                        .setTermTypeId(1L)
                        .setGmtCreated(new Date());
                term.setGmtModified(term.getGmtCreated());
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (bf != null) {
                    bf.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }


    public BufferedReader getBufferedReader(String data) {
        FileReader fileReader;
        BufferedReader bf = null;
        try {
            String path = "F:\\IDEA_project\\medical-science\\src\\test\\java\\com\\boob\\medical\\dao\\" + data;
            File file = new File(path);
            fileReader = new FileReader(file);
            bf = new BufferedReader(fileReader);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return bf;
    }


}
