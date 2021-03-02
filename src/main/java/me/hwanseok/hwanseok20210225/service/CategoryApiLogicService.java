package me.hwanseok.hwanseok20210225.service;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import me.hwanseok.hwanseok20210225.ifs.CrudInterface;
import me.hwanseok.hwanseok20210225.model.entity.Category;
import me.hwanseok.hwanseok20210225.model.entity.Partner;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import me.hwanseok.hwanseok20210225.model.netwrok.request.CategoryApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.request.PartnerApiRequest;
import me.hwanseok.hwanseok20210225.model.netwrok.response.CategoryApiResponse;
import me.hwanseok.hwanseok20210225.model.netwrok.response.PartnerApiResponse;
import me.hwanseok.hwanseok20210225.repository.CategoryRepository;
import me.hwanseok.hwanseok20210225.repository.PartnerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.expression.CachedExpressionEvaluator;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Service
public class CategoryApiLogicService implements CrudInterface<CategoryApiRequest, CategoryApiResponse> {

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public Header<CategoryApiResponse> create(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();

        Category category = Category.builder()
                .id(body.getId())
                .type(body.getType())
                .title(body.getTitle())
                .build();
        Category newCategory = categoryRepository.save(category);
        return response(newCategory);
    }

    @Override
    public Header<CategoryApiResponse> read(Long id) {
        return categoryRepository
                .findById(id)
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header<CategoryApiResponse> update(Header<CategoryApiRequest> request) {
        CategoryApiRequest body = request.getData();
        Optional<Category> optional = categoryRepository.findById(body.getId());

        return optional.map(category -> {
            category.setTitle(body.getTitle())
                    .setType(body.getType());
            return category;
        })
                .map(category -> categoryRepository.save(category))
                .map(this::response)
                .orElseGet(() -> Header.ERROR("데이터 없음"));
    }

    @Override
    public Header delete(Long id) {
        Optional<Category> optional = categoryRepository.findById(id);

        return optional.map(category -> {
            categoryRepository.delete(category);
            return Header.OK();
        }).orElseGet(() -> Header.ERROR("데이터 없음"));

    }

    private Header<CategoryApiResponse> response(Category category) {
         CategoryApiResponse categoryApiResponse = CategoryApiResponse.builder()
                .id(category.getId())
                .type(category.getType())
                .title(category.getTitle())
                .build();
        return Header.OK(categoryApiResponse);
    }
}
