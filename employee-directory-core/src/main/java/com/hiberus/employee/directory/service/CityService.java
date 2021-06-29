package com.hiberus.employee.directory.service;

import javax.transaction.Transactional;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.repository.ICityRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Transactional
@Validated
@Lazy
@Service
public class CityService extends BaseService<CityEntity, ICityRepository> implements ICityService {

    public CityService(ICityRepository repository) {
        super(repository);
    }
}
