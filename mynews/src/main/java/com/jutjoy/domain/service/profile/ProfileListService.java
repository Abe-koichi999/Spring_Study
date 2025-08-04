package com.jutjoy.domain.service.profile;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.profile.Profile;
import com.jutjoy.domain.repository.profile.ProfileRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProfileListService {

    @Autowired
    private ProfileRepository ProfileRepository;

    public List<Profile> list(String name) {

        List<Profile> profileList = new ArrayList<>();

        if (Objects.isNull(name) || name.isEmpty()) {
            // リポジトリで定義したメソッド呼び出し。一覧取得
            profileList = ProfileRepository.findAllByOrderById();
        } else {
            // 検索
            profileList = ProfileRepository.findByNameLike(createLikeParam(name));
        }

        return profileList;
    }

    private String createLikeParam(String param) {
        return "%" + param + "%";
    }

}