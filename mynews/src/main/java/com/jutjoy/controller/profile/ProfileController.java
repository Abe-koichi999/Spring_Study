package com.jutjoy.controller.profile;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.form.profile.ProfileCreateForm;
import com.jutjoy.domain.form.profile.ProfileEditForm;
import com.jutjoy.domain.service.profile.ProfileCreateService;
import com.jutjoy.domain.service.profile.ProfileDeleteService;
import com.jutjoy.domain.service.profile.ProfileEditService;
import com.jutjoy.domain.service.profile.ProfileListService;

@Controller

@RequestMapping("/profile")
public class ProfileController {
    
    @Autowired
    private ProfileCreateService profileCreateService;
    
    @Autowired
    private ProfileListService profileListService;
    
    @Autowired
    private ProfileEditService profileEditService;
    
    @Autowired
    private ProfileDeleteService profileDeleteService;

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
    
    @GetMapping("/profile/{action}/complete")
    public String complete(@PathVariable(name = "action") String action, Model model) {

        return "profile/complete";
    }
    
    @GetMapping("/list")
    public String list(@RequestParam(name = "name", required = false) String name, Model model) {

        List<Profile> profileList = profileListService.list(name);
        model.addAttribute("profileList", profileList);
        model.addAttribute("name", name);

        return "profile/list";
    }
    
    @GetMapping("/edit/{id}")
    public String edit(@ModelAttribute("form") ProfileEditForm profileEditForm,
            @PathVariable(name = "id") int id, Model model) {

        Profile profile = profileEditService.findProfile(id);
        model.addAttribute("profile", profile);

        return "profile/edit";
    }

    @PostMapping("/edit/{id}")
    public String edit(@PathVariable(name = "id") int id,
            @Validated @ModelAttribute("form") ProfileEditForm profileEditForm, BindingResult result,
            Model model) {

        if (result.hasErrors()) {
            return edit(profileEditForm, id, model);
        }
        profileEditService.edit(id, profileEditForm);

        return "redirect:/profile/edit/complete";
    }
    
    @PostMapping("/delete")
    public String delete(@RequestParam(name = "id", required = true) int id, Model model) {
        profileDeleteService.delete(id);
        return "redirect:/profile/list";
    }
}