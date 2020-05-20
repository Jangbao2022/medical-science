//package com.boob.medical.utils;
//
//import com.boob.medical.entity.FileData;
//import com.boob.medical.entity.Term;
//import com.boob.medical.entity.TermType;
//
//import java.io.BufferedReader;
//import java.io.File;
//import java.io.FileReader;
//import java.io.IOException;
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//
///**
// * 生成初始化数据
// */
//public class GeneDataUtils {
//
//    public static FileData geneData(GeneratorDataStrategy generatorDataStrategy) {
//        FileData fileData = new FileData();
//        FileReader fileReader = null;
//        BufferedReader bf = null;
//        try {
//            File file = new File(generatorDataStrategy.getPath());
//            fileReader = new FileReader(file);
//            bf = new BufferedReader(fileReader);
//
//            String line = bf.readLine();
//            TermType termType = new TermType();
//            termType.setName(line);
//            termType.setGmtCreated(new Date());
//            termType.setGmtModified(termType.getGmtModified());
//            fileData.setTermType(termType);
//
//            List<Term> terms = new ArrayList<>();
//            while ((line = bf.readLine()) != null) {
//                if (line.length() == 0) {
//                    continue;
//                }
//                String line2 = bf.readLine();
//                Term term = generatorDataStrategy.generatorData(line, line2);
//                terms.add(term);
//            }
//            fileData.setTerms(terms);
//        } catch (IOException e) {
//            e.printStackTrace();
//        } finally {
//            try {
//                if (bf != null) {
//                    bf.close();
//                }
//                if (fileReader != null) {
//                    fileReader.close();
//                }
//            } catch (IOException e) {
//                e.printStackTrace();
//            }
//        }
//        return fileData;
//    }
//
//}
