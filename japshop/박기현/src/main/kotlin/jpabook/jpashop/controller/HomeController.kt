package jpabook.jpashop.controller

import jpabook.jpashop.util.logger
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping


@Controller
class HomeController {
    val log = logger<HomeController>()

    @RequestMapping("/")
    fun home(): String {
        log.info("home controller")
        return "home"
    }
}