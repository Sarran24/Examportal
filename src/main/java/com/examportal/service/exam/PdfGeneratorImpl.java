package com.examportal.service.exam;

import com.examportal.model.exam.Result;
import com.examportal.repository.exam.ResultRepository;
import com.lowagie.text.*;
import com.lowagie.text.Font;
import com.lowagie.text.pdf.ColumnText;
import com.lowagie.text.pdf.PdfContentByte;
import com.lowagie.text.pdf.PdfPageEventHelper;
import com.lowagie.text.pdf.PdfWriter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.awt.*;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.util.Optional;

@Service
@CrossOrigin("*")
public class PdfGeneratorImpl implements PdfGenerator {

    @Autowired
    private ResultRepository resultRepository;


    private final Logger logger = LoggerFactory.getLogger(PdfGeneratorImpl.class);

    @Override
    public ByteArrayInputStream generateResultPDF(Long id) {
        Optional<Result> result = resultRepository.findById(id);
        logger.info("Generating Result PDF");
        String title = "Result of: "+result.get().getUserid();
        double marksGot = result.get().getMarksGot();
        int attempted = result.get().getAttempted();
        int correctMarks = result.get().getCorrectMarks();
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        try (Document document = new Document()) {
            PdfWriter writer = PdfWriter.getInstance(document, outputStream);
            writer.setPageEvent(new PDFPageEvent());
            document.open();
            Font fontTitle = FontFactory.getFont(FontFactory.HELVETICA, 20);
            Paragraph titleParagraph = new Paragraph(title, fontTitle);
            titleParagraph.setAlignment(Element.ALIGN_CENTER);

            Font fontCommon = FontFactory.getFont(FontFactory.HELVETICA, 15);
            Paragraph marksParagraph = new Paragraph("Marks: " + marksGot, fontCommon);
            marksParagraph.setAlignment(Element.ALIGN_CENTER);

            Paragraph attemptParagraph = new Paragraph("Attempted: " + attempted, fontCommon);
            attemptParagraph.setAlignment(Element.ALIGN_CENTER);

            Paragraph correctMarksParagraph = new Paragraph("Correct Marks: " + correctMarks, fontCommon);
            correctMarksParagraph.setAlignment(Element.ALIGN_CENTER);

            document.add(titleParagraph);
            document.add(new Paragraph("\n"));
            document.add(marksParagraph);
            document.add(new Paragraph("\n"));
            document.add(attemptParagraph);
            document.add(new Paragraph("\n"));
            document.add(correctMarksParagraph);

        } catch (DocumentException e) {
            logger.error("DocumentException occurred while generating result PDF: {}", e.getMessage());
        }
        logger.info("Result PDF generated successfully.");
        return new ByteArrayInputStream(outputStream.toByteArray());
    }

    @Override
    public Result saveResult(Result result) {
        return resultRepository.save(result);
    }

    private class PDFPageEvent extends PdfPageEventHelper {
        public void onEndPage(PdfWriter writer, Document document) {
            PdfContentByte cb = writer.getDirectContent();
            cb.setColorStroke(Color.BLUE);
            cb.rectangle(20, 20, document.getPageSize().getWidth() - 40, document.getPageSize().getHeight() - 40);
            cb.stroke();
            ColumnText.showTextAligned(cb, Element.ALIGN_CENTER, new Phrase("Examportal By Sarran"), (document.right() - document.left()) / 2 + document.leftMargin(), document.bottom() - 10, 0);
        }
    }
}