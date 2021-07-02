package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.PositionEntity;
import com.hiberus.employee.directory.repository.IPositionRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.Position;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

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

    @Override
    public List<Position> findAll() {
        return repository.findAll();
    }
}
