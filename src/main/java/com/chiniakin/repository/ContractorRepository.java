package com.chiniakin.repository;

import com.chiniakin.entity.Contractor;
import com.chiniakin.exception.ContractorNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

/**
 * Репозиторий для работы с контрагентами.
 *
 * @author ChiniakinD.
 */
public interface ContractorRepository extends JpaRepository<Contractor, String> {

    /**
     * Получает всех контрагентов согласно спецификации с пагинацией.
     *
     * @param spec     спецификация.
     * @param pageable информация о пагинации.
     * @return страница контрагентов, подходящим по требованиям.
     */
    Page<Contractor> findAll(Specification<Contractor> spec, Pageable pageable);

    /**
     * @param pageable информация о пагинации.
     * @return страница всех активных контрагентов.
     */
    @Query("select c from Contractor c where c.isActive = true")
    Page<Contractor> findAllContractors(Pageable pageable);

    /**
     * Получает контрагента по id, с информацией о стране, отрасли и  организационной формы.
     *
     * @param id идентификатор контрагента.
     * @return контрагент.
     */
    @Query("select c from Contractor c join fetch c.country join fetch c.industry join fetch c.orgForm where c.id = :id")
    Optional<Contractor> findByIdWithDetails(@Param("id") String id);

    /**
     * Поулчает контрагента по id, с информацией о стране, отрасли и  организационной формы.
     *
     * @param id идентификатор контрагента.
     * @return контрагент.
     * @throws ContractorNotFoundException, если контрагент не найден.
     */
    default Contractor findByIdWithDetailsOrThrow(String id) {
        return findByIdWithDetails(id)
                .orElseThrow(() -> new ContractorNotFoundException("Контрагент с id " + id + " не найден."));
    }

    /**
     * Поулчает контрагента по id.
     *
     * @param id идентификатор контрагента.
     * @return контрагент.
     * @throws ContractorNotFoundException, если контрагент не найден.
     */
    default Contractor findByIdOrThrow(String id) {
        return findById(id).orElseThrow(() -> new ContractorNotFoundException("Контрагент с id " + id + " не найден."));
    }

}
