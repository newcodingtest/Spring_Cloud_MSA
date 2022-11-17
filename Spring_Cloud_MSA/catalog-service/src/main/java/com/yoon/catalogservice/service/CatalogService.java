package com.yoon.catalogservice.service;

import com.yoon.catalogservice.dto.CatalogDto;
import com.yoon.catalogservice.entity.CatalogEntity;
import com.yoon.catalogservice.vo.ResponseCatalog;

import java.util.List;

public interface CatalogService {
    List<ResponseCatalog> getAllCatalogs();
}
