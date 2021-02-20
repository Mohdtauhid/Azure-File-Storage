package com.craterzone.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.craterzone.service.FileService;

@RestController
@RequestMapping("/")
public class FileController {

	@Autowired
	private FileService fileservice;

	@PostMapping("/container")
	public ResponseEntity createContainer(@RequestBody String containerName) {
		boolean created = fileservice.createContainer(containerName);
		return ResponseEntity.ok(created);
	}

	@PostMapping
	public ResponseEntity upload(@RequestParam(value = "File") MultipartFile File) {
		URI url = fileservice.upload(File);
		return ResponseEntity.ok(url);
	}

	@GetMapping("/blobs")
	public ResponseEntity getAllBlobs(@RequestParam(value = "containerName") String containerName) {
		List<URI> uris = fileservice.listBlobs(containerName);
		return ResponseEntity.ok(uris);
	}

	@DeleteMapping
	public ResponseEntity delete(@RequestParam String containerName, @RequestParam String blobName) {
		fileservice.deleteBlob(containerName, blobName);
		return ResponseEntity.ok().body("Deleted successfully");
	}

}