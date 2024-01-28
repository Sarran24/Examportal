package com.examportal.service.exam;

import com.examportal.model.exam.Result;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;

@Service
public interface PdfGenerator {

   ByteArrayInputStream generateResultPDF(Long id);

   Result saveResult(Result result);
}
