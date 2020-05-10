package com.boob.medical.controller.admin;

import com.boob.medical.entity.Term;
import com.boob.medical.entity.TermType;
import com.boob.medical.service.ITermService;
import com.boob.medical.service.ITermTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/term")
public class TermAdminController {

    @Autowired
    private ITermService termService;

    @Autowired
    private ITermTypeService termTypeService;

    @RequestMapping("add_page")
    public String addPage(Model model) {
        List<TermType> termTypes = termTypeService.getAllTermType();
        model.addAttribute("termTypes", termTypes);
        return "term_update";
    }

    @RequestMapping("update_page")
    public String updatePage(Long id, Model model) {
        Term term = termService.getTermById(id);
        model.addAttribute("term", term);
        return "forward:/admin/term/add_page";
    }

    @RequestMapping("add_or_update")
    public String addOrUpdate(Term term, Model model) {
        TermType termType = termTypeService.getTermTypeById(term.getTermTypeId());
        term.setTermTypeName(termType.getName());
        if (term.getId() == null) {
            //增加
            term = termService.insertTerm(term);
        } else {
            //更新
            term = termService.updateTerm(term);
        }
        model.addAttribute("term", term);
        return "term-page";
    }

    @RequestMapping("delete")
    public String delete(Long id, Model model) {
        boolean deleted = termService.deleteTermById(id);
        if (!deleted) {
            model.addAttribute("errorMsg", "删除失败");
        } else {
            model.addAttribute("successMsg", "删除术语成功");
        }
        return "forward:/term/page";
    }

}
