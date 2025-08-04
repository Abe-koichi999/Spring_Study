package com.jutjoy.domain.repository.profile;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.jutjoy.domain.entity.profile.Profile;

@Repository // Springがこのインターフェースをリポジトリとして認識する
public interface ProfileRepository extends JpaRepository<Profile, Integer> {
    // クエリメソッド
    public List<Profile> findAllByOrderById();

    // 検索機能の実装
    public List<Profile> findByNameLike(String name);
}
