package kr.or.ddit.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.slf4j.Slf4j;

@RequestMapping("/security")
@Slf4j
@Controller
public class SecurityController {

	/*
	 1. 회원 게시판
	 	1)목록 : 모두 접근 가능(/board/list)
	 	2)등록 : 로그인한 회원만 접근 가능(/board/register)
	 2. 공지사항 게시판
	 	1)목록 : 모두 접근 가능(/notice/list)
	 	2)등록 : 로그인한 관리자만 접근 가능(/notice/register)
	 */
	
}
