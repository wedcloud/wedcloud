package club.wedcloud.www.controller;

import club.wedcloud.www.service.impl.ManagerService;
import club.wedcloud.www.utils.ResponseBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/v1")
public class ManagerController {
    @Autowired
    private ManagerService service;

    @GetMapping("/manager")
    public ResponseEntity<ResponseBean> getManagerList() {
        return ResponseEntity.ok(ResponseBean.ok(service.getList()));
    }
}
