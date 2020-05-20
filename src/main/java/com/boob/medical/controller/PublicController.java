package com.boob.medical.controller;

import com.boob.medical.entity.Publish;
import com.boob.medical.entity.Term;
import com.boob.medical.entity.TermType;
import com.boob.medical.entity.User;
import com.boob.medical.service.IPublicService;
import com.boob.medical.service.ITermService;
import com.boob.medical.service.ITermTypeService;
import com.boob.medical.service.impl.PublishServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/publish")
public class PublicController {

    @Autowired
    private ITermService termService;

    @Autowired
    private ITermTypeService termTypeService;

    @Autowired
    private IPublicService publicService;

    @RequestMapping("do_publish")
    public String publish(HttpServletRequest request, Term term) {
        User user = (User) request.getSession().getAttribute("user");
        TermType termType = termTypeService.getTermTypeById(term.getTermTypeId());
        term.setTermTypeName(termType.getName());
        term.setType(0);
        //发布
        term = termService.insertTerm(term);
        Publish publish = new Publish()
                .setType(1)
                .setUserId(user.getId())
                .setTermId(term.getId())
                .setTermName(term.getName());
        //存入数据库
        publicService.insertPublish(publish);
        return "redirect:/publish/user_publish_page";
    }

    @RequestMapping("user_publish_page")
    public String UserPublishPage(HttpServletRequest request, Model model) {
        User user = (User) request.getSession().getAttribute("user");
        List<Publish> publishList = publicService.getPublishListByUserId(user.getId());
        model.addAttribute("publishList", publishList);
        return "publish";
    }




}
