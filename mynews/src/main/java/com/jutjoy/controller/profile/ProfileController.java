package com.jutjoy.controller.profile;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.service.profile.ProfileCreateService;

@Controller
@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private ProfileCreateService profileCreateService;

    @GetMapping("/create")
    public String create(@ModelAttribute("form") ProfileCreateForm profileCreateForm) {
        return "profile/create";
    }
    
    @PostMapping("/create")
    public String create(@Validated @ModelAttribute("form") ProfileCreateForm profileCreateForm,
            BindingResult result, Model model) {

        if (result.hasErrors()) {
            return "profile/create";
        }

        profileCreateService.create(profileCreateForm);

        return "redirect:/profile/create/complete";
    }
    
    @GetMapping("/create/complete")
    public String complete() {
        return "profile/complete";
    }
}