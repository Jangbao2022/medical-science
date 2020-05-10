package com.boob.medical.entity;

import lombok.Data;

import java.util.List;

/**
 * 医学术语文件实体
 */
@Data
public class FileData {

    private TermType termType;
    private List<Term> terms;

}
