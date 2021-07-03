package com.hiberus.employee.directory.service;

import java.util.List;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import com.hiberus.employee.directory.entity.PositionEntity;
import com.hiberus.employee.directory.repository.IPositionRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Position;

/**
 * Position service implementation
 *
 * @author bcueva
 * @version 1.0
 */
@Validated
@Lazy
@Service
public class PositionService extends BaseService<PositionEntity, IPositionRepository> implements IPositionService {

    public PositionService(IPositionRepository repository) {
        super(repository);
    }

    /**
     * {@inheritDoc}
     */
    @Transactional(readOnly = true)
    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }
}
