package cn.cerish.controller;

import cn.cerish.entity.ResPageBean;
import cn.cerish.entity.RespBean;
import cn.cerish.entity.Visitor;
import cn.cerish.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/visitor")
public class VisitorController {
    @Autowired
    private VisitorService visitorService;


    @GetMapping("/")
    public ResPageBean getVisitorByPage(@RequestParam Integer page,
                                        @RequestParam Integer size,
                                        Visitor visitor) {
        return visitorService.getVisitorByPage(page, size, visitor);
    }

    @GetMapping("/{id}")
    public Visitor getVisitorById(@PathVariable Integer id) {
        return visitorService.getVisitorById(id);
    }

    @PostMapping("/")
    public RespBean addVisitor(@RequestBody Visitor visitor) {
        if (visitorService.addVisitor(visitor) == 1) {
            return RespBean.success("学生添加成功!");
        }
        return RespBean.error("学生添加失败!");
    }

    @DeleteMapping("/{id}")
    public RespBean deleteVisitorById(@PathVariable Integer id) {
        if (visitorService.deleteVisitorById(id) == 1) {
            return RespBean.success("学生删除成功!");
        }
        return RespBean.error("学生删除失败!");
    }
    @PutMapping("/")
    public RespBean updateVisitorById(@RequestBody Visitor visitor) {
        if (visitorService.updateVisitorById(visitor) == 1) {
            return RespBean.success("学生更新成功!");
        }
        return RespBean.error("学生更新失败!");
    }

}