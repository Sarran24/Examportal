package com.examportal.controller;

import com.examportal.model.exam.Result;
import com.examportal.service.exam.PdfGeneratorImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/download-pdf")
@CrossOrigin("*")
public class PdfController {
    private final Logger logger = LoggerFactory.getLogger(PdfController.class);

    @Autowired
    private PdfGeneratorImpl pdfGenerator;

    @GetMapping("/resultPdf/{id}")
    public ResponseEntity<InputStreamResource> generateResultPdf(@PathVariable Long id){
        logger.info("Generating result PDF...");
        ByteArrayInputStream pdf = pdfGenerator.generateResultPDF(id);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("ContentDesposition","inline;file=result.pdf");
        logger.info("Result PDF generated successfully. Returning the response.");
        return ResponseEntity.ok().headers(httpHeaders).contentType(MediaType.APPLICATION_PDF).body(new InputStreamResource(pdf));
    }

    @PostMapping("/save-result")
    public ResponseEntity<Result> saveResult(@RequestBody Result result){
        Result result1= pdfGenerator.saveResult(result);
        return ResponseEntity.ok(result1);
    }
}

