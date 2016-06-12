package com.dan.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
public class FileController {
	@RequestMapping(value = "/uploadFiles/", method = RequestMethod.POST)
	public ResponseEntity<String> UploadFile(HttpServletResponse response, @RequestParam("file") MultipartFile file,
			@RequestParam("prodId") Integer prodId) {

//		if (!file.isEmpty()) {
			File newFile = new File("d://dan//WS//tmp//img//" + prodId + "//" + file.getOriginalFilename());
			newFile.getParentFile().mkdirs();
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile))) {
				FileCopyUtils.copy(file.getInputStream(), stream);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(HttpStatus.BAD_GATEWAY);
			}
//		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
}
