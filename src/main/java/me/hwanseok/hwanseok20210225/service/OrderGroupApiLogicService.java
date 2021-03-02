package me.hwanseok.hwanseok20210225.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.entity.Item;
import me.hwanseok.hwanseok20210225.model.entity.OrderGroup;
import me.hwanseok.hwanseok20210225.model.enumClass.OrderGroupStatus;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.ItemApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.request.OrderGroupApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.ItemApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.OrderGroupApiResponse;
import me.hwanseok.hwanseok20210225.repository.*;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class OrderGroupApiLogicService implements CrudInterface<OrderGroupApiRequest, OrderGroupApiResponse> {

    @Autowired
    private OrderGroupRepository orderGroupRepository;

    @Autowired
    private UserRepository userRepository;

    @Override
    public Header<OrderGroupApiResponse> create(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();

        OrderGroup orderGroup = OrderGroup.builder()
                .status(OrderGroupStatus.EACH)
                .orderType(body.getOrderType())
                .revAddress(body.getRevAddress())
                .revName(body.getRevName())
                .paymentType(body.getPaymentType())
                .totalPrice(body.getTotalPrice())
                .totalQuantity(body.getTotalQuantity())
                .orderAt(body.getOrderAt())
                .arrivalDate(body.getArrivalDate())
                .user(userRepository.getOne(body.getUserId()))
                .build();
        OrderGroup newOrderGroup = orderGroupRepository.save(orderGroup);
        return response(newOrderGroup);
    }

    @Override
    public Header<OrderGroupApiResponse> read(Long id) {
        return orderGroupRepository
                .findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<OrderGroupApiResponse> update(Header<OrderGroupApiRequest> request) {
        OrderGroupApiRequest body = request.getData();
        Optional<OrderGroup> optional = orderGroupRepository.findById(body.getId());

        return optional.map(orderGroup -> {
            orderGroup.setStatus(body.getStatus())
                    .setOrderType(body.getOrderType())
                    .setRevAddress(body.getRevAddress())
                    .setRevName(body.getRevName())
                    .setTotalPrice(body.getTotalPrice())
                    .setTotalQuantity(body.getTotalQuantity())
                    .setOrderAt(body.getOrderAt())
                    .setArrivalDate(body.getArrivalDate())
                    .setUser(userRepository.getOne(body.getUserId()));
            return orderGroup;
        })
                .map(orderGroup -> orderGroupRepository.save(orderGroup))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<OrderGroup> optional = orderGroupRepository.findById(id);

        return optional.map(item -> {
            orderGroupRepository.delete(item);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private Header<OrderGroupApiResponse> response(OrderGroup orderGroup) {

        OrderGroupApiResponse orderGroupApiResponse = OrderGroupApiResponse.builder()
                .id(orderGroup.getId())
                .status(orderGroup.getStatus())
                .orderType(orderGroup.getOrderType())
                .revAddress(orderGroup.getRevAddress())
                .revName(orderGroup.getRevName())
                .totalPrice(orderGroup.getTotalPrice())
                .totalQuantity(orderGroup.getTotalQuantity())
                .orderAt(orderGroup.getOrderAt())
                .arrivalDate(orderGroup.getArrivalDate())
                .userId(orderGroup.getUser().getId())
                .build();
        return Header.OK(orderGroupApiResponse);
    }
}
