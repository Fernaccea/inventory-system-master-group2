package com.inventory_system.repositories;

import com.inventory_system.model.Item;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.math.BigDecimal;
import java.util.List;

public interface ItemRepository extends JpaRepository<Item, Long> {

  List<Item> findByPriceGreaterThanEqualAndPriceLessThan(BigDecimal priceMin, BigDecimal priceMax);

  List<Item> findTop5ByOrderByIdDesc();

  List<Item> findTop1ByOrderByIdDesc();

  List<Item> findByCategoryEquals(String category);

  List<Item> findByNameEquals(String name);

  @Query("SELECT category FROM Item WHERE id = ( select min(id) from Item)")
  String findFirstCategory();

  @Query("SELECT name FROM Item WHERE id = ( select min(id) from Item)")
  String findFirstName();


  @Query("SELECT SUM(quantity) FROM Item")
  int getTotalQuantity();

  @Query("SELECT DISTINCT category FROM Item")
  List<String> getAllCategories();

  @Query("SELECT DISTINCT name FROM Item")
  List<String> getAllNames();

  List<String> findByIdEquals(Long id);

  @Query("SELECT SUM(price*quantity) FROM Item")
  BigDecimal getTotalPrice();
}
