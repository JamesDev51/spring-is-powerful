package me.hwanseok.hwanseok20210225.controller.api;

import lombok.extern.slf4j.Slf4j;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.ItemApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.request.PartnerApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.ItemApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.PartnerApiResponse;
import me.hwanseok.hwanseok20210225.service.ItemApiLogicService;
import me.hwanseok.hwanseok20210225.service.PartnerApiLogicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping("/api/partner")
public class PartnerApiController implements CrudInterface<PartnerApiRequest, PartnerApiResponse> {

    @Autowired
    private PartnerApiLogicService partnerApiLogicService;

    @Override
    @PostMapping("")
    public Header<PartnerApiResponse> create(@RequestBody Header<PartnerApiRequest> request) {
        log.info("{}", request);
        return partnerApiLogicService.create(request);
    }

    @Override
    @GetMapping("{id}")
    public Header<PartnerApiResponse> read(@PathVariable(name = "id") Long id) {
        log.info("{}", id);
        return partnerApiLogicService.read(id);
    }

    @Override
    @PutMapping("")
    public Header<PartnerApiResponse> update(@RequestBody Header<PartnerApiRequest> request) {
        log.info("{}", request);
        return partnerApiLogicService.update(request);
    }             

    @Override
    @DeleteMapping("{id}")
    public Header delete(@PathVariable  Long id) {
        log.info("{}", id);
        return partnerApiLogicService.delete(id);
    }
}

