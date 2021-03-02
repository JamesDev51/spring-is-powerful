package me.hwanseok.hwanseok20210225.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.entity.Item;
import me.hwanseok.hwanseok20210225.model.entity.Partner;
import me.hwanseok.hwanseok20210225.model.entity.User;
import me.hwanseok.hwanseok20210225.model.enumClass.ItemStatus;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.ItemApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.request.UserApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.ItemApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.UserApiResponse;
import me.hwanseok.hwanseok20210225.repository.ItemRepository;
import me.hwanseok.hwanseok20210225.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class ItemApiLogicService implements CrudInterface<ItemApiRequest, ItemApiResponse> {

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private PartnerRepository partnerRepository;

    @Override
    public Header<ItemApiResponse> create(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();

        Item item = Item.builder()
                .status(ItemStatus.REGISTERED)
                .name(itemApiRequest.getName())
                .title(itemApiRequest.getTitle())
                .content(itemApiRequest.getContent())
                .price(itemApiRequest.getPrice())
                .brandName(itemApiRequest.getBrandName())
                .registeredAt(itemApiRequest.getRegisteredAt())
                .unregisteredAt(itemApiRequest.getUnregisteredAt())
                .partner(partnerRepository.getOne(itemApiRequest.getPartnerId()))
                .build();
        Item newItem = itemRepository.save(item);
        return response(newItem);
    }

    @Override
    public Header<ItemApiResponse> read(Long id) {
        return itemRepository
                .findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<ItemApiResponse> update(Header<ItemApiRequest> request) {
        ItemApiRequest itemApiRequest = request.getData();
        Optional<Item> optional = itemRepository.findById(itemApiRequest.getId());

        return optional.map(item -> {
            item.setStatus(itemApiRequest.getStatus())
                    .setName(itemApiRequest.getName())
                    .setTitle(itemApiRequest.getTitle())
                    .setContent(itemApiRequest.getContent())
                    .setPrice(itemApiRequest.getPrice())
                    .setBrandName(itemApiRequest.getBrandName())
                    .setRegisteredAt(itemApiRequest.getRegisteredAt())
                    .setUnregisteredAt(itemApiRequest.getUnregisteredAt());
            return item;
        })
                .map(item -> itemRepository.save(item))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Item> optional = itemRepository.findById(id);

        return optional.map(item -> {
            itemRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private Header<ItemApiResponse> response(Item item) {

        ItemApiResponse itemApiResponse = ItemApiResponse.builder()
                .id(item.getId())
                .status(item.getStatus())
                .name(item.getName())
                .content(item.getContent())
                .price(item.getPrice())
                .brandName(item.getBrandName())
                .partnerId(item.getPartner().getId())
                .build();
        return Header.OK(itemApiResponse);
    }
}
