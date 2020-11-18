package com.bug1024.demo;

import lombok.extern.slf4j.Slf4j;
import org.springframework.core.task.TaskRejectedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * 全局异常处理
 *
 * @author wangyu024
 * @date 2020-05-08
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

	@ExceptionHandler({TaskRejectedException.class})
	public Object taskRejectedException(TaskRejectedException e) {
		log.error("线程池任务拒绝异常:", e);
		return "任务繁忙~";
	}

}
