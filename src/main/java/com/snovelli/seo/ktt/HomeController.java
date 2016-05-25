package com.snovelli.seo.ktt;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Salvatore on 25/05/2016.
 */
@Controller
public class HomeController {
    @RequestMapping("/")
    @ResponseBody
    String home() {
        return "<H1>Go away!</H1>";
    }
}
