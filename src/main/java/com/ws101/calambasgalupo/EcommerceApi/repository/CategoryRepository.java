package com.ws101.calambasgalupo.EcommerceApi.repository;

import com.ws101.calambasgalupo.EcommerceApi.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Long> {
}
