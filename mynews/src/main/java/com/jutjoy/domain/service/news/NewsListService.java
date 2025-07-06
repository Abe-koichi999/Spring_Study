package com.jutjoy.domain.service.news;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.jutjoy.domain.entity.news.News;
import com.jutjoy.domain.repository.NewsRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class NewsListService {

    @Autowired
    private NewsRepository newsRepository;

    public List<News> list() {

        List<News> newsList = new ArrayList<>();
        // 一覧取得
        newsList = newsRepository.findAllByOrderById();

        return newsList;
    }
}