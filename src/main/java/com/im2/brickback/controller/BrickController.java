package com.im2.brickback.controller;


import com.im2.brickback.controller.request.brick.BrickCreateRequest;
import com.im2.brickback.controller.request.brick.BrickModifyRequest;
import com.im2.brickback.controller.response.brick.BrickResponse;
import com.im2.brickback.controller.response.Response;
import com.im2.brickback.domain.Brick;
import com.im2.brickback.service.BrickService;
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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Tag(name="brick", description = "Brick(ToDo) API")
@RestController
@RequestMapping("/api/v1/bricks")
@RequiredArgsConstructor
public class BrickController {

    private final BrickService brickService;

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

    @Operation(summary = "Brick 생성", description = "Brick 을 신규로 생성한다.", tags = { "brick" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Brick.class))}) })
    @PostMapping
    public Response<Void> create(@RequestBody BrickCreateRequest request, Authentication authentication){
        // TODO : authentication 으로 가져오는 username 은 중복발생 가능한거 아닌가? -> userId로 가져오는 방법은 없나?
        brickService.create(request.getTitle(), request.getContent(), request.getPriority(), LocalDateTime.parse(request.getDeadline(),dateTimeFormatter), request.getHashtag(), authentication.getName());
        return Response.success();
    }

    // TODO : 일정 조회하는 방법을 어떻게?
    //  1) 오늘 일정 조회
    //  2) 월별 일정 조회
    //  3) 전체 일정 조회
//    @Operation(summary = "일정 조회(단건)", description = "일정 조회(단건)", tags = { "brick" })
//    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Brick.class))}) })
//    @PostMapping("/{postId}")
//    public Response<BrickResponse> one(@RequestBody Brick request, Authentication authentication){
//        return Response.success();
//    }

    @Operation(summary = "Brick 전체 리스트 조회", description = "해당 유저의 모든 Brick 리스트를 조회한다.", tags = { "brick" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Brick.class))}) })
    @GetMapping("/list")
    public Response<Page<BrickResponse>> list(Pageable pageable, Authentication authentication){
        return Response.success(brickService.list(authentication.getName(), pageable).map(BrickResponse::fromBrick));
    }

    @Operation(summary = "일정 수정", description = "일정 수정", tags = { "brick" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Brick.class))}) })
    @PutMapping("/{brickId}")
    public Response<Void> modify(@RequestBody BrickModifyRequest request, Authentication authentication, @PathVariable Long brickId){
        brickService.modify(request.getTitle(), request.getContent(), request.getPriority(),LocalDateTime.parse(request.getDeadline(),dateTimeFormatter), request.getHashtag(), authentication.getName(), brickId);
        return Response.success();
    }

    @Operation(summary = "일정 삭제", description = "일정 삭제", tags = { "brick" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Brick.class))}) })
    @DeleteMapping("/{brickId}")
    public Response<Void> delete(Authentication authentication, @PathVariable Long brickId){
        brickService.delete(authentication.getName(), brickId);
        return Response.success();
    }

}

