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

import java.util.List;

@NoRepositoryBean
public interface CustomPagingAndSortRepository<T extends BaseEntity, ID extends Long>
    extends PagingAndSortingRepository<T, ID> {

    @Override
    @Query("select e from #{#entityName} e where e.isActive = 1")
    List<T> findAll(Sort sort);

    @Override
    @Query("select e from #{#entityName} e where e.isActive = 1")
    Page<T> findAll(Pageable pageable);

    @Query("select e from #{#entityName} e where e.id =?1 and e.isActive = 1")
    T findOne(Long id);

    default boolean exists(Long id){
        return findOne(id) != null;
    }

    @Override
    @Query("select e from #{#entityName} e where e.isActive = 1")
    List<T> findAll();

    @Query("select e from #{#entityName} e where e.isActive = 1")
    Iterable<T> findAll(Iterable<ID> iterable);

    @Override
    @Query("select count(e) from #{#entityName} e where e.isActive = 1")
    long count();

    @Transactional
    @Modifying
    @Query("update #{#entityName} e set e.isActive=0 where e.id=?1")
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
    @Query("update #{#entityName} e set e.isActive=0")
    void deleteAll();
}
