package com.example.githubcicd;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/api/word")
public class WordController {

    @PostMapping("/extract-text")
    public ResponseEntity<String> extractTextFromShapes(@RequestParam("file") MultipartFile file) {
        StringBuilder extractedText = new StringBuilder();

        String patternString = "\\{\\!\\![^\\}]+\\}";
        try (XWPFDocument document = new XWPFDocument(file.getInputStream())) {
            // Iterate over all paragraphs
            List<XWPFParagraph> paragraphs = document.getParagraphs();
            for (XWPFParagraph paragraph : paragraphs) {
                List<XWPFRun> runs = paragraph.getRuns();
                for (XWPFRun run : runs) {
                    // Check if the run contains text

                    final List<String> strings = extractValues(run.getCTR().xmlText());
                    if (!CollectionUtils.isEmpty(strings)){
                        extractedText.append(strings).append("\n");
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            return new ResponseEntity<>("Error processing file", HttpStatus.INTERNAL_SERVER_ERROR);
        }

        return new ResponseEntity<>(extractedText.toString(), HttpStatus.OK);
    }

    public static List<String> extractValues(String input) {
        String patternString = "\\{!![^}]+}";

        Pattern pattern = Pattern.compile(patternString);

        Matcher matcher = pattern.matcher(input);

        List<String> matches = new ArrayList<>();

        while (matcher.find()) {
            matches.add(matcher.group());
        }

        return matches;
    }
}
