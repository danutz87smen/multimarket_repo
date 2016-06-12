package com.dan.controllers;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class FileController {
	//todo put in conf file
	private String imgDir="d://dan//WS//tmp//img//";
	
	@RequestMapping(value = "/uploadFiles/", method = RequestMethod.POST)
	public ResponseEntity<String> UploadFile(HttpServletRequest request, HttpServletResponse response,
			@RequestParam("prodId") Integer prodId) throws IOException, ServletException {
		List<Part> fileParts = request.getParts().stream().filter(part -> part.getName().startsWith("uploaded_file_"))
				.collect(Collectors.toList()); 
												
		File newFile = null;
		for (Part filePart : fileParts) {
			String fileName = filePart.getSubmittedFileName();
			newFile = new File(imgDir + prodId + "//" + fileName);
			newFile.getParentFile().mkdirs();
			try (BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(newFile))) {
				FileCopyUtils.copy(filePart.getInputStream(), stream);
			} catch (Exception e) {
				e.printStackTrace();
				return new ResponseEntity<String>(HttpStatus.BAD_REQUEST);
			}
		}
		return new ResponseEntity<String>(HttpStatus.CREATED);
	}
}
