package me.hwanseok.hwanseok20210225.controller.api;

import me.hwanseok.hwanseok20210225.controller.CrudController;
import me.hwanseok.hwanseok20210225.model.entity.Item;
import me.hwanseok.hwanseok20210225.model.netwrok.request.ItemApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.ItemApiResponse;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/item")
public class ItemApiController extends CrudController<ItemApiRequest, ItemApiResponse, Item> {

}

