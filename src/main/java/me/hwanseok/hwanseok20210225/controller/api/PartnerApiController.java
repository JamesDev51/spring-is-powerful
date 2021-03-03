package me.hwanseok.hwanseok20210225.controller.api;

import me.hwanseok.hwanseok20210225.controller.CrudController;
import me.hwanseok.hwanseok20210225.model.entity.Partner;
import me.hwanseok.hwanseok20210225.model.netwrok.request.PartnerApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.PartnerApiResponse;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/partner")
public class PartnerApiController extends CrudController<PartnerApiRequest, PartnerApiResponse, Partner> {
}

