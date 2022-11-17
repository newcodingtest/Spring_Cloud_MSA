package com.yoon.catalogservice.controller;

import com.yoon.catalogservice.entity.CatalogEntity;
import com.yoon.catalogservice.service.CatalogService;
import com.yoon.catalogservice.vo.ResponseCatalog;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
@Api(tags = {" 상품정보를 관리하는 Controller"})
@RestController
@RequiredArgsConstructor
@RequestMapping("/catalog-service")
public class CatalogController {

    private final Environment env;
    private final CatalogService catalogService;

    @GetMapping("/health_check")
    public String status(){
        return String.format("It's working in catalog-service on Port %s",env.getProperty("local.server.port"));
    }

    @ApiOperation(value = "상품들 조회")
    @GetMapping("/catalogs")
    public ResponseEntity<List<ResponseCatalog>> getCatalogs(){

        List<ResponseCatalog> catalogs = catalogService.getAllCatalogs();

        return ResponseEntity.status(HttpStatus.OK).body(catalogs);
    }

}
