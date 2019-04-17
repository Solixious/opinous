package com.opinous.repository.common;

import com.opinous.model.common.BaseEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.transaction.annotation.Transactional;

@NoRepositoryBean
public interface CustomPagingAndSortRepository<T extends BaseEntity, ID extends Long>
    extends PagingAndSortingRepository<T, ID> {

    @Override
    @Query("select e from #{#entityName} e where e.isActive = true")
    Iterable<T> findAll(Sort sort);

    @Override
    @Query("select e from #{#entityName} e where e.enabled = true")
    Page<T> findAll(Pageable pageable);

    @Query("select e from #{#entityName} e where e.id =?1 and e.enabled = true")
    T findOne(Long id);

    default boolean exists(Long id){
        return findOne(id) != null;
    }

    @Override
    @Query("select e from #{#entityName} e where e.enabled = true")
    Iterable<T> findAll();

    @Query("select e from #{#entityName} e where e.enabled = true")
    Iterable<T> findAll(Iterable<ID> iterable);

    @Override
    @Query("select count(e) from #{#entityName} e where e.enabled = true")
    long count();

    @Transactional
    @Modifying
    @Query("update #{#entityName} e set e.enabled=false where e.id=?1 and e.professor = ?#{principal.professor}")
    void delete(Long id);

    @Override
    @Transactional
    @Modifying
    default void delete(T t){
        delete(t.getId());
    }

    @Transactional
    @Modifying
    default void delete(Iterable<? extends T> iterable){
        iterable.forEach(entity -> delete(entity.getId()));
    }

    @Override
    @Transactional
    @Modifying
    @Query("update #{#entityName} e set e.enabled=false where e.professor = ?#{principal.professor}")
    void deleteAll();
}
