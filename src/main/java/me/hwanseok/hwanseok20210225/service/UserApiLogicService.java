package me.hwanseok.hwanseok20210225.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.entity.User;
import me.hwanseok.hwanseok20210225.model.enumClass.UserStatus;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.UserApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.UserApiResponse;
import me.hwanseok.hwanseok20210225.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class UserApiLogicService implements CrudInterface<UserApiRequest, UserApiResponse> {

    @Autowired
    private UserRepository userRepository;


    @Override
    public Header<UserApiResponse> create(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();

        User user = User.builder()
                .account(userApiRequest.getEmail())
                .password(userApiRequest.getPassword())
                .status(UserStatus.REGISTERED)
                .phoneNumber(userApiRequest.getPhoneNumber())
                .email(userApiRequest.getEmail())
                .registeredAt(LocalDateTime.now())
                .build();

        User newUser = userRepository.save(user);
        return response(newUser);
    }

    @Override
    public Header<UserApiResponse> read(Long id) {
        return userRepository
                .findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<UserApiResponse> update(Header<UserApiRequest> request) {
        UserApiRequest userApiRequest = request.getData();
        Optional<User> optional = userRepository.findById(userApiRequest.getId());

        return optional.map(user -> {
            return user.setAccount(userApiRequest.getAccount())
                    .setPassword(userApiRequest.getPassword())
                    .setStatus(userApiRequest.getStatus())
                    .setEmail(userApiRequest.getEmail())
                    .setPhoneNumber(userApiRequest.getPhoneNumber())
                    .setRegisteredAt(userApiRequest.getRegisteredAt())
                    .setRegisteredAt(userApiRequest.getUnregisteredAt());
        }).map(user -> userRepository.save(user))
                .map(user -> response(user))
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<User> optional = userRepository.findById(id);

        return optional.map(user -> {
            userRepository.delete(user);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private Header<UserApiResponse> response(User user) {

        UserApiResponse userApiResponse = UserApiResponse.builder()
                .id(user.getId())
                .account(user.getAccount())
                .password(user.getPassword())
                .phoneNumber(user.getPhoneNumber())
                .status(user.getStatus())
                .registeredAt(user.getRegisteredAt())
                .unregisteredAt(user.getUnregisteredAt())
                .build();

        return Header.OK(userApiResponse);
    }
}
