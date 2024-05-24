package com.example.demo;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class HtmlController {
    @GetMapping("/")
    public ResponseEntity<String> showSearchForm() {
        String content = "<!DOCTYPE html>\n" +
                "<html>\n" +
                "<head>\n" +
                "  <title>Evening Sofa Expert</title>\n" +
                "</head>\n" +
                "<body>\n" +
                "\n" +
                "  <h3> Evening Sofa Expert</h3>\n" +
                "\n" +
                "  <form method=\"POST\" action=\"/search\">\n" +
                "    <label for=\"title\">Название фильма:</label><br>\n" +
                "    <input type=\"text\" id=\"title\" name=\"title\"><br><br>\n" + 
                "    <label for=\"id\">ID фильма:</label><br>\n" +
                "    <input type=\"text\" id=\"id\" name=\"id\"><br><br>\n" +
                "    <input type=\"submit\" value=\"Поиск\">\n" +
                "  </form>\n" +
                "\n" +
                "</body>\n" +
                "</html>";
        return ResponseEntity.ok(content);
    }
}
