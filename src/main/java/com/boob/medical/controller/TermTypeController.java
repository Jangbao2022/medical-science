package com.boob.medical.controller;

import com.boob.medical.dto.PageDto;
import com.boob.medical.entity.TermType;
import com.boob.medical.service.ITermTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequestMapping("term_type")
public class TermTypeController {

    @Autowired
    private ITermTypeService termTypeService;

    @RequestMapping("page")
    public String pageTerm(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                           @RequestParam(name = "size", defaultValue = "5", required = false) int size,
                           Model model) {
        List<TermType> termTypes = termTypeService.getAllTermType();
        model.addAttribute("termTypes", termTypes);
        return "term-type";
    }

    @RequestMapping("find_by_id")
    public String findById(Long id, Model model) {
        TermType termType = termTypeService.getTermTypeById(id);
        model.addAttribute("termType", termType);
        return "term-page";
    }

}
