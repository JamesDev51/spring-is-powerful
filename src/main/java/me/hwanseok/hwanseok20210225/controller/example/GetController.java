package me.hwanseok.hwanseok20210225.controller.example;


import me.hwanseok.hwanseok20210225.model.example.SearchParam;
import me.hwanseok.hwanseok20210225.model.netwrok.Header;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api") // localhost:8080/api
public class GetController {

    @RequestMapping(method = RequestMethod.GET, path = "/getMethod") // localhost:8080/api/getMethod
    public String getRequest(){
        return "Hello World";
    }

    @GetMapping("/getParameter") // localhost:8080/api/getParameter?id=1234&password=abcd
    public String getParameter(@RequestParam String id, @RequestParam(name = "password") String pwd){
        String password = "bbb";
        System.out.println("id : "+id);
        System.out.println("pwd: "+pwd);
        return id + pwd;
    }

    @GetMapping("getMultiParameter")
    public String getMultiParameter(SearchParam searchParam){
        System.out.println(searchParam.getAccount());
        System.out.println(searchParam.getEmail());
        System.out.println(searchParam.getPage());
        return "OK";
    }
    @GetMapping("/header")
    public Header getHeader(){
//        {"resultCode":"OK", "description":"OK"{
        return Header.builder().resultCode("OK").description("OK").build();
    }

}
