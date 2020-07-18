package am.davsoft.sfl_assessment.core.service;

import am.davsoft.sfl_assessment.core.entity.BaseEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author David Shahbazyan
 * @since Jul 18, 2020
 */
public class BaseCrudService<T extends BaseEntity> {
    private final JpaRepository<T, Integer> repository;

    public BaseCrudService(JpaRepository<T, Integer> repository) {
        this.repository = repository;
    }

    public List<T> loadAll() {
        return repository.findAll();
    }

    public T loadById(Integer entityId) {
        return repository.getOne(entityId);
    }

    public T save(T entity) {
        return repository.save(entity);
    }

    public void delete(T entity) {
        repository.delete(entity);
    }

    public void delete(Integer entityId) {
        repository.deleteById(entityId);
    }
}
