package hello.hellospring.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.processing.Generated;

@Controller
public class HelloController {

    // GET 메소드 /hello 를 호출 했을 때 실행됨
    @GetMapping("hello")
    public String hellss(Model model){
        // 모델에 값 추가 후 넘겨줌
        model.addAttribute("data","Value1234");
        // ViewResolver를 통해 hello.html을 재 갱신하라.
        return "hello";
    }

    @GetMapping("hello-mvc")
    public String mvcControler(@RequestParam(value = "name", required = false, defaultValue = "예비 사용자")  String Myname, Model model){
        model.addAttribute("name", Myname);
        return "hello-mvc";
    }

    @GetMapping("hello-API")
    @ResponseBody
    public Hello helloApi(@RequestParam("name") String name){
        Hello hello = new Hello();
        hello.setName(name);
        return hello;
    }

    static class Hello{
        private String name;

        public String getName() {
            return name;
        }
        public void setName(String name) {
            this.name = name;
        }

    }

}
