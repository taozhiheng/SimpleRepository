package service;

import org.json.JSONArray;
import org.json.JSONObject;
import java.util.HashMap;
import java.util.List;
import data.Book;
import data.Chapter;
import util.TimeUtil;

/**
 * Created by taozhiheng on 15-7-23.
 * 与服务器进行通信，上传数据或下载数据
 */
public class BookUtil {


    public static JSONObject getOnlyBookJson(Book book)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("isbn", (book.getIsbn()==null)?"null":book.getIsbn());
        map.put("title", book.getName());
        map.put("creator", (book.getAuthor()==null)?"null":book.getAuthor());
        map.put("publisher", (book.getPress()==null)?"null":book.getPress());
        map.put("cover", (book.getUrl()==null)?"null":book.getUrl());
        map.put("color", String.valueOf(book.getColor()));
        map.put("words", book.getWordNum());
        return new JSONObject(map);
    }

    public static JSONObject getBookJson(Book book, List<Chapter> chapterList)
    {
        HashMap<String, Object> map = new HashMap<>();
        map.put("isbn", (book.getIsbn()==null)?"null":book.getIsbn());
        map.put("title", book.getName());
        map.put("creator", (book.getAuthor()==null)?"null":book.getAuthor());
        map.put("publisher", (book.getPress()==null)?"null":book.getPress());
        map.put("cover", (book.getUrl()==null)?"null":book.getUrl());
        map.put("color", String.valueOf(book.getColor()));
        map.put("words", book.getWordNum());
        map.put("add_time", TimeUtil.getNeedTime(System.currentTimeMillis()));
        if(chapterList == null)
            return new JSONObject(map);
        JSONArray chapters = new JSONArray();
        HashMap<String, String> chapterMap = new HashMap<>();
        for(Chapter chapter : chapterList)
        {
            if(chapter.getName().compareTo("") == 0)
                continue;
            chapterMap.put("name", chapter.getName());
            chapters.put(new JSONObject(chapterMap));
        }

        map.put("chapters", chapters);
        return new JSONObject(map);
    }
}
