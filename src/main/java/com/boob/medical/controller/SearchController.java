package com.boob.medical.controller;

import com.boob.medical.dto.PageDto;
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
public class SearchController {

    @Autowired
    private ITermService termService;

    @Autowired
    private ITermTypeService termTypeService;

    @RequestMapping("/search")
    public String search(@RequestParam(name = "page", defaultValue = "1", required = false) int page,
                         @RequestParam(name = "size", defaultValue = "5", required = false) int size,
                         @RequestParam("key_word") String keyWord, Model model) {
        boolean searchTermType = keyWord.contains("#");
        if (searchTermType) {
            //搜索类型
            List<TermType> termTypes = termTypeService.getTermTypesByKeyWord(keyWord);
            model.addAttribute("termTypes", termTypes);
            return "term-type";
        }
        boolean searchTerm = keyWord.contains("@");
        if (searchTerm) {
            //搜索术语
            PageDto<Term> termPageDto = termService.getTermPageByKeyWord(page, size, keyWord);
            model.addAttribute("pageDto", termPageDto);
            return "term";
        }
        return "redirect:/index";
    }

}
