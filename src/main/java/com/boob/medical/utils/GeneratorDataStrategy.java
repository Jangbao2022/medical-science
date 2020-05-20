//package com.boob.medical.utils;
//
//import com.boob.medical.entity.Term;
//
//import java.util.Date;
//
//public interface GeneratorDataStrategy {
//
//    Term generatorData(String line1, String line2);
//
//    String getPath();
//
//    class GeneratorData放射学术语Strategy implements GeneratorDataStrategy {
//        public Term generatorData(String line1, String line2) {
//            String[] strings = line1.split(" | ");
//            //这是一个术语名(中文名和英文名)
//            String name = strings[0];
//            StringBuilder englishName = new StringBuilder();
//            for (int i = 1; i < strings.length - 1; ++i) {
//                englishName.append(strings[i]).append(" ");
//            }
//            englishName.append(strings[strings.length - 1]);
//
//            String content = line2;
//            Term term = new Term(null, name, englishName.toString(), 1L, "放射学术语", content, new Date(), null);
//            term.setGmtModified(term.getGmtCreated());
//            return term;
//        }
//
//        @Override
//        public String getPath() {
//            return "data/data1.txt";
//        }
//    }
//
//    class GeneratorData解剖学术语Strategy implements GeneratorDataStrategy {
//        public Term generatorData(String line1, String line2) {
//            String[] strings = line1.split(" | ");
//            //这是一个术语名(中文名和英文名)
//            String name = strings[0];
//            System.out.println(name);
//            String englishName = strings[1].replace("（", "")
//                    .replace("）", "")
//                    .replace("：", "");
//
//            String content = line2;
//            Term term = new Term(null, name, englishName, 2L, "解剖学术语", content, new Date(), null);
//            term.setGmtModified(term.getGmtCreated());
//            return term;
//        }
//
//        @Override
//        public String getPath() {
//            return "data/data2.txt";
//        }
//
//    }
//
//    class GeneratorData成像术语Strategy implements GeneratorDataStrategy {
//        public Term generatorData(String line1, String line2) {
//            String[] strings = line1.split("\\(");
//            //这是一个术语名(中文名和英文名)
//            String name = strings[0];
//            String englishName = strings[1]
//                    .replace(" ", "")
//                    .replace(")", "")
//                    .replace("。", "")
//                    .replace(":", "")
//                    .replace("：", "");
//            String content = line2
//                    .replace("：", "")
//                    .replace(":", "");
//
//            Term term = new Term(null, name, englishName, 3L, "成像术语", content, new Date(), null);
//            term.setGmtModified(term.getGmtCreated());
//            return term;
//        }
//
//        @Override
//        public String getPath() {
//            return "data/data3.txt";
//        }
//    }
//
//    class GeneratorData物理学术语Strategy implements GeneratorDataStrategy {
//        public Term generatorData(String line1, String line2) {
//            String[] strings = line1.replace("（", "(")
//                    .replace("）", ")")
//                    .split("\\(");
//            //这是一个术语名(中文名和英文名)
//            String name = strings[0];
//            String englishName = strings[1]
//                    .replace(" ", "")
//                    .replace(")", "")
//                    .replace("。", "")
//                    .replace(":", "")
//                    .replace("：", "");
//            //再读一行读取内容
//            String content = line2
//                    .replace("：", "")
//                    .replace(":", "");
//            Term term = new Term(null, name, englishName, 4L, "物理学术语", content, new Date(), null);
//            term.setGmtModified(term.getGmtCreated());
//            return term;
//        }
//
//        @Override
//        public String getPath() {
//            return "data/data4.txt";
//        }
//    }
//
//    class GeneratorData影像学术语Strategy implements GeneratorDataStrategy {
//        public Term generatorData(String line1, String line2) {
//            String[] strings = line1.replace("（", "(")
//                    .replace("）", ")")
//                    .split(" ");
//            //这是一个术语名(中文名和英文名)
//            String name = strings[0].replace(":", "").replace(".", "");
//            StringBuilder englishName = new StringBuilder();
//            for (int i = 1; i < strings.length - 1; ++i) {
//                englishName.append(strings[i]).append(" ");
//            }
//            englishName.append(strings[strings.length - 1].replace(":", "").replace(".", ""));
//            //再读一行读取内容
//            String content = line2
//                    .replace("：", "")
//                    .replace(":", "");
//
//            Term term = new Term(null, name, englishName.toString(), 5L, "影像学术语", content, new Date(), null);
//            term.setGmtModified(term.getGmtCreated());
//            return term;
//        }
//
//        @Override
//        public String getPath() {
//            return "data/data5.txt";
//        }
//    }
//
//}
//
