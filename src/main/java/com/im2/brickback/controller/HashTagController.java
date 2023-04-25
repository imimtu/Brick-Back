package com.im2.brickback.controller;

import com.im2.brickback.controller.request.hashtag.HashTagCreateRequest;
import com.im2.brickback.controller.request.hashtag.HashTagModifyRequest;
import com.im2.brickback.controller.response.hashtag.HashTagResponse;
import com.im2.brickback.controller.response.Response;
import com.im2.brickback.domain.HashTag;
import com.im2.brickback.service.HashTagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@Tag(name="hashtag", description = "HashTag API")
@RestController
@RequestMapping("/api/v1/hashtags")
@RequiredArgsConstructor
public class HashTagController {

    private final HashTagService hashTagService;

    @Operation(summary = "HashTag 생성", description = "HashTag 를 신규로 생성한다.", tags = { "hashtag" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = HashTag.class))}) })
    @PostMapping()
    public Response<Void> create(@RequestBody HashTagCreateRequest request, Authentication authentication){
        hashTagService.create(request.getTitle(), request.getCount(), authentication.getName());
        return Response.success();
    }

    @Operation(summary = "HashTag 조회", description = "유저 명의로 된 HashTag 목록을 반환한다.", tags = { "hashtag" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = HashTag.class))}) })
    @GetMapping("/list")
    public Response<Page<HashTagResponse>> list(Pageable pageable, Authentication authentication){
        return Response.success(hashTagService.list(authentication.getName(), pageable).map(HashTagResponse::fromHashTag));
    }

    @Operation(summary = "HashTag 수정", description = "HashTag 를 수정한다.", tags = { "hashtag" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = HashTag.class))}) })
    @PutMapping("/{hashTagId}")
    public Response<Void> modify(@RequestBody HashTagModifyRequest request, Authentication authentication, @PathVariable Long hashTagId){
        hashTagService.modify(request.getTitle(), request.getCount(), authentication.getName(), hashTagId );
        return Response.success();
    }

    @Operation(summary = "HashTag 삭제", description = "HashTag 를 삭제한다.", tags = { "hashtag" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = HashTag.class))}) })
    @DeleteMapping("/{hashTagId}")
    public Response<Void> delete(Authentication authentication, @PathVariable Long hashTagId){
        hashTagService.delete(authentication.getName(), hashTagId);
        return Response.success();
    }
    
}
