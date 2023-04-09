package com.im2.brickback.controller;


import com.im2.brickback.controller.request.BrickCreateRequest;
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

    @Operation(summary = "일정 생성", description = "일정 생성", tags = { "brick" })
    @ApiResponses(value = { @ApiResponse(description = "successful operation", content = { @Content(mediaType = "application/json", schema = @Schema(implementation = Brick.class))}) })
    @PostMapping
    public Response<Void> create(@RequestBody BrickCreateRequest request, Authentication authentication){
        // TODO : authentication 으로 가져오는 username 은 중복발생 가능한거 아닌가? -> userId로 가져오는 방법은 없나?
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        brickService.create(request.getTitle(), request.getContent(), request.getPriority(), LocalDateTime.parse(request.getDeadline(),dateTimeFormatter), request.getHashtag(), authentication.getName());
        return Response.success();
    }

}
