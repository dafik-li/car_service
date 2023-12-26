package com.solvd.carservice.service.impl;

import com.solvd.carservice.domain.Detail;
import com.solvd.carservice.persistence.DetailRepository;
import com.solvd.carservice.persistence.impl.DetailRepositoryImpl;
import com.solvd.carservice.service.DetailService;
import java.util.List;
import java.util.Optional;

public class DetailServiceImpl implements DetailService {
    private final DetailRepository detailRepository;

    public DetailServiceImpl() {
        this.detailRepository = new DetailRepositoryImpl();
    }
    @Override
    public Detail add(Detail detail) {
        detail.setId(null);
        detailRepository.create(detail);
        return detail;
    }
    @Override
    public List<Detail> retrieveAll() {
        return detailRepository.getAll();
    }
    @Override
    public Optional<Detail> retrieveById(Long id) {
        return detailRepository.getById(id);
    }
    @Override
    public void change(Detail detail, String field) {
        detailRepository.update(detail, field);
    }
    @Override
    public void removeById(Long id) {
        detailRepository.deleteById(id);
    }
}
