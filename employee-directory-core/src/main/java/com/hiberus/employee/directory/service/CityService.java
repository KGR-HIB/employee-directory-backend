package com.hiberus.employee.directory.service;

import java.util.List;
import com.hiberus.employee.directory.entity.CityEntity;
import com.hiberus.employee.directory.repository.ICityRepository;
import com.hiberus.employee.directory.service.common.BaseService;
import com.hiberus.employee.directory.vo.City;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Lazy
@Service
public class CityService extends BaseService<CityEntity, ICityRepository> implements ICityService {

    public CityService(ICityRepository repository) {
        super(repository);
    }

    @Override
    public List<City> findAll() {
        return repository.findAll();
    }
}
