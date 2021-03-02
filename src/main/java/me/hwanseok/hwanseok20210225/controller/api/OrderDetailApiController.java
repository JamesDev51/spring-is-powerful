package me.hwanseok.hwanseok20210225.controller.api;

import lombok.extern.slf4j.Slf4j;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.OrderDetailApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.request.OrderGroupApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.OrderDetailApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.OrderGroupApiResponse;
import me.hwanseok.hwanseok20210225.service.OrderDetailApiLogicService;
import me.hwanseok.hwanseok20210225.service.OrderGroupApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/orderDetail")
public class OrderDetailApiController implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailApiLogicService orderDetailApiLogicService;

    @Override
    @PostMapping("")
    public Header<OrderDetailApiResponse> create(@RequestBody Header<OrderDetailApiRequest> request) {
        log.info("{}", request);
        return orderDetailApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<OrderDetailApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("{}", id);
        return orderDetailApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<OrderDetailApiResponse> update(@RequestBody Header<OrderDetailApiRequest> request) {
        log.info("{}", request);
        return orderDetailApiLogicService.update(request);
    }             

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable  Long id) {
        log.info("{}", id);
        return orderDetailApiLogicService.delete(id);
    }
}

