package com.gov.restapi.GovRestApi.service;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.gov.restapi.GovRestApi.entity.BulletinBoard;
import com.gov.restapi.GovRestApi.repository.BulletinBoardRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class BulletinBoardService {

	private final BulletinBoardRepository bbRepository;
	
	public BulletinBoard save(BulletinBoard bb){
		return bbRepository.save(bb);
	}
	
	public List<BulletinBoard> getAllBulletinBoards(){
		return bbRepository.findAll();
	}
	
	public Optional<BulletinBoard> findByboardNo(Long boardNo){
		return bbRepository.findById(boardNo);
	}
	
	public void deleteBulletinBoard(BulletinBoard bb){
		bbRepository.delete(bb);
	}
}
