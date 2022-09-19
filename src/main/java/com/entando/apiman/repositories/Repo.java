package com.entando.apiman.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.entando.apiman.entity.EntServiceDetail;

@Repository
public interface Repo extends JpaRepository<EntServiceDetail, Integer>
{
     
}
