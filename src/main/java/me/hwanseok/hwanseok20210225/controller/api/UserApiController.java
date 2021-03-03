package me.hwanseok.hwanseok20210225.controller.api;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import me.hwanseok.hwanseok20210225.controller.CrudController;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.entity.User;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.UserApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.UserApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.UserOrderInfoApiResponse;
import me.hwanseok.hwanseok20210225.service.UserApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/api/user")
@RequiredArgsConstructor
public class UserApiController extends CrudController<UserApiRequest, UserApiResponse, User> {

    private final UserApiLogicService userApiLogicService;

    @GetMapping("/{id}/orderInfo")
    public Header<UserOrderInfoApiResponse> orderInfo(@PathVariable Long id){
        return userApiLogicService.orderInfo(id);
    }

    @GetMapping("/search")
    public Header<List<UserApiResponse>> findAll(@PageableDefault(sort = { "id" }, direction = Sort.Direction.ASC) Pageable pageable){
        log.info("{}",pageable);
        return userApiLogicService.search(pageable);
    }
}


