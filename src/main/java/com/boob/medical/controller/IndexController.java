package com.boob.medical.controller;

import com.boob.medical.entity.Term;
import com.boob.medical.entity.TermType;
import com.boob.medical.service.ITermService;
import com.boob.medical.service.ITermTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class IndexController {

    @Autowired
    private ITermService termService;

    @Autowired
    private ITermTypeService termTypeService;

    @RequestMapping("/index")
    public String index(@RequestParam(name = "size", defaultValue = "5", required = false) int size, Model model) {
        List<Term> termList = termService.getTermList(size);
        List<TermType> termTypes = termTypeService.getAllTermType();
        model.addAttribute("terms", termList);
        model.addAttribute("termTypes", termTypes);
        return "index";
    }

    @RequestMapping("/")
    public String index() {
        return "redirect:/index";
    }

}
