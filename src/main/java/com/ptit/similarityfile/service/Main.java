package com.ptit.similarityfile.service;



import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Main {
	public static String  pathInput="C:\\Users\\Admin 88\\Downloads\\Data_1";
	public static String  pathOutput="C:\\Users\\Admin 88\\Downloads\\__MACOSX";

	public static void main(String[] args) throws FileNotFoundException {

		File file=new File(pathInput);
		if(!file.isDirectory()){
			System.out.println("Nhập sai thư mục");
			return;
		}
		File fileOut=new File(pathOutput+"\\"+"output.csv");
		PrintWriter printWriter=new PrintWriter(fileOut);
		printWriter.println("STT, Source File , Compare File, Simillarity");
		int dem=0;
		String[] children = file.list();
		for (int i = 0; i < children.length-1; i++) {
			for (int j = i+1; j < children.length; j++) {
				String file1=file.getAbsolutePath()+"\\"+children[i];
				String file2=file.getAbsolutePath()+"\\"+children[j];
				String result=comapreFile(file1,file2);
				printWriter.println(dem+","+children[i]+","+children[j]+","+result);
				dem++;
			}
		}
		System.out.println("Xử lý thành công");
		printWriter.close();
	}
	private static String comapreFile(String file1,String file2){
		String fileA = FileToString.readFile(file1);
		String fileB = FileToString.readFile(file2);
		GetAstSimilarity gas = new GetAstSimilarity();
		gas.SimilarityCalculation(fileA, Arrays.asList(fileB));
		List<Double> simList = gas.getSimList();
		return simList.get(0).toString();
	}

}
