package me.hwanseok.hwanseok20210225.controller.api;

import lombok.extern.slf4j.Slf4j;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.AdminUserApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.request.UserApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.AdminUserApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.UserApiResponse;
import me.hwanseok.hwanseok20210225.service.AdminUserApiLogicService;
import me.hwanseok.hwanseok20210225.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/adminUser")
public class AdminUserApiController implements CrudInterface<AdminUserApiRequest, AdminUserApiResponse> {

    @Autowired
    private AdminUserApiLogicService adminUserApiLogicService;

    @Override
    @PostMapping("")
    public Header<AdminUserApiResponse> create(@RequestBody Header<AdminUserApiRequest> userApiRequest) {
        log.info("{}", userApiRequest);
        return adminUserApiLogicService.create(userApiRequest);
    }

    @Override
    @GetMapping("{id}")
    public Header<AdminUserApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("{}", id);
        return adminUserApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<AdminUserApiResponse> update(@RequestBody Header<AdminUserApiRequest> userApiRequest) {
        log.info("{}", userApiRequest);
        return adminUserApiLogicService.update(userApiRequest);
    }             

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable  Long id) {
        log.info("{}", id);
        return adminUserApiLogicService.delete(id);
    }
}

