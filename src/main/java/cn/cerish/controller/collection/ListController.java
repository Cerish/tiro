package cn.cerish.controller.collection;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/collection")
public class ListController {
    @GetMapping("/list")
    public String getList() {
        return "Collection List";
    }
}
