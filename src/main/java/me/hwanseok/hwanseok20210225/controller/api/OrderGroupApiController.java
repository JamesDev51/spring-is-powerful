package me.hwanseok.hwanseok20210225.controller.api;

import me.hwanseok.hwanseok20210225.controller.CrudController;
import me.hwanseok.hwanseok20210225.model.entity.OrderGroup;
import me.hwanseok.hwanseok20210225.model.netwrok.request.OrderGroupApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.OrderGroupApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orderGroup")
public class OrderGroupApiController extends CrudController<OrderGroupApiRequest, OrderGroupApiResponse, OrderGroup> {


}

