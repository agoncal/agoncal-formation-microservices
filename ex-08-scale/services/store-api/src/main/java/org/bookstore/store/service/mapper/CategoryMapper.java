package org.bookstore.store.service.mapper;

import org.bookstore.store.domain.Category;
import org.bookstore.store.service.dto.CategoryDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Category and its DTO CategoryDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface CategoryMapper extends EntityMapper<CategoryDTO, Category> {



    default Category fromId(Long id) {
        if (id == null) {
            return null;
        }
        Category category = new Category();
        category.setId(id);
        return category;
    }
}
