package org.chiniakin.repository;

import org.chiniakin.entity.Industry;
import org.chiniakin.exception.IndustryNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Репозиторий для работы с отраслями.
 *
 * @author ChiniakinD.
 */
public interface IndustryRepository extends JpaRepository<Industry, Long> {

    /**
     * Получает отрасль по id.
     *
     * @param id идентификатор отрасли.
     * @return отрасль.
     * @throws IndustryNotFoundException, если отрасль не найдена.
     */
    default Industry findByIdOrThrow(Long id) {
        return findById(id).orElseThrow(() -> new IndustryNotFoundException("Отрасль с id " + id + " не найдена."));
    }

}
