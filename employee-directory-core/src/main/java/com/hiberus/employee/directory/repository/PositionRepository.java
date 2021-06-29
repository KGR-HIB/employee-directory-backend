package com.hiberus.employee.directory.repository;

import static com.hiberus.employee.directory.entity.QPositionEntity.positionEntity;

import java.util.List;
import com.hiberus.employee.directory.entity.PositionEntity;
import com.hiberus.employee.directory.repository.common.JPAQueryDslBaseRepository;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Repository;

/**
 * Position repository implements
 *
 * @author bcueva
 * @version 1.0
 */
@Lazy
@Repository
public class PositionRepository extends JPAQueryDslBaseRepository<PositionEntity> implements IPositionRepository {

    /**
     * Creates a new {@link QuerydslRepositorySupport} instance for the given domain type.
     */
    public PositionRepository() {
        super(PositionEntity.class);
    }

    @Override
    public List<PositionEntity> findAll() {
        return from(positionEntity)
            .where(positionEntity.status.eq(Boolean.TRUE))
            .fetch();
    }
}
