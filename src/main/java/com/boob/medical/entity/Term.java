package com.boob.medical.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.persistence.*;
import java.util.Date;

/**
 * 术语实体类
 */
@Entity
@Table(name = "term")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class Term {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    /**
     * 英文名
     */
    private String englishName;
    private Long termTypeId;
    private String termTypeName;
    /**
     * 描述
     */
    private String description;
    private Date gmtCreated;
    private Date gmtModified;
}
