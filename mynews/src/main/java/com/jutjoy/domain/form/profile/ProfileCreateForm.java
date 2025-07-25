package com.jutjoy.domain.form.profile;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

import lombok.Data;

@Data
public class ProfileCreateForm {

    @NotEmpty(message = "＊名前を入力してください。")
    @Size(max = 20, message = "＊名前は20文字以内で設定してください。")
    private String name;

    @NotEmpty(message = "＊どちらか選んでください。")
    private String gender;
    
    @Size(max = 20, message = "＊趣味は40文字以内で設定してください。")
    private String hobby;
    
    @Size(max = 20, message = "＊自己紹介は100文字以内で設定してください。")
    private String introduction;
    
}