package com.boob.medical.controller;

import com.boob.medical.dto.PageDto;
import com.boob.medical.entity.Term;
import com.boob.medical.service.ITermService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("/term")
public class TermController {

    @Autowired
    private ITermService termService;

    @GetMapping("page")
    public String pageTerm(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                           @RequestParam(name = "size", defaultValue = "5", required = false) int size,
                           Model model) {
        PageDto<Term> termPage = termService.getTermPage(page, size);
        model.addAttribute("pageDto", termPage);
        return "term";
    }

    @GetMapping("page_by_type")
    public String pageTermByType(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                                 @RequestParam(name = "size", defaultValue = "5", required = false) int size,
                                 @RequestParam(name = "type_id") long typeId,
                                 Model model) {
        PageDto<Term> termPage = termService.getTermPageByType(page, size, typeId);
        model.addAttribute("pageDto", termPage);
        return "term";
    }

    @GetMapping("find_by_id")
    public String findById(Long id, Model model) {
        Term term = termService.getTermById(id);
        model.addAttribute("term", term);
        return "term-page";
    }
}
