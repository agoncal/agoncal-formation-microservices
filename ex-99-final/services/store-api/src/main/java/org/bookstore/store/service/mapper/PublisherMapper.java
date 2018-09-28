package org.bookstore.store.service.mapper;

import org.bookstore.store.domain.Publisher;
import org.bookstore.store.service.dto.PublisherDTO;
import org.mapstruct.Mapper;

/**
 * Mapper for the entity Publisher and its DTO PublisherDTO.
 */
@Mapper(componentModel = "spring", uses = {})
public interface PublisherMapper extends EntityMapper<PublisherDTO, Publisher> {



    default Publisher fromId(Long id) {
        if (id == null) {
            return null;
        }
        Publisher publisher = new Publisher();
        publisher.setId(id);
        return publisher;
    }
}
