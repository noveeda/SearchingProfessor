package com.delp.searchingprofessor;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.Base64;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Parser{

    private String url = "https://www.kunsan.ac.kr/index.kunsan?menuCd=DOM_000000107003000000&sso=ok";

    public ArrayList<HashMap> SearchTerm(String searchTerm){
        try{
            Document doc = Jsoup.connect(url)
                    .data("collection", "office")
                    .data("searchTerm", searchTerm)
                    .data("realSearchKeyword", searchTerm)
                    .data("currentPage", "1")
                    .post();

            ArrayList<HashMap> list = ParseData(doc);
            return list;
        }catch (Exception e){
            e.printStackTrace();
            System.out.println("검색어가 잘못되었거나, 페이지 수를 초과하였습니다. 페이지 수를 다시 입력해주세요.");
        }
        return null;
    }

    public ArrayList<HashMap> SearchTerm(String searchTerm, int page){
        try{
            Document doc = Jsoup.connect(url)
                    .data("collection", "office")
                    .data("searchTerm", searchTerm)
                    .data("realSearchKeyword", searchTerm)
                    .data("currentPage", Integer.toString(page))
                    .post();

            ArrayList<HashMap> list = ParseData(doc);
            return list;
        }
        catch(Exception e){
            e.printStackTrace();
            System.out.println("검색어가 잘못되었거나, 페이지 수를 초과하였습니다. 페이지 수를 다시 입력해주세요.");
        }
        return null;
    }

    private ArrayList<HashMap> ParseData(Document doc) {
        Elements elements = doc.select("div.cscroll_768 tbody > tr");

        ArrayList<HashMap> list = new ArrayList<HashMap>();

        for(Element e : elements){
            LinkedHashMap<String, String> map = new LinkedHashMap<String, String>();
            map.put("부서", e.select("td")
                    .get(0)
                    .text()
                    .split(" > ")[e.select("td").get(0).text().split(">").length-1]);
            map.put("직위", e.select("td").get(1).text());
            map.put("성명", e.select("td").get(2).text());
            map.put("담당업무", e.select("td").get(3).text());
            map.put("전화번호", e.select("td").get(4).text());
            list.add(map);
        }

        return list;
        /*for(int i = 0; i < list.size(); i++){
            System.out.println(list.get(i));
        }*/
    }
}