package com.wzs.controller;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;

import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.*;

/**
 * @Description: TODO
 * @Author Wazak
 * @Date 2020/5/21 0:29
 */
@SpringBootTest
class RecommendControllerTest {

    @Resource
    private RecommendController recommendController;

    @Test
    void refreshUserRating() {
        recommendController.refreshUserRating(1);
    }

    @Test
    void getTopicUserVec() {
        System.out.println(Arrays.toString(recommendController.getTopicUserVec(1)));
    }

    @Test
    void getTopicNoticeVec() {
        System.out.println(Arrays.toString(recommendController.getTopicNoticeVec(1)));
    }
}