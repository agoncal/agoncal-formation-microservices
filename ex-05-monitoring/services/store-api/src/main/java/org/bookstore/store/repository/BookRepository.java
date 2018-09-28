package org.bookstore.store.repository;

import org.bookstore.store.domain.Book;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

/**
 * Spring Data  repository for the Book entity.
 */
@SuppressWarnings("unused")
@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Query(value = "select distinct str_book from Book str_book left join fetch str_book.authors",
        countQuery = "select count(distinct str_book) from Book str_book")
    Page<Book> findAllWithEagerRelationships(Pageable pageable);

    @Query(value = "select distinct str_book from Book str_book left join fetch str_book.authors")
    List<Book> findAllWithEagerRelationships();

    @Query("select str_book from Book str_book left join fetch str_book.authors where str_book.id =:id")
    Optional<Book> findOneWithEagerRelationships(@Param("id") Long id);

}
