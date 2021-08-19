package springboot.jewelry.api.commondata;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.NoRepositoryBean;
import springboot.jewelry.api.commondata.model.AbstractEntity;

import java.util.List;

@NoRepositoryBean
public interface GenericRepository<T extends AbstractEntity, ID> extends JpaRepository<T, ID> {

    <P> List<P> findAllBy(Class<P> type);
}
