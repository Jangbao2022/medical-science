package com.boob.medical.controller.admin;

import com.boob.medical.entity.Publish;
import com.boob.medical.service.IPublicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
@RequestMapping("/admin/publish")
public class PublicAdminController {

    @Autowired
    private IPublicService publicService;

    @RequestMapping("publish_page")
    public String publishPage(Model model) {
        List<Publish> publishList = publicService.getAllPublish();
        model.addAttribute("publishList", publishList);
        return "publish";
    }

    @RequestMapping("consent")
    public String consent(Long publishId) {
        publicService.consent(publishId);
        return "redirect:/publish/publish_page";
    }

    @RequestMapping("reject")
    public String reject(Long publishId) {
        publicService.reject(publishId);
        return "redirect:/publish/publish_page";
    }
}
