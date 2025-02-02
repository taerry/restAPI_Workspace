package com.gov.restapi.GovRestApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.gov.restapi.GovRestApi.entity.BulletinBoard;

public interface BulletinBoardRepository extends JpaRepository<BulletinBoard, Long>{

}
