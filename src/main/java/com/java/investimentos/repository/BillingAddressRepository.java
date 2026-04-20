package com.java.investimentos.repository;


import com.java.investimentos.entity.BillingAddress;
import com.java.investimentos.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;


//implementa com o spring.data.jpa a implementação de ir no banco e fazer o insert da entity
@Repository
public interface BillingAddressRepository extends JpaRepository<BillingAddress, UUID> {



}
