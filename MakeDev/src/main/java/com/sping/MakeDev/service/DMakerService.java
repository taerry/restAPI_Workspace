package com.sping.MakeDev.service;

import static com.sping.MakeDev.exception.DMakerErrorCode.DUPLICATED_MEMBER_ID;
import static com.sping.MakeDev.exception.DMakerErrorCode.NO_DEVELOPER;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.lang.NonNull;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.sping.MakeDev.code.StatusCode;
import com.sping.MakeDev.dto.CreateDeveloper;
import com.sping.MakeDev.dto.DeveloperDetailDto;
import com.sping.MakeDev.dto.DeveloperDto;
import com.sping.MakeDev.dto.EditDeveloper;
import com.sping.MakeDev.entity.Developer;
import com.sping.MakeDev.entity.RetiredDeveloper;
import com.sping.MakeDev.exception.DMakerException;
import com.sping.MakeDev.repository.DeveloperRepository;
import com.sping.MakeDev.repository.RetiredDeveloperRepository;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class DMakerService {
	
	private final DeveloperRepository developerRepository;
    private final RetiredDeveloperRepository retiredDeveloperRepository;

    @Transactional
    public CreateDeveloper.Response createDeveloper(
            CreateDeveloper.Request request
    ) {
        validateCreateDeveloperRequest(request);

        // business logic start
        return CreateDeveloper.Response.fromEntity(
                developerRepository.save(createDeveloperFromRequest(request))
        );
    }
    
    private Developer createDeveloperFromRequest(CreateDeveloper.Request request) {
        return Developer.builder()
                .developerLevel(request.getDeveloperLevel())
                .developerSkillType(request.getDeveloperSkillType())
                .experienceYears(request.getExperienceYears())
                .memberId(request.getMemberId())
                .statusCode(StatusCode.EMPLOYED)
                .name(request.getName())
                .age(request.getAge())
                .build();
    }

    private void validateCreateDeveloperRequest(
            @NonNull CreateDeveloper.Request request
    ) {
        // business validation
        request.getDeveloperLevel().validateExperienceYears(
                request.getExperienceYears()
        );

        developerRepository.findByMemberId(request.getMemberId())
                .ifPresent((developer -> {
                    throw new DMakerException(DUPLICATED_MEMBER_ID);
                }));
    }

    @Transactional(readOnly = true)
    public List<DeveloperDto> getAllEmployedDevelopers() {
        return developerRepository.findDevelopersByStatusCodeEquals(StatusCode.EMPLOYED)
        		.stream()
        		.map(DeveloperDto::fromEntity)
                .collect(Collectors.toList());
    }

    @Transactional(readOnly = true)
    public DeveloperDetailDto getDeveloperDetail(String memberId) {
        return DeveloperDetailDto.fromEntity(getDeveloperByMemberId(memberId));
    }

    private Developer getDeveloperByMemberId(String memberId) {
        return developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));
    }

    @Transactional
    public DeveloperDetailDto editDeveloper(
            String memberId, EditDeveloper.Request request
    ) {
        request.getDeveloperLevel().validateExperienceYears(
                request.getExperienceYears()
        );

        return DeveloperDetailDto.fromEntity(
                getUpdatedDeveloperFromRequest(
                        request, getDeveloperByMemberId(memberId)
                )
        );
    }

    private Developer getUpdatedDeveloperFromRequest(
            EditDeveloper.Request request, Developer developer
    ) {
        developer.setDeveloperLevel(request.getDeveloperLevel());
        developer.setDeveloperSkillType(request.getDeveloperSkillType());
        developer.setExperienceYears(request.getExperienceYears());

        return developer;
    }

    @Transactional
    public DeveloperDetailDto deleteDeveloper(String memberId) {
        // 1. EMPLOYED -> RETIRED
        Developer developer = developerRepository.findByMemberId(memberId)
                .orElseThrow(() -> new DMakerException(NO_DEVELOPER));
        developer.setStatusCode(StatusCode.RETIRED);

        // 2. save into RetiredDeveloper
        RetiredDeveloper retiredDeveloper = RetiredDeveloper.builder()
                .memberId(memberId)
                .name(developer.getName())
                .build();
        retiredDeveloperRepository.save(retiredDeveloper);
        return DeveloperDetailDto.fromEntity(developer);
    }

}
