package com.example.demo;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;

@RestController
public class MovieController {

    private String apiKey = "7ebabc4f";
    String res = "<!DOCTYPE html>\n" +
    "<html>\n" +
    "<body>\n" +
    "  <form action=\"/\">\n" + 
    "    <input type=\"submit\" value=\"Назад на главную страницу\">\n" +
    "  </form>\n" +
    "</body>\n" +
    "</html>";

    private final RestTemplate restTemplate = new RestTemplate();

    @PostMapping("/search")
    public ResponseEntity<String> searchMovies(@RequestParam(value = "title", required = false) String title, @RequestParam(value = "id", required = false) String id) {
        if (title != null && !title.isEmpty()) {
            try {
                String url = "http://www.omdbapi.com/?apikey=" + apiKey + "&s=" + title;
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class); 
    
                if (response.getStatusCode().is2xxSuccessful()) {
                    return response;
                } else {
                    return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не удалось найти сведений о фильме, проверьте правильность вводных данных.\n\n"+res);
                }
    
            } catch (RestClientException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Произошла серверная ошибка, пожалйста, попробуйте позже.\n\n"+res);
            }
        }else if (id != null && !id.isEmpty()) {
            try {
                String url = "http://www.omdbapi.com/?apikey=" + apiKey + "&i=" + id;
                ResponseEntity<String> response = restTemplate.getForEntity(url, String.class);
    
                if (response.getStatusCode().is2xxSuccessful()) {
                    return response;
                } else {
                    ResponseEntity.status(HttpStatus.NOT_FOUND).body("Не удалось найти сведений о фильме, проверьте правильность вводных данных.\n\n"+res);
                }
            } catch (RestClientException e) {
                return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(res);
            }
        }else if (id.isEmpty()&&title.isEmpty()){
            return ResponseEntity.badRequest().body("Вы оставили поля пустыми, пожалуйста, заполните данные для поиска.\n\n" + res);
        }
        return null;
    }
}
