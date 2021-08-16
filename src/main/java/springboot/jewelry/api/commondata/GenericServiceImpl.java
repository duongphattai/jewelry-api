package springboot.jewelry.api.commondata;

import org.springframework.beans.factory.annotation.Autowired;
import springboot.jewelry.api.commondata.model.AbstractEntity;

import java.util.List;
import java.util.Optional;

public class GenericServiceImpl <T extends AbstractEntity, ID> implements GenericService<T, ID> {

    @Autowired(required = false)
    private GenericRepository<T, ID> genericRepository;

    @Override
    public List<T> findAll() {
        return genericRepository.findAll();
    }

    @Override
    public Optional<T> findById(ID id) {
        return genericRepository.findById(id);
    }

    @Override
    public T save(T entity) {
        return genericRepository.save(entity);
    }

    @Override
    public T update(T entity) {
        return genericRepository.save(entity);
    }

    @Override
    public void deleteById(ID id) {
        genericRepository.deleteById(id);
    }

    @Override
    public <C> List<C> findAllBy(Class<C> type) {
        return genericRepository.findAllBy(type);
    }
}
