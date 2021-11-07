package com.ptit.similarityfile.controller;

import com.ptit.similarityfile.service.FileToString;
import com.ptit.similarityfile.service.GetAstSimilarity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;
@CrossOrigin("*")
@RequestMapping("file")
@RestController
public class FileContronller {
    @GetMapping
    public String ping(){
        return "pong";
    }
    @PostMapping("tree")
    public String compare(@RequestBody  MultipartFile sfile,
                          @RequestBody MultipartFile tfile) {
        try {
            String a = getFileToString(sfile);
            String b = getFileToString(tfile);
            GetAstSimilarity gas = new GetAstSimilarity();
            gas.SimilarityCalculation(a, Arrays.asList(b));
            List<Double> simList = gas.getSimList();
            return simList.get(0).toString();
        } catch (Exception exception) {
            exception.printStackTrace();
            return "Có lỗi xảy ra";
        }
    }

    public String getFileToString(MultipartFile file) throws IOException {
        BufferedReader input = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
        StringBuffer buffer = new StringBuffer();
        String text;
        while ((text = input.readLine()) != null)
            buffer.append(text + "\n");

        return buffer.toString();
    }
}
