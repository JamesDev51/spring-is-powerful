package me.hwanseok.hwanseok20210225.controller.api;

import lombok.extern.slf4j.Slf4j;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.CategoryApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.request.ItemApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.CategoryApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.ItemApiResponse;
import me.hwanseok.hwanseok20210225.service.CategoryApiLogicService;
import me.hwanseok.hwanseok20210225.service.ItemApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/category")
public class CategoryApiController implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryApiLogicService categoryApiLogicService;

    @Override
    @PostMapping("")
    public Header<CategoryApiResponse> create(@RequestBody Header<CategoryApiRequest> request) {
        log.info("{}", request);
        return categoryApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<CategoryApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("{}", id);
        return categoryApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<CategoryApiResponse> update(@RequestBody Header<CategoryApiRequest> request) {
        log.info("{}", request);
        return categoryApiLogicService.update(request);
    }             

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable  Long id) {
        log.info("{}", id);
        return categoryApiLogicService.delete(id);
    }
}

