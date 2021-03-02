package me.hwanseok.hwanseok20210225.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.entity.OrderDetail;
import me.hwanseok.hwanseok20210225.model.entity.OrderGroup;
import me.hwanseok.hwanseok20210225.model.enumClass.OrderDetailStatus;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.OrderDetailApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.request.OrderGroupApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.OrderDetailApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.OrderGroupApiResponse;
import me.hwanseok.hwanseok20210225.repository.ItemRepository;
import me.hwanseok.hwanseok20210225.repository.OrderDetailRepository;
import me.hwanseok.hwanseok20210225.repository.OrderGroupRepository;
import me.hwanseok.hwanseok20210225.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class OrderDetailApiLogicService implements CrudInterface<OrderDetailApiRequest, OrderDetailApiResponse> {

    @Autowired
    private OrderDetailRepository orderDetailRepository;

    @Autowired
    private ItemRepository itemRepository;

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Override
    public Header<OrderDetailApiResponse> create(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();

        OrderDetail orderDetail = OrderDetail.builder()
                .status(OrderDetailStatus.CONFIRM)
                .arrivalDate(body.getArrivalDate())
                .quantity(body.getQuantity())
                .totalPrice(body.getTotalPrice())
                .orderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                .item(itemRepository.getOne(body.getItemId()))
                .build();
        OrderDetail newOrderDetail = orderDetailRepository.save(orderDetail);
        return response(newOrderDetail);
    }

    @Override
    public Header<OrderDetailApiResponse> read(Long id) {
        return orderDetailRepository
                .findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderDetailApiResponse> update(Header<OrderDetailApiRequest> request) {
        OrderDetailApiRequest body = request.getData();
        Optional<OrderDetail> optional = orderDetailRepository.findById(body.getId());

        return optional.map(orderDetail -> {
            orderDetail.setStatus(body.getStatus())
                    .setArrivalDate(body.getArrivalDate())
                    .setQuantity(body.getQuantity())
                    .setTotalPrice(body.getTotalPrice())
                    .setOrderGroup(orderGroupRepository.getOne(body.getOrderGroupId()))
                    .setItem(itemRepository.getOne(body.getItemId()));
            return orderDetail;
        })
                .map(orderDetail -> orderDetailRepository.save(orderDetail))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderDetail> optional = orderDetailRepository.findById(id);

        return optional.map(item -> {
            orderDetailRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private Header<OrderDetailApiResponse> response(OrderDetail orderDetail) {

        OrderDetailApiResponse orderDetailApiResponse = OrderDetailApiResponse.builder()
                .id(orderDetail.getId())
                .status(orderDetail.getStatus())
                .arrivalDate(orderDetail.getArrivalDate())
                .orderGroupId(orderDetail.getOrderGroup().getId())
                .itemId(orderDetail.getItem().getId())
                .build();
        return Header.OK(orderDetailApiResponse);
    }
}
