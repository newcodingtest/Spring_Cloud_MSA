package com.yoon.catalogservice.service;

import com.yoon.catalogservice.dto.CatalogDto;
import com.yoon.catalogservice.entity.CatalogEntity;
import com.yoon.catalogservice.repository.CatalogRepository;
import com.yoon.catalogservice.vo.ResponseCatalog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class CatalogServiceImpl implements CatalogService{

    private final CatalogRepository catalogRepository;

    @Override
    public List<ResponseCatalog> getAllCatalogs() {
        List<ResponseCatalog> catalogs = new ArrayList<>();

        List<CatalogEntity> catalogEntity = catalogRepository.findAll();
        catalogEntity.forEach( x -> {
            catalogs.add(new ModelMapper().map(x, ResponseCatalog.class));
        });

        return catalogs;
    }
}
