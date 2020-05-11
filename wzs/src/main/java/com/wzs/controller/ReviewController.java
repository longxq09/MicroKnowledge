package com.wzs.controller;

//import com.wzs.service.MEvidService;
//import com.wzs.service.MGuessService;
import com.wzs.service.ReviewService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/review")
public class ReviewController {

//    @Autowired
//    private MGuessService guessService;
//
//    @Autowired
//    private MEvidService mEvidService;

    @Autowired
    private ReviewService reviewService;





}
