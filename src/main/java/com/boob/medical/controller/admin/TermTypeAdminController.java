package com.boob.medical.controller.admin;

import com.boob.medical.entity.TermType;
import com.boob.medical.service.ITermTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;


@Controller
@RequestMapping("/admin/term_type")
public class TermTypeAdminController {

    @Autowired
    private ITermTypeService termTypeService;

    @RequestMapping("add_or_update")
    public String addOrUpdate(@RequestParam(name = "old_name", required = false) String oldName,
                      @RequestParam(name = "new_name", required = true) String newName,
                      Model model) {
        TermType termType = new TermType()
                .setName(newName);
        if ("".equals(oldName) || oldName == null) {
            //增加
            boolean checked = termTypeService.insertTermType(termType);
            if (!checked) {
                model.addAttribute("errorMsg", "术语已存在");
            } else {
                model.addAttribute("successMsg", "增加术语类型成功");
            }
        } else {
            boolean checked = termTypeService.updateTermTypeByName(oldName, newName);
            if (!checked) {
                model.addAttribute("errorMsg", "术语已存在");
            } else {
                model.addAttribute("successMsg", "修改术语类型成功");
            }
        }
        return "forward:/term_type/page";
    }

    @RequestMapping("delete")
    public String delete(Long id, Model model) {
        boolean deleted = termTypeService.deleteTermTypeById(id);
        if (!deleted) {
            model.addAttribute("errorMsg", "有术语属于该类型,无法删除");
        } else {
            model.addAttribute("successMsg", "删除术语类型成功");
        }
        return "forward:/term_type/page";
    }


}
