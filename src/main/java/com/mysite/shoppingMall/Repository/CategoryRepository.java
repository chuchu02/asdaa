package com.mysite.shoppingMall.Repository;

import com.mysite.shoppingMall.Vo.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
