package com.sparta.gathering.domain.user.controller;

import com.sparta.gathering.common.response.ApiResponse;
import com.sparta.gathering.common.response.ApiResponseEnum;
import com.sparta.gathering.domain.user.dto.response.UserDTO;
import com.sparta.gathering.domain.user.service.UserProfileService;
import java.io.IOException;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/users/{userId}/profile-image")
@RequiredArgsConstructor
public class UserProfileController {

    private final UserProfileService userProfileService;


    // 이미지 수정
    @PutMapping
    public ResponseEntity<ApiResponse<?>> updateProfileImage(
            @AuthenticationPrincipal UserDTO userDto,
            @PathVariable("userId") UUID userId,
            @RequestParam("file") MultipartFile newImage
    ) throws IOException {
        String res = userProfileService.updateProfileImage(userDto, userId, newImage);
        return ResponseEntity.ok(
                ApiResponse.successWithData(res, ApiResponseEnum.USER_PROFILE_GET_SUCCESS));
    }

    // 이미지 조회
    @GetMapping
    public ResponseEntity<ApiResponse<?>> getUserProfileImages(
            @PathVariable("userId") UUID userId
    ) {
        String res = userProfileService.getUserProfileImages(userId);
        return ResponseEntity.ok(
                ApiResponse.successWithData(res, ApiResponseEnum.USER_PROFILE_GET_SUCCESS));
    }

    // 이미지 삭제
    @DeleteMapping()
    public ResponseEntity<ApiResponse<?>> deleteUserProfileImage(
            @AuthenticationPrincipal UserDTO userDto,
            @PathVariable("userId") UUID userId
    ) {
        userProfileService.deleteUserProfileImage(userDto, userId);
        return ResponseEntity.ok(
                ApiResponse.successWithOutData(ApiResponseEnum.USER_PROFILE_DELETE_SUCCESS));
    }
}
